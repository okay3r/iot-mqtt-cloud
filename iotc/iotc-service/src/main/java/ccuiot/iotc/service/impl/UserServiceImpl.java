package ccuiot.iotc.service.impl;

import ccuiot.iotc.enums.RedisKeyEnum;
import ccuiot.iotc.mapper.UserMapper;
import ccuiot.iotc.pojo.User;
import ccuiot.iotc.pojo.bo.UserBo;
import ccuiot.iotc.service.UserService;
import ccuiot.iotc.utils.ApiJsonResult;
import ccuiot.iotc.utils.MD5Utils;
import ccuiot.iotc.utils.RedisOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisOperation redisOperation;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public String queryUserForLogin(String username, String password) {
        User user = null;
        try {
            user = new User();
            user.setUsername(username);
            user.setPassword(MD5Utils.getMD5Str(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        User one = this.userMapper.selectOne(user);
        if (one == null) {
            return null;
        }
        String secretKey = getSecretKey(one);
        this.redisOperation.set(RedisKeyEnum.USER_KEY.value + ":" + one.getUsername(), secretKey, 1800);
        return secretKey;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ApiJsonResult userRegister(UserBo userBo) {
        if (checkUsername(userBo.getUsername())) {
            return ApiJsonResult.errorMsg("用户名已经存在");
        }
        if (checkEmail(userBo.getEmail())) {
            return ApiJsonResult.errorMsg("邮箱已经被注册");
        }
        if (checkPhone(userBo.getPhone())) {
            return ApiJsonResult.errorMsg("手机号已被注册");
        }
        User newUser = new User();
        BeanUtils.copyProperties(userBo, newUser);
        Date now = new Date();
        newUser.setCreateTime(now);
        newUser.setUpdateTime(now);
        newUser.setIsAdmin(0);
        try {
            newUser.setPassword(MD5Utils.getMD5Str(userBo.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.userMapper.insert(newUser);
        return ApiJsonResult.ok();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean checkUsername(String username) {
        User user = new User();
        user.setUsername(username);
        User res = this.userMapper.selectOne(user);
        return res != null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean checkEmail(String email) {
        User user = new User();
        user.setEmail(email);
        User res = this.userMapper.selectOne(user);
        return res != null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean checkPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        User res = this.userMapper.selectOne(user);
        return res != null;
    }

    public String getSecretKey(User user) {
        String toKey = user.getUsername() + user.getPassword() + new Date() + "#_e%^Z@Q(w#";
        //redis中的key
        String cacheKey = "user:" + user.getId() + "_" + user.getUsername();
        String secretKey = null;
        try {
            secretKey = MD5Utils.getMD5Str(toKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return secretKey;
    }

}
