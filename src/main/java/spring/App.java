package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.notify.Importance;
import spring.notify.NotificationService;
import spring.notify.PeriodicSenderService;

public class App {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("spring");
        PeriodicSenderService bean = applicationContext.getBean(PeriodicSenderService.class);
        bean.send("Hello",100,2, Importance.CRITICAL);
        bean.send("boo",120,3,Importance.MEDIUM);
        bean.send("foo",130,2,Importance.LOW);
        applicationContext.close();

    }
}
//TODO
// plugin WSL2 to be installed. 2 version only


// .gitignore !!!!!!!!!!!!
// /target
//  pom.xml never to be excluded
// git rm --cached