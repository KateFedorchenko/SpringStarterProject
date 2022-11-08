package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.notify.NotificationService;
import spring.notify.NotificationServiceImpl;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("spring");
        NotificationService bean = applicationContext.getBean(NotificationService.class);
        bean.notify("bad", NotificationService.Importance.MEDIUM);



    }
}
//TODO
// plugin WSL2 to be installed. 2 version only


// .gitignore !!!!!!!!!!!!
// /target
//  pom.xml never to be excluded
// git rm --cached