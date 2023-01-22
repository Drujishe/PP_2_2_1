package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.openSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<Car> listCars() {
//        return sessionFactory.openSession().createNativeQuery("SELECT * FROM cars", Car.class).getResultList();
//        return listUsers()
//                .stream().map(User::getCar)
//                .collect(Collectors.toList());
        return sessionFactory.openSession().createQuery("from Car", Car.class).getResultList();
    }

    @Override
    public List<User> getUserByCar(int series, String model) {
//        String request = String.format("SELECT * FROM cars WHERE series = %d AND model = \"%s\"", series, model);
//        return sessionFactory.openSession().createNativeQuery(request, Car.class)
//                .stream().map(Car::getUser)
//                .collect(Collectors.toList());

        List<User> result = sessionFactory.openSession().createQuery(
                        "FROM User as user where user.car.series = " + series, User.class)
                .getResultList();
        return result;
    }
}
