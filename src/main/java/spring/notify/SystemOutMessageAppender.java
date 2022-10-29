package spring.notify;

public class SystemOutMessageAppender implements MessageAppender {
    @Override
    public void appendMessage(String message) {
        System.out.println(message);
    }
}
