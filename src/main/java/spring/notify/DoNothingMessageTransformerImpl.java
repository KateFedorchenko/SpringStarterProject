package spring.notify;

import java.util.HashMap;
import java.util.Map;

public class DoNothingMessageTransformerImpl implements MessageTransformer {

    @Override
    public String transform(String message) {
        return message;
    }

}
