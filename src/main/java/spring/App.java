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
        bean.notify("spam",Importance.CRITICAL);
        bean.notify("I am not spam",Importance.CRITICAL);
        applicationContext.close();

    }
}
//TODO
// plugin WSL2 to be installed. 2 version only


// .gitignore !!!!!!!!!!!!
// /target
//  pom.xml never to be excluded
// git rm --cached