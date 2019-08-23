package cn.jun.mysql.dao;

import cn.jun.mysql.vo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    void insert(User user);

    void insertBatch(List<User> list);

}
