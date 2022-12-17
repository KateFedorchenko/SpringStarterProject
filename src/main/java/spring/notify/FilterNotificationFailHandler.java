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


//        int size = filterFailedTime.size();
//        if(size >= 5){
//            Long lastDate = filterFailedTime.get(size - 1);
//            Long firstDate = filterFailedTime.get(size - 5);
//            if((lastDate-firstDate) <= 60){
//                System.err.println("Too many filters have been failed");
//                filterFailedTime.clear();
//            }
//        }
    }

//    public static int findMaxAverage(int[] nums, int k) {
//        //If the array does not contain k elements it is not eligible
//        if (nums.length < k)
//            return -1;
//        //Declaring the minimum
//        int max = 0;
//        //Finding the sum of first k elements
//        for (int i = 0; i < k; i++)
//            max = max + nums[i];
//        int cur = max;
//        //As the sliding window moves
//        //We move the start and end index by 1
//        for (int i = k; i < nums.length; i++) {
//            //Thus we remove the initial index value
//            cur = cur - nums[i - k] + nums[i];
//            //And add the current index value
//            max = Math.max(cur, max);
//        }
//        return max / k;
//
//    }

}


// queue to be used + leetcode problem
// to be thread safe
