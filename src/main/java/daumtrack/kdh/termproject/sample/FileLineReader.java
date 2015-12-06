package daumtrack.kdh.termproject.sample;

import daumtrack.kdh.termproject.batch.DataItemReader;
import daumtrack.kdh.termproject.batch.NoMoreDataException;

import java.io.*;

/**
 * FileLineReader.
 *
 * @author mitchell.geek
 */
public class FileLineReader implements DataItemReader<String> {
    private final File file;
    private BufferedReader fileReader;
    private String fetched = null;

    public FileLineReader(File file) {
        this.file = file;
    }

    @Override
    public void init() throws FileNotFoundException {
        fileReader = new BufferedReader(new FileReader(file));
    }

    @Override
    public void close() throws IOException {
        fileReader.close();
    }

    @Override
    public boolean hasNext() {
        try {
            if (fetched == null) {
                fetched = fileReader.readLine();
            }
            return fetched != null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String next() throws NoMoreDataException {
        if (hasNext()) {
            String aLine = fetched;
            fetched = null;
            return aLine;
        } else {
            throw new NoMoreDataException();
        }
    }
}
