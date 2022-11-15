package spring.notify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemOutMessageAppender implements MessageAppender {
    private final Importance importance;

    public SystemOutMessageAppender(@Value("${spring.notify.importance.low}") Importance importance) {
        this.importance = importance;
    }

    @Override
    public void appendMessage(String message) {
        System.out.println(message);
    }

    @Override
    public Importance getImportance() {
        return importance;
    }
}
