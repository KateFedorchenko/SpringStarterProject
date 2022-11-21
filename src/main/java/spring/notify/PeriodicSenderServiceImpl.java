package spring.notify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class PeriodicSenderServiceImpl implements PeriodicSenderService {
    @Value("${spring.notify.periodic.sender.message}")
    private String message;

    private NotificationService notificationService;
    @Override
    public void send(int count) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("spring");
        NotificationService bean = applicationContext.getBean(NotificationService.class);

        CountDownLatch lock = new CountDownLatch(count);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
            bean.notify(message, Importance.LOW);
            lock.countDown();
        }, 500, 100, TimeUnit.MILLISECONDS);

        lock.await(1000, TimeUnit.MILLISECONDS);
        future.cancel(true);
    }
}
