package cn.jun.utils;

import cn.jun.dao.UserDao;
import cn.jun.vo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;


    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("doGetAuthorizationInfo:角色权限和对应权限添加");
        //获取登陆用户名
        User loginUser = (User)principalCollection.getPrimaryPrincipal();
        //添加权限和角色
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        loginUser.getRoles().forEach(role -> {
            //添加角色
            info.addRole(role.getRoleName());
            //添加权限
            role.getPermissions().forEach(permission -> {
                info.addStringPermission(permission.getPermissionName());
            });
        });
        return info;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("doGetAuthenticationInfo:用户认证");
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        User user = userDao.getUserByName(token.getUsername());
        if(user== null){
            return null;
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
