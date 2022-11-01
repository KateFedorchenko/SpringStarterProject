package spring.notify;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CensorFilterMessageTransformerImpl implements MessageTransformer {

    @Override
    public String transform(String message) {
        String toLowerCase = message.toLowerCase();
        String[] strings = toLowerCase.split(" ");
        StringBuilder sb = new StringBuilder();
        Map<Integer,String> badWords = new HashMap<>();
        badWords.put(1,"bad");
        badWords.put(2,"baddy");
        badWords.put(3,"badly");
        badWords.put(4,"worse");
        badWords.put(5,"worst");
        for (int i = 0; i < strings.length; i++) {
            boolean isBadWord = badWords.containsValue(strings[i]);
            if(isBadWord){
                sb.append("****").append(" ");
            } else {
                sb.append(strings[i]).append(" ");
            }
        }
        return sb.toString();
    }
}
