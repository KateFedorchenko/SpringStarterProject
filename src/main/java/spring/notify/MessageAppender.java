package spring.notify;

import java.util.List;

public interface MessageAppender {

    void appendMessage(String message);

    List<Importance> getListImportance();
}
