package spring.notify;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import spring.anno.TeamRed;

@Component
@TeamRed
public class LongMessageFilter implements MessageFilter {
    private final int maxMessageLength;

    public LongMessageFilter(@Value("${spring.notify.message.filter.max.message.length}") int maxMessageLength) {
        this.maxMessageLength = maxMessageLength;
    }

    @Override
    public boolean filter(String message) {
        return message.length() > maxMessageLength;
    }
}
