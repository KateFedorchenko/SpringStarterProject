package spring.notify;

import org.springframework.stereotype.Component;
import spring.anno.FilterHandler;

@Component
@FilterHandler
public class FilterNotificationFailHandler implements NotificationFailHandler {
    @Override
    public void handle(String message, Importance importance) {
        System.err.println("Too many filters have been failed");

    }
}
