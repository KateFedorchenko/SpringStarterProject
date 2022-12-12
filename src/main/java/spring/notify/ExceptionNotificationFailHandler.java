package spring.notify;

import org.springframework.stereotype.Component;
import spring.anno.ExceptionHandler;

@Component
@ExceptionHandler
public class ExceptionNotificationFailHandler implements NotificationFailHandler {
    @Override
    public void handle(String message, Importance importance) {
        System.err.println("You do not have rights to write in a file!");
    }
}
