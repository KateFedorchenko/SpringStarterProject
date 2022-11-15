package spring.notify;

import spring.anno.AwesomeService;

import java.util.List;

@AwesomeService
public class NotificationServiceImpl implements NotificationService {
    private final List<MessageAppender> messageAppenders;
    private final List<MessageTransformer> messageTransformers;

    public NotificationServiceImpl(
            List<MessageAppender> messageAppenders,
            List<MessageTransformer> messageTransformers
    ) {
        this.messageAppenders = messageAppenders;
        this.messageTransformers = messageTransformers;

    }

    @Override
    public void notify(String message, Importance importance) {
        String transformedMessage = message;

        for (MessageTransformer mt : messageTransformers) {
            transformedMessage = mt.transform(transformedMessage);
        }

        for (MessageAppender messageAppender : messageAppenders) {
            Importance imp = messageAppender.getImportance();
            if (importance == imp) {
                messageAppender.appendMessage(transformedMessage);
            }
        }

    }
}


// 1) change NotificationService #notify(String str, Important.low/med/high (enum))
// each MessApp has param the Important
// SOUT only high important, File - all important, etc

