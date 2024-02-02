package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void add(User user, Car car) {
        if (user.getCar() != null) {
            user.getCar().setUser(user);
        }
        userDao.add(user);
        System.out.println("user: " + user);
    }

    @Override
    public List<User> listUsers() {
        List<User> users = userDao.listUsers();
        System.out.println("Retrieved all users: " + users);
        return users;
    }

    @Override
    public List<User> getUserByCar(String model, int series) {
        List<User> users = userDao.getUserByCar(model, series);
        System.out.println("Retrieved users by car: " + users);
        return users;
    }
}
