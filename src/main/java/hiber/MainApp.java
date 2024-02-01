package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {//todo: throws BeansException ...не нужно добавлять вещи, смысл которых не понятен
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        Car car1 = new Car("Toyota", 123);
        Car car2 = new Car("BMW", 456);
        Car car3 = new Car("Audi", 789);
        Car car4 = new Car("Jigyli", 543);

        user1.setCar(car1);
        userService.add(user1, car1);

        user2.setCar(car2);
        userService.add(user2, car2);

        user3.setCar(car3);
        userService.add(user3, car3);

        user4.setCar(car4);
        userService.add(user4, car4);

        //Вывод счасливого обладеля машины
        List<User> users = userService.getUserByCar("Toyota", 123);
        //Вывод всех пользователь с машиинами
//        List<User> users = userService.listUsers();//todo: codeStyle - код не оставляем в комментариях
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println(user.getCar());
            System.out.println();
        }
        context.close();
    }
}
