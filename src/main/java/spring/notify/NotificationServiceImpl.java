package spring.notify;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spring.anno.ExceptionHandler;
import spring.anno.FilterHandler;
import spring.anno.TeamBlue;
import spring.anno.TeamRed;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final List<MessageAppender> messageAppenders;
    private final List<MessageTransformer> messageTransformers;
    private final MessageFilter originalMessageFilter;
    private final MessageFilter transformedMessageFilter;
    private final NotificationFailHandler exceptionHandler;
    private final NotificationFailHandler filterHandler;
    private List<Long> filterFailedTime = new ArrayList<>();

    public NotificationServiceImpl(
            List<MessageAppender> messageAppenders,
            @Qualifier("timestampMessageTransformer") List<MessageTransformer> messageTransformers,
            @TeamRed MessageFilter originalMessageFilter,
            @TeamBlue MessageFilter transformedMessageFilter,
            @ExceptionHandler NotificationFailHandler exceptionHandler,
            @FilterHandler NotificationFailHandler filterHandler) {
        this.messageAppenders = messageAppenders;
        this.messageTransformers = messageTransformers;
        this.originalMessageFilter = originalMessageFilter;
        this.transformedMessageFilter = transformedMessageFilter;
        this.exceptionHandler = exceptionHandler;
        this.filterHandler = filterHandler;
    }

    @Override
    public void notify(String message, Importance importance) {
        String transformedMessage = message;

        if (originalMessageFilter.filter(message) || transformedMessageFilter.filter(message)) {
            filterFailedTime.add(System.currentTimeMillis()/1000);
            int size = filterFailedTime.size();
            if(size >= 5){
                Long lastDate = filterFailedTime.get(size - 1);
                Long firstDate = filterFailedTime.get(size - 5);
                if((lastDate - firstDate) <= 60){
                    filterHandler.handle(message,importance);
                    filterFailedTime.removeAll(filterFailedTime);
                }
            }
            return;
        }

        for (MessageTransformer mt : messageTransformers) {
            transformedMessage = mt.transform(transformedMessage);
        }

        for (MessageAppender messageAppender : messageAppenders) {
            List<Importance> importanceList = messageAppender.getListImportance();

            if (importanceList.contains(importance)) {
                try {
                    messageAppender.appendMessage(transformedMessage);
                } catch (RuntimeException e) {
                    exceptionHandler.handle(transformedMessage, importance);
                }

            }
        }
    }
}


// 1) concurrent task later
// 2) Two impls (handler): for exceptions-100% (error stream) and filters-not 100% (only print message if
// quantity of filtered messages exceeds some number in 1 min - sliding window problem).
