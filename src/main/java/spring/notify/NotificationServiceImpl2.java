package spring.notify;


public class NotificationServiceImpl2 implements NotificationService{
    private final MessageAppender messageAppender;

    public NotificationServiceImpl2(MessageAppender messageAppender) {
        this.messageAppender = messageAppender;
    }

    @Override
    public void notify(String message) {
        CensorFilterMessageTransformerImpl censorFilterMessageTransformer = new CensorFilterMessageTransformerImpl();
        String transform = censorFilterMessageTransformer.transform(message);
        messageAppender.appendMessage(transform);
    }

}
