package spring.notify;

public interface MessageAppender {

    void appendMessage(String message);

    Importance getImportance();
}
