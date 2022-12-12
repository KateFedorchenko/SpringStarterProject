package spring.notify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Lazy
public class CensorFilterMessageTransformer implements MessageTransformer {
    private final Set<String> badWords;

    public CensorFilterMessageTransformer(@Value("#{'${spring.notify.bad.words}'.split(',')}") List<String> badWords) {
        this.badWords = badWords.stream().map(String::toLowerCase).collect(Collectors.toSet());    //map(x -> x.toLowerCase())
    }

    @Override
    public String transform(String message) {
        String[] strings = message.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < strings.length; i++) {
            if (isBadWord(strings[i])) {
                int length = strings[i].length();
                for (int j = 0; j < length; j++) {
                    sb.append("*");
                }
                sb.append(" ");
            } else {
                sb.append(strings[i]).append(" ");
            }
        }
        return sb.toString();
    }

    private boolean isBadWord(String word) {
        return badWords.contains(word.toLowerCase());
    }
}
// No lower case! It matters. Save the register as in the original message. "Hello the bad" -> Hello the ***
// Quantity of *** should be the same as in the word
// No const as in HM. This class should have no const! Think over.

