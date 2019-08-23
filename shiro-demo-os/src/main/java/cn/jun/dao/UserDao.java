package cn.jun.dao;

import cn.jun.vo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User getUserByLogin(User user);

    User getUserByName(@Param("username") String username);

}
