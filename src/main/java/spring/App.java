package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.notify.Importance;
import spring.notify.NotificationService;
import spring.notify.PeriodicSenderService;

public class App {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("spring");
        NotificationService bean = applicationContext.getBean(NotificationService.class);
        bean.notify("CRITICAL",Importance.CRITICAL);
        bean.notify("LOW",Importance.LOW);
        bean.notify("ad",Importance.MEDIUM);
        bean.notify("ad",Importance.MEDIUM);
        bean.notify("ad",Importance.MEDIUM);
        bean.notify("ad",Importance.MEDIUM);
        Thread.sleep(2000);
        bean.notify("ad",Importance.MEDIUM);
        bean.notify("ad",Importance.MEDIUM);
        bean.notify("MEDIUM",Importance.MEDIUM);
        applicationContext.close();

    }
}
//TODO
// plugin WSL2 to be installed. 2 version only


// .gitignore !!!!!!!!!!!!
// /target
//  pom.xml never to be excluded
// git rm --cached