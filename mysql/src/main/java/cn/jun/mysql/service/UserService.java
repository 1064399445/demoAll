package cn.jun.mysql.service;

import cn.jun.mysql.dao.UserDao;
import cn.jun.mysql.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public void insert(User user){
        userDao.insert(user);
    }

}
