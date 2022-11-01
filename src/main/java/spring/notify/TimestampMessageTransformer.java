package spring.notify;

import java.text.SimpleDateFormat;
import java.util.Date;

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
