package cloud.service.impl;

import cloud.dao.UserDao;
import cloud.entity.User;
import cloud.redis.RedisUtils;
import cloud.result.Result;
import cloud.result.SignInAck;
import cloud.service.UserService;
import cloud.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserDao userDao;

    @Override
    public Result<SignInAck> signIn(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user = this.userDao.selectBySelective(user);
        if (user == null) {
            return new Result(HttpStatus.BAD_REQUEST.value(), "用户名不存在", null);
        }
        if (!password.equals(user.getPassword())) {
            return new Result(HttpStatus.BAD_REQUEST.value(), "密码错误", null);
        }
        SignInAck signInAck = UserUtils.getAck(user);
        this.redisUtils.set(signInAck.getCacheKey(), signInAck.getSecretKey(), 1800);
        return new Result(HttpStatus.OK.value(), "登录成功", signInAck);
    }
}
