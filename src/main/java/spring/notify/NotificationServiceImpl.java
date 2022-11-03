package spring.notify;

import spring.anno.AwesomeService;

import java.util.List;

@AwesomeService
public class NotificationServiceImpl implements NotificationService {
    private final List<MessageAppender> messageAppenders;
    private final List<MessageTransformer> messageTransformers;

    public NotificationServiceImpl(
            List<MessageAppender> messageAppenders,
            /*@Qualifier("timestampMessageTransformer")*/ List<MessageTransformer> messageTransformers // ctrl+shift+/
    ) {
        this.messageAppenders = messageAppenders;
        this.messageTransformers = messageTransformers;
    }

    @Override
    public void notify(String message) {
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


