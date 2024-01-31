package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional//todo: выносим над классом типовую аннотацию, как обобщение
    @Override
    public void add(User user, Car car) {
        userDao.add(user,car);
    }//todo: не забываем про log-и (иммитацию)

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUserByCar(String model, int series) {
        return userDao.getUserByCar(model, series);

    }
}