package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    private UserDao userDao;

    @Override
    public void add(User user, Car car) {
        if (user.getCar() != null) {
            user.getCar().setUser(user);
        }
        userDao.add(user);
        logger.info("User added: " + user);
    }

    @Override
    public List<User> listUsers() {
        List<User> users = userDao.listUsers();
        logger.info("Retrieved all users: " + users);
        return users;
    }

    @Override
    public List<User> getUserByCar(String model, int series) {
        List<User> users = userDao.getUserByCar(model, series);
        logger.info("Retrieved users by car: " + users);
        return users;
    }
}
