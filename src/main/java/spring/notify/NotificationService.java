package spring.notify;

public interface NotificationService {
    void notify(String message, Importance importance);

    enum Importance{
        CRITICAL,
        HIGH,
        MEDIUM,
        LOW
    }
}


