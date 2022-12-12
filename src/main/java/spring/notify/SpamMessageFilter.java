package spring.notify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import spring.anno.TeamBlue;

import java.util.List;

@Component
@TeamBlue
public class SpamMessageFilter implements MessageFilter {
    private final List<String> spamWords;

    public SpamMessageFilter(@Value("${spring.notify.message.filter.spam.words}") List<String> spamWords) {
        this.spamWords = spamWords;
    }

    @Override
    public boolean filter(String message) {
        return spamWords.contains(message);
    }
}
