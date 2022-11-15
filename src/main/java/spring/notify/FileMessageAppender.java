package spring.notify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class FileMessageAppender implements MessageAppender {
    private final File file;
    private final Importance importance;

    public FileMessageAppender(@Value("${spring.notify.message.appender.file}") File file,
                               @Value("${spring.notify.importance.medium}") Importance importance) {
        this.file = file;
        this.importance = importance;
    }

    @Override
    public void appendMessage(String message) {
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Importance getImportance() {
        return importance;
    }
}
