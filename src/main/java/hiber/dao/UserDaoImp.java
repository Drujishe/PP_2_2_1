package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User", User.class).getResultList();
    }

    @Override
    public List<Car> listCars() {
        return sessionFactory.getCurrentSession().createQuery("from Car", Car.class).getResultList();
    }

    @Override
    public List<User> getUserByCar(int series, String model) {
        return sessionFactory.getCurrentSession().createQuery(
                        "FROM User as user where user.car.series = " + series, User.class)
                .getResultList();
    }
}