package daumtrack.kdh.termproject.batch.impl;

import daumtrack.kdh.termproject.batch.model.BatchResult;
import daumtrack.kdh.termproject.batch.util.BatchSystem;
import daumtrack.kdh.termproject.batch.except.NoMoreDataException;
import daumtrack.kdh.termproject.batch.writer.DataItemProcessor;
import daumtrack.kdh.termproject.batch.reader.DataItemReader;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * DefaultBatchSystemTest.
 *
 * @author 2011108219_김동훈
 */
public class DefaultBatchSystemTest {

    BatchSystem sut;
    DataItemReader reader;
    DataItemProcessor processor;

    @Before
    public void setUp() throws Exception {
        sut = new DefaultBatchSystem();
        reader = mock(DataItemReader.class);
        processor = mock(DataItemProcessor.class);

    }

    @Test
    public void 읽은_만큼_처리해야한다() throws Exception {
        // Given
        when(reader.hasNext())
                .thenReturn(true, true, false); // 2 items
        when(reader.next())
                .thenReturn("hello", "world");

        // When
        BatchResult result = sut.batch(reader, processor);

        // Then
        verify(processor, times(2)).process(anyString());
        assertThat(result.getProcessedItems(), is(2));
        assertThat(result.isSucessed(), is(true));
        assertThat(result.getException(), nullValue());
    }

    @Test
    public void 읽을때_예외가_발생하면_실패결과를_리턴한다() throws Exception {
        // Given
        when(reader.hasNext())
                .thenReturn(true, true, false); // 2 items
        when(reader.next())
                .thenThrow(new NoMoreDataException());

        // When
        BatchResult result = sut.batch(reader, processor);

        // Then
        assertThat(result.isSucessed(), is(false));
    }

    @Test
    public void 처리중예외가_발생해도_실패결과를_리턴한다() throws Exception {
        // Given
        when(reader.hasNext())
                .thenReturn(true, true, false); // 2 items
        when(reader.next())
                .thenReturn("hello", "world");
        doThrow(RuntimeException.class)
                .when(processor).process(anyString());

        // When
        BatchResult result = sut.batch(reader, processor);

        // Then
        assertThat(result.isSucessed(), is(false));
    }
}