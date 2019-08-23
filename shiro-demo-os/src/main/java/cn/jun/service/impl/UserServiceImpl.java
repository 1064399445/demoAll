package cn.jun.service.impl;

import cn.jun.dao.UserDao;
import cn.jun.service.UserService;
import cn.jun.utils.ResultVo;
import cn.jun.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public ResultVo login(User user) {
        ResultVo resultVo = null;
        try {
            User loginUser = userDao.getUserByLogin(user);
            resultVo = new ResultVo("200","查询成功",loginUser);
        }catch (Exception e){
            e.printStackTrace();
            resultVo = new ResultVo("400","后台错误");
        }
        return resultVo;
    }
}
