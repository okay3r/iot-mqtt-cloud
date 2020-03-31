package yx.graduation.elec.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import yx.graduation.elec.mapper.UserMapper;
import yx.graduation.elec.pojo.User;
import yx.graduation.elec.pojo.bo.UserBo;
import yx.graduation.elec.service.UserService;
import yx.graduation.utils.ApiJsonResult;
import yx.graduation.utils.MobileEmailUtils;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 创建新用户
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public ApiJsonResult createUser(UserBo userBo) {
        String check = check(userBo);
        if (check != null) {
            return ApiJsonResult.errorMsg(check);
        }

        User user = new User();
        BeanUtils.copyProperties(userBo, user);
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        // user.setRemark("无");

        this.userMapper.insert(user);
        return ApiJsonResult.ok("注册成功");
    }

    /**
     * 登录验证用户名、密码
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User queryForLogin(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User one = this.userMapper.selectOne(user);
        return one;
    }

    /**
     * 根据用户名查询用户id
     *
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User queryUserByName(String username) {
        User user = new User();
        user.setUsername(username);
        User res = this.userMapper.selectOne(user);
        return res;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public String check(UserBo userBo) {
        String checkUsername = checkUsername(userBo.getUsername());
        if (checkUsername != null) {
            return checkUsername;
        }

        String checkEmail = checkEmail(userBo.getEmail());
        if (checkEmail != null) {
            return checkEmail;
        }

        String checkPhone = checkPhone(userBo.getPhone());
        if (checkPhone != null) {
            return checkPhone;
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public String checkUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return "用户名不能为空";
        }
        User user = new User();
        user.setUsername(username);
        if (this.userMapper.selectOne(user) != null) {
            return "用户名已被注册";
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public String checkEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return "邮箱不能为空";
        }
        if (!MobileEmailUtils.checkEmailIsOk(email)) {
            return "邮箱格式不正确";
        }
        User user = new User();
        user.setEmail(email);
        if (this.userMapper.selectOne(user) != null) {
            return "邮箱已被注册";
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public String checkPhone(String phone) {
        if (StringUtils.isBlank(phone)) {
            return "手机号码不能为空";
        }
        if (!MobileEmailUtils.checkMobileIsOk(phone)) {
            return "手机号码格式不正确";
        }
        User user = new User();
        user.setPhone(phone);
        if (this.userMapper.selectOne(user) != null) {
            return "手机号已被注册";
        }
        return null;
    }
}
