package spring.notify;

import spring.anno.AwesomeService;

import java.util.List;

@AwesomeService
public class NotificationServiceImpl implements NotificationService {
    private final List<MessageAppender> messageAppenders;
    private final List<MessageTransformer> messageTransformers;
    private final Importance importance;

    public NotificationServiceImpl(
            List<MessageAppender> messageAppenders,
            /*@Qualifier("timestampMessageTransformer")*/ List<MessageTransformer> messageTransformers,
           Importance importance
    ) {
        this.messageAppenders = messageAppenders;
        this.messageTransformers = messageTransformers;
        this.importance = importance;
    }

    @Override
    public void notify(String message, Importance importance) {
        String transformedMessage = message;
        for (int i = 0; i < messageTransformers.size(); i++) {
            MessageTransformer mt = messageTransformers.get(i);
            transformedMessage = mt.transform(transformedMessage);
        }
        for (int i = 0; i < messageAppenders.size(); i++) {
            MessageAppender ma = messageAppenders.get(i);
            ma.appendMessage(transformedMessage);
        }
    }
}


// 1) change NotificationService #notify(String str, Important.low/med/high (enum))
// each MessApp has param the Important
// SOUT only high important, File - all important, etc

