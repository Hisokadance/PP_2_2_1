package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.save(user);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<User> query = session.createQuery("from User", User.class);
            return query.getResultList();
        }
    }

    @Override
    public List<User> getUserByCar(String model, int series) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<User> query = session.createQuery("FROM User WHERE car.model =: model AND car.series =:series", User.class);
            query.setParameter("model", model).setParameter("series", series);
            List<User> users = query.getResultList();
            if (users.isEmpty()) {
                throw new EntityNotFoundException("У пользователя нет машины с моделью " + model + " и серией " + series);
            }
            return users;
        }
    }
}
