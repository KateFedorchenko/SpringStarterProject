package spring.notify;


/**
 * Handler which contains an action that takes place if Notification fails.
 */
public interface NotificationFailHandler {
    void handle(String message, Importance importance);
}
