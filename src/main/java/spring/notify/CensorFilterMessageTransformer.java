package spring.notify;

import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CensorFilterMessageTransformer implements MessageTransformer {
    private BadWord badWord;

    public CensorFilterMessageTransformer(BadWord badWord){
        this.badWord=badWord;
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
        return badWord.isBadWord(word);
    }
}
// No lower case! It matters. Save the register as in the original message. "Hello the bad" -> Hello the ***
// Quantity of *** should be the same as in the word
// No const as in HM. This class should have no const! Think over.

