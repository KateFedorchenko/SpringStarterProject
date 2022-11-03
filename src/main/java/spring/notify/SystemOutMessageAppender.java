package spring.notify;

import org.springframework.stereotype.Component;

@Component
public class SystemOutMessageAppender implements MessageAppender {
    @Override
    public void appendMessage(String message) {
        System.out.println(message);
    }
}
