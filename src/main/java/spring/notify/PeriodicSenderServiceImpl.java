package spring.notify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.*;

@Component
public class PeriodicSenderServiceImpl implements PeriodicSenderService {
    private static class MessageInfo {
        private String message;
        private Importance importance;

        public MessageInfo(String message, Importance importance) {
            this.message = message;
            this.importance = importance;
        }
    }

    private final SortedMap<Long, List<MessageInfo>> messageInfo = new TreeMap<>();
    private final NotificationService notificationService;
    private volatile boolean isWorking = true;

    private final ExecutorService executorService;

    public PeriodicSenderServiceImpl(NotificationService notificationService) {
        this.notificationService = notificationService;
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
                    while (true) {
                        synchronized (messageInfo) {
                            if (!isWorking && messageInfo.isEmpty()) {
                                break;
                            }
                            if (!messageInfo.isEmpty()) {
                                Long firstKey = messageInfo.firstKey();

                                if (firstKey <= System.currentTimeMillis()) {

                                    List<MessageInfo> messages = messageInfo.get(firstKey);
                                    for (MessageInfo message : messages) {
                                        notificationService.notify(message.message, message.importance);
                                    }
                                    messageInfo.remove(firstKey);
                                }
                            } else {
                                try {
                                    messageInfo.wait();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                        }
                    }
                }
        );
    }


    @PreDestroy
    private void onDestroy() {
        isWorking = false;
        synchronized (messageInfo) {
            messageInfo.notify();
        }
        executorService.shutdown();
    }

    @Override
    public void send(String message, long interval, int count, Importance importance) {
        synchronized (messageInfo) {
            long curTime = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                messageInfo.computeIfAbsent(
                        curTime + interval * i,
                        k -> new ArrayList<>()).add(new MessageInfo(message, importance));
            }
            messageInfo.notify();
        }
        // 1 - curTime + interval*0
        // 2 - curTime + interval*1
        // 3 - curTime + interval*2
        // 4 - cueTime + interval*3
    }
}

// close context correctly
// if Map.isEmpty -- Worker should wait till new tasks come, somebody must notify
// * Importance should be added to send(), should be used in NotificationService
// some issues: 1 key with diff messages with diff importances -> how to add importance to map?
// note: extra class may help here