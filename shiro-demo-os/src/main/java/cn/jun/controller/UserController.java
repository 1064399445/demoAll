package cn.jun.controller;

import cn.jun.service.UserService;
import cn.jun.utils.ResultVo;
import cn.jun.vo.User;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo login(User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        subject.login(usernamePasswordToken);
        return userService.login(user);
    }

    @RequestMapping(value = "/index")
    @ResponseBody
    public ResultVo index() {
        ResultVo resultVo = new ResultVo("200","登陆成功");
        return resultVo;
    }

    /**
     * 等出
     * @return
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public String logout() {
        return "logout";
    }

    /**
     * 错误页面展示
     * @return
     */
    @RequestMapping(value = "/error",method = RequestMethod.POST)
    public String error() {
        return "error ok!";

    }

    /**
     * 注解的使用
     * @return
     */
    @RequiresRoles("admin")
    @RequiresPermissions("create")
    @RequestMapping(value = "/create")
    public String create() {
        return "Create success!";
    }
}
