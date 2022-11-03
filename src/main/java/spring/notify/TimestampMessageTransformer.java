package spring.notify;

import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TimestampMessageTransformer implements MessageTransformer {

    @Override
    public String transform(String message) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        sb.append(date).append(" ").append(message);
        return sb.toString();
    }
}

// api to be newer. java.util.Calendar and java.util.Date - OUT OF USE! java.time to be used instead.
