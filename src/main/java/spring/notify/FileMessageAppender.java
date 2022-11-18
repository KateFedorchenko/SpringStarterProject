package spring.notify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class FileMessageAppender implements MessageAppender {
    private final File file;
    private final List<Importance> importanceList;

    public FileMessageAppender(@Value("${spring.notify.message.appender.file}") File file,
                               @Value("${spring.notify.message.appender.file.importance}")
                               List<Importance> importanceList) {
        this.file = file;
        this.importanceList = importanceList;
    }

    @Override
    public void appendMessage(String message) {
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(message);
            fileWriter.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Importance> getListImportance() {
        return importanceList;
    }
}
