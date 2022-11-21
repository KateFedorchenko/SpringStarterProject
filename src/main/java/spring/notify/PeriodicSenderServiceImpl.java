package spring.notify;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class PeriodicSenderServiceImpl implements PeriodicSenderService {
    @Override
    public void send(int count) throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(count);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
            bean.notify( , Importance.LOW);
            lock.countDown();
        }, 500, 100, TimeUnit.MILLISECONDS);

        lock.await(1000, TimeUnit.MILLISECONDS);
        future.cancel(true);
    }
}
