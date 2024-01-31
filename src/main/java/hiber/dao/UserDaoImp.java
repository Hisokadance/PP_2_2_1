package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    //todo: вводим Transaction аннотацией

    @Override
    public void add(User user, Car car) {
        sessionFactory.getCurrentSession().save(user);//todo: нет, так не делается. Необходимо на моделях создать связь и сделать сохраниение user with car одиним методом
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");//todo: Session - в try_catch_with_resources (везде)
        return query.getResultList();
    }

    @Override
    public List<User> getUserByCar(String model, int series) {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("FROM User WHERE car.model =: model AND car.series =:series", User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        List<User> users = query.getResultList();
        if (users.isEmpty()) {
            throw new EntityNotFoundException("у User нет машины");
        }
        return query.getResultList();
    }

}
