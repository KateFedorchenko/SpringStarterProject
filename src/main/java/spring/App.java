package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.notify.Importance;
import spring.notify.NotificationService;

public class App {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("spring");
        Thread.sleep(50_000);
        applicationContext.close();

    }
}
//TODO
// plugin WSL2 to be installed. 2 version only


// .gitignore !!!!!!!!!!!!
// /target
//  pom.xml never to be excluded
// git rm --cached