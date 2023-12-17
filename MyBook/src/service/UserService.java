package service;

import dao.UserDao;
import entity.User;

import java.util.List;

public class UserService {
    UserDao userDao = new UserDao();

    public User login(String username, String password, String checkCode, String piccode) {
        return userDao.login(username, password, checkCode, piccode);
    }

    public int userAdd(User user) {
        return userDao.userAdd(user);
    }

    public int delete(String id) {
        return userDao.delete(id);
    }

    public List<User> getUserListByName(String name) {
        return userDao.getUserListByName(name);
    }

    public int update(User user){
        return userDao.update(user);
    }

    public User getUserInfo(String id){return userDao.getUserInfo(id);}
}
