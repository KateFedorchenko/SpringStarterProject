package spring.notify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class CriticalLogsMessageAppender extends FileMessageAppender {

    public CriticalLogsMessageAppender(@Value("${spring.notify.message.appender.critical_file}")File file,
                                       @Value("${spring.notify.message.appender.critical.logs.importance}")
                                       List<Importance> importance) {
        super(file,importance);
    }

}
