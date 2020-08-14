package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("honda", 2000)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("audi", 3000)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("lexus", 4000)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("lamborghini", 5000)));

        User carUser = userService.getUserByCar("audi", 888888);

        System.out.println("User by Car: " + carUser.getFirstName() + "\n" + carUser.getLastName() + "\n" + carUser.getEmail());
        System.out.println();
        
        List<User> users = userService.listUsers();

        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car name = " + user.getCar().getName() + " car series = " + user.getCar().getSeries());
            System.out.println();
        }

        context.close();
    }
}
