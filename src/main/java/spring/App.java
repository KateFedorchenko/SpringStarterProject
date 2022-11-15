package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.notify.Importance;
import spring.notify.NotificationService;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("spring");
        NotificationService bean = applicationContext.getBean(NotificationService.class);
        bean.notify("bad", Importance.MEDIUM);
        bean.notify("low", Importance.LOW);
        bean.notify("critical", Importance.CRITICAL);
        bean.notify("dgd", Importance.CRITICAL);
        bean.notify("critgdgdical", Importance.CRITICAL);
        bean.notify("critewfical", Importance.CRITICAL);


    }
}
//TODO
// plugin WSL2 to be installed. 2 version only


// .gitignore !!!!!!!!!!!!
// /target
//  pom.xml never to be excluded
// git rm --cached