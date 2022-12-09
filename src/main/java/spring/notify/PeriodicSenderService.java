package spring.notify;

public interface PeriodicSenderService{
    void send(String message, long interval, int count, Importance importance);
}
