package spring.notify;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampMessageTransformerImpl implements MessageTransformer {

    @Override
    public String transform(String message) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        sb.append(message).append(" ").append(date);
        return sb.toString();
    }
}
