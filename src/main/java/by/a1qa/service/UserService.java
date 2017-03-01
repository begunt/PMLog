package by.a1qa.service;

import by.a1qa.dao.UserDao;
import by.a1qa.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by t.begun on 01.03.2017.
 */
@Service
@Transactional
public class UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(User user) {
        this.userDao.addUser(user);
    }

    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }

    public void removeUser(int id) {
        this.userDao.removeUser(id);
    }

    public User getUserById(int id) {
        return this.userDao.getUserById(id);
    }

    public User getUserByLogin(String login) {
        return this.userDao.getUserByLogin(login);
    }

    public List<User> listUsers() {
        return this.userDao.listUsers();
    }

    public boolean ifUserExists(String login){
        return this.userDao.ifUserExists(login);
    }
}