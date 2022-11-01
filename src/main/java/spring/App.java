package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.notify.NotificationService;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ns1-config.xml");
        NotificationService notificationService = applicationContext.getBean(NotificationService.class);
        notificationService.notify("hello that bad");

        System.out.println();


    }
}
