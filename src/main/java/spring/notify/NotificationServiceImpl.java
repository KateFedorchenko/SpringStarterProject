package spring.notify;


public class NotificationServiceImpl implements NotificationService{
    private final MessageAppender messageAppender;

    public NotificationServiceImpl(MessageAppender messageAppender) {
        this.messageAppender = messageAppender;
    }

    @Override
    public void notify(String message) {
        TimeStampMessageTransformerImpl timeStampMessageTransformer = new TimeStampMessageTransformerImpl();
        String transform = timeStampMessageTransformer.transform(message);
        messageAppender.appendMessage(transform);
    }

}
