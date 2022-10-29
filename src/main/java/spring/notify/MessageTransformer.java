package spring.notify;

public interface MessageTransformer {

    String transform(String message);
}


// NotificationServiceImpl should depend on MessageTransformer
// get a message and transform it into smth and then return a new msg in File
// options: 1) add timestamp to a msg, 2) censor bad words, 3) no-op (do nothing) transformer