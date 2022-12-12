package spring.notify;

public interface MessageFilter {
    /**
     * Filters incoming messages.
     * @param message incoming message.
     * @return true if the message passes filter, otherwise false.
     */
    boolean filter(String message);
}
