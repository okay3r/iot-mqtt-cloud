package ccu.iot.cloud.service.impl;

import ccu.iot.cloud.dao.UserDao;
import ccu.iot.cloud.result.BoolResult;
import ccu.iot.cloud.result.SignInAck;
import ccu.iot.cloud.entity.User;
import ccu.iot.cloud.redis.RedisUtils;
import ccu.iot.cloud.service.UserService;
import ccu.iot.cloud.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public SignInAck checkSignIn(User user) {
        List<User> users = this.userDao.selectBySelective(user);
        SignInAck signInAck = null;
        if (users.size() > 0) {
            user = users.get(0);
            signInAck = UserUtils.getAck(user);
            this.redisUtils.set(signInAck.getCacheKey(), signInAck.getSecretKey(), 1800);
        }
        return signInAck;
    }

    @Override
    public BoolResult signUp(User user) {
        User forCheck = new User();
        forCheck.setUsername(user.getUsername());
        boolean hasUserName = this.userDao.selectBySelective(forCheck).size() != 0;
        if (hasUserName) {
            return new BoolResult(false, "username already exists", null);
        }
        forCheck.setUsername(null);
        forCheck.setEmail(user.getEmail());
        boolean hasEmail = this.userDao.selectBySelective(forCheck).size() != 0;
        if (hasEmail) {
            return new BoolResult(false, "email already exists", null);
        }
        forCheck.setEmail(null);
        forCheck.setPhone(user.getPhone());
        boolean hasPhone = this.userDao.selectBySelective(forCheck).size() != 0;
        if (hasPhone) {
            return new BoolResult(false, "phone already exists", null);
        }
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setIsAdmin(false);
        int insert = this.userDao.insert(user);
        if (insert == 0) {
            return new BoolResult(false, "insert error", null);
        }
        return new BoolResult(true, "sign up success", null);
    }
}
