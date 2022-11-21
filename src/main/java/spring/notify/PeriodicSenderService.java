package spring.notify;

public interface PeriodicSenderService {
    void send(int count) throws InterruptedException;
}
