package spring.notify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.*;

@Component
public class PeriodicSenderServiceImpl implements PeriodicSenderService {
    private final String message;

    private final NotificationService notificationService;

    private final ExecutorService executorService;

    public PeriodicSenderServiceImpl(@Value("${spring.notify.periodic.sender.message}") String message,
                                     NotificationService notificationService) {
        this.message = message;
        this.notificationService = notificationService;
        executorService = Executors.newFixedThreadPool(2);
        executorService.submit(this::foo);
    }

    private void foo(){
        int n = 2;
        int interval = 1000; // 1 sec
        for (int i = 0; i < n; i++) {
            notificationService.notify(message, Importance.LOW);
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @PreDestroy
    private void onDestroy(){
        executorService.shutdown();
    }
}
