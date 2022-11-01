package spring.notify;


public class NotificationServiceImpl implements NotificationService{
    private final MessageAppender messageAppender;
    private final MessageTransformer messageTransformer;

    public NotificationServiceImpl(MessageAppender messageAppender, MessageTransformer messageTransformer) {
        this.messageAppender = messageAppender;
        this.messageTransformer = messageTransformer;
    }

    @Override
    public void notify(String message) {
        String transform = messageTransformer.transform(message);
        messageAppender.appendMessage(transform);
    }

}
