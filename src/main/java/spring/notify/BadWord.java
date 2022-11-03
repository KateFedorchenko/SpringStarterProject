package spring.notify;

import java.util.List;

public class BadWord {
    private List<String> badWords;

    public BadWord(List<String> badWords){
        this.badWords = badWords;
    }

    public boolean isBadWord(String word){
        return badWords.contains(word);
    }
}
