package ccu.iot.cloud.service.impl;

import ccu.iot.cloud.mapper.UserMapper;
import ccu.iot.cloud.result.BoolResult;
import ccu.iot.cloud.result.SignInAck;
import ccu.iot.cloud.entity.User;
import ccu.iot.cloud.redis.RedisUtils;
import ccu.iot.cloud.service.UserService;
import ccu.iot.cloud.utils.UserUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public SignInAck checkSignIn(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        User selectOne = this.userMapper.selectOne(queryWrapper);
        SignInAck signInAck = null;
        if (selectOne != null) {
            signInAck = UserUtils.getAck(selectOne);
            this.redisUtils.set(signInAck.getCacheKey(), signInAck.getSecretKey(), 1800);
        }
        return signInAck;
    }

    @Override
    public BoolResult signUp(User user) {
        User forCheck = new User();
        forCheck.setUsername(user.getUsername());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        //验证用户名
        queryWrapper.setEntity(forCheck);
        boolean hasUserName = this.userMapper.selectOne(queryWrapper) != null;
        if (hasUserName) {
            return new BoolResult(false, "username already exists", null);
        }

        //验证email
        forCheck.setUsername(null);
        forCheck.setEmail(user.getEmail());
        boolean hasEmail = this.userMapper.selectOne(queryWrapper) != null;
        if (hasEmail) {
            return new BoolResult(false, "email already exists", null);
        }

        //验证phone
        forCheck.setEmail(null);
        forCheck.setPhone(user.getPhone());
        boolean hasPhone = this.userMapper.selectOne(queryWrapper) != null;
        if (hasPhone) {
            return new BoolResult(false, "phone already exists", null);
        }

        //通过以上验证，插入新user
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setIsAdmin(false);
        int insert = this.userMapper.insert(user);
        if (insert == 0) {
            return new BoolResult(false, "insert error", null);
        }
        return new BoolResult(true, "sign up success", null);
    }
}
