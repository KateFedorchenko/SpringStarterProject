package spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import spring.notify.FileMessageAppender;
import spring.notify.Importance;
import spring.notify.MessageAppender;

import java.io.File;
import java.util.List;

@Configuration
@PropertySource("classpath:config.properties")
public class ApplicationConfig {
    @Bean
    public MessageAppender criticalLogsMessageAppender(@Value("${spring.notify.message.appender.critical_file}") File file,
                                                       @Value("${spring.notify.message.appender.critical.logs.importance}")
                                                       List<Importance> importanceList) {
        return new FileMessageAppender(file, importanceList);
    }

    @Bean
    public MessageAppender fileMessageAppender(@Value("${spring.notify.message.appender.file}") File file,
                                               @Value("${spring.notify.message.appender.file.importance}")
                                               List<Importance> importanceList) {
        return new FileMessageAppender(file, importanceList);
    }
}
