package daumtrack.kdh.termproject.sample;

import daumtrack.kdh.termproject.batch.DataItemProcessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * FileLineWriter.
 *
 * @author mitchell.geek
 */
public class FileLineWriter implements DataItemProcessor<String> {
    private final File file;
    private BufferedWriter writer;

    public FileLineWriter(File file) {
        this.file = file;
    }

    @Override
    public void process(String dataItem) {
        try {
            writer.write(dataItem);
            writer.newLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void init() throws Exception {
        writer = new BufferedWriter(new FileWriter(file));
    }

    @Override
    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}
