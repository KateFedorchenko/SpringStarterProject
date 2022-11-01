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

        for (int i = 0; i < strings.length; i++) {
            if (isBadWord(strings[i])) {
                sb.append("****").append(" ");
            } else {
                sb.append(strings[i]).append(" ");
            }
        }
        return sb.toString();
    }

    private boolean isBadWord(String word) {
        Map<Integer, String> badWords = new HashMap<>();
        badWords.put(1, "bad");
        badWords.put(2, "baddy");
        badWords.put(3, "badly");
        badWords.put(4, "worse");
        badWords.put(5, "worst");
        return badWords.containsValue(word);
    }
}
