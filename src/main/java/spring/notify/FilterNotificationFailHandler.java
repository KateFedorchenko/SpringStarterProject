package spring.notify;

import org.springframework.stereotype.Component;
import spring.anno.FilterHandler;

import java.util.ArrayList;
import java.util.List;

@Component
@FilterHandler
public class FilterNotificationFailHandler implements NotificationFailHandler {
    private final List<Long> filterFailedTime = new ArrayList<>();

    @Override
    public void handle(String message, Importance importance) {
        filterFailedTime.add(System.currentTimeMillis()/1000);
        int size = filterFailedTime.size();
        if(size >= 5){
            Long lastDate = filterFailedTime.get(size - 1);
            Long firstDate = filterFailedTime.get(size - 5);
            if((lastDate-firstDate) <= 60){
                System.err.println("Too many filters have been failed");
                filterFailedTime.removeAll(filterFailedTime);
            }
        }
    }
}
