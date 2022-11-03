package spring.notify;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class NoopMessageTransformer implements MessageTransformer {

    @Override
    public String transform(String message) {
        return message;
    }

}
