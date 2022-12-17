package spring.notify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import spring.anno.FilterHandler;

import java.util.concurrent.ConcurrentLinkedDeque;

@Component
@FilterHandler
public class FilterNotificationFailHandler implements NotificationFailHandler {
    private final ConcurrentLinkedDeque<Long> failureTime = new ConcurrentLinkedDeque<>();
    private final int maxFailedFilters;

    public FilterNotificationFailHandler(@Value("${spring.notify.notification.fail.handler.max.failed.filters}") int maxFailedFilters) {
        this.maxFailedFilters = maxFailedFilters;
    }

    @Override
    public void handle(String message, Importance importance) {
        failureTime.offerFirst(System.currentTimeMillis());
        if (failureTime.size() == maxFailedFilters) {
            Long firstTime = failureTime.peek();
            Long lastTime = failureTime.getLast();
            if (lastTime - firstTime < 60000) {
                System.err.println("Failed");
                failureTime.clear();
            } else {
                failureTime.clear();
            }
        }
    }

//    public static int findMaxAverage(int[] nums, int k) {
//        if (nums.length < k) {
//            return -1;
//        }
//        int max = 0;
//        for (int i = 0; i < k; i++)
//            max = max + nums[i];
//        int cur = max;
//        for (int i = k; i < nums.length; i++) {
//            cur = cur - nums[i - k] + nums[i];
//            max = Math.max(cur, max);
//        }
//        return max / k;
//    }

}


// queue to be used + leetcode problem
// to be thread safe
