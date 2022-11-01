package spring.notify;


public class NotificationServiceImpl3 implements NotificationService{
    private final MessageAppender messageAppender;

    public NotificationServiceImpl3(MessageAppender messageAppender) {
        this.messageAppender = messageAppender;
    }

    @Override
    public void notify(String message) {
        DoNothingMessageTransformerImpl doNothingMessageTransformer = new DoNothingMessageTransformerImpl();
        String transform = doNothingMessageTransformer.transform(message);
        messageAppender.appendMessage(transform);
    }

}
