package spring.notify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SystemOutMessageAppender implements MessageAppender {
    private final List<Importance> importance;

    public SystemOutMessageAppender(@Value("${spring.notify.message.appender.systemout.importance}") List<Importance> importance) {
        this.importance = importance;
    }

    @Override
    public void appendMessage(String message) {
        System.out.println(message);
    }

    @Override
    public List<Importance> getListImportance() {
        return importance;
    }
}
