package spring.notify;

public class NoopMessageTransformer implements MessageTransformer {

    @Override
    public String transform(String message) {
        return message;
    }

}
