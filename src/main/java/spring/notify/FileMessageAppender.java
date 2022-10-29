package spring.notify;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileMessageAppender implements MessageAppender {
    private final File file;

    public FileMessageAppender(File file) {
        this.file = file;
    }

    @Override
    public void appendMessage(String message) {
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
