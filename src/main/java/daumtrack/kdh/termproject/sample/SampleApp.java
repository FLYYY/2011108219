package daumtrack.kdh.termproject.sample;

import daumtrack.kdh.termproject.batch.BatchResult;
import daumtrack.kdh.termproject.batch.DataItemReader;
import daumtrack.kdh.termproject.batch.BatchSystem;
import daumtrack.kdh.termproject.batch.DataItemProcessor;
import daumtrack.kdh.termproject.batch.ext.DataItemConvertAndProcessor;
import daumtrack.kdh.termproject.batch.impl.DefaultBatchSystem;

import java.io.File;
import java.io.IOException;

/**
 * SampleApp.
 *
 * @author mitchell.geek
 */
public class SampleApp {
    private final static String INPUT = "input.txt";
    private final static String OUTPUT = "output.txt";

    public static void main(String[] args) throws IOException {
        BatchSystem batchSystem = new DefaultBatchSystem();
        DataItemReader<String> reader = new FileLineReader(new File(INPUT));

        DataItemProcessor<String> processor = new DataItemConvertAndProcessor<>(
                new StringTokenSorter(),
                new FileLineWriter(new File(OUTPUT))
        );

        BatchResult result = batchSystem.batch(reader, processor);

        System.out.print(result.prettyReport());
    }
}
