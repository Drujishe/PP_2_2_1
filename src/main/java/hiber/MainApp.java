package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        int findingSeries = 136;

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        // иницилизация юзеров
        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        // иницилизация машин
        Car car1 = new Car("Mercedes",5658, user1);
        Car car2 = new Car("Lada", findingSeries, user2);
        Car car3 = new Car("Renault",1568795, user3);
        Car car4 = new Car("Nissan",687486, user4);

        // добавление в БД
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        // список всех юзеров и их данных
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        // получение списка всех машин
        System.out.println("\nСписок всех машин");
        List<Car> listCars = userService.listCars();
        listCars.forEach(System.out::println);

        // получение списка юзеров по серийному номеру и модели машины
        System.out.println("\nПоиск юзеров по серийному номеру и модели машины");
        List<User> list = userService.getUserByCar(findingSeries, "Lada");
        list.forEach(System.out::println);

        context.close();
    }
}