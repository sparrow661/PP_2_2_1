package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.util.List;

public class MainApp {
   public static void main(String[] args)  {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User Harry = (new User("Harry", "Potter", "Potter@gmail.com"));
      User Ron = (new User("Ron", "Weasly", "Weasly@gmail.com"));
      User Draco = (new User("Draco", "Malfoy", "Malfoy@gmail.com"));
      User Rybeys = (new User("Rybeys", "Hagrid", "Hagrid@gmail.com"));

      Car Ford = new Car("Ford", 10);
      Car Nissan = new Car("Nissan", 55);
      Car Toyota = new Car("Toyota", 228);
      Car Mersedes = new Car("Mersedes", 222);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      userService.add(Harry.setCar(Ford).setUser(Harry));
      userService.add(Ron.setCar(Nissan).setUser(Ron));
      userService.add(Draco.setCar(Toyota).setUser(Draco));
      userService.add(Rybeys.setCar(Mersedes).setUser(Rybeys));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("Ford", 10));

      try {
         System.out.println(userService.getUserByCarS(Ford));
      } catch (NoResultException e) {
         System.out.println("Пользователь с авто" + Ford + "не найден");
      }

      context.close();
   }
}
