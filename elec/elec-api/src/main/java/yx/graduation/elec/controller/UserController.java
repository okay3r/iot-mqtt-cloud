package yx.graduation.elec.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yx.graduation.elec.pojo.User;
import yx.graduation.elec.pojo.bo.UserBo;
import yx.graduation.elec.service.UserService;
import yx.graduation.utils.ApiJsonResult;
import yx.graduation.utils.MD5Utils;
import yx.graduation.utils.RedisOperator;

import java.util.Date;


@Api(value = "用户", tags = "用户相关接口")
@RestController
@RequestMapping("/passport")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisOperator redisOperator;

    /**
     * 注册新用户
     */
    @ApiOperation(value = "注册新用户", notes = "注册新用户", httpMethod = "POST")
    @PostMapping("/regist")
    public ApiJsonResult register(@RequestBody UserBo userBo) {
        ApiJsonResult result = this.userService.createUser(userBo);
        return result;
    }

    /**
     * 用户登录
     */
    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public ApiJsonResult login(
            @RequestParam String username,
            @RequestParam String password) throws Exception {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return ApiJsonResult.errorMsg("用户名或密码不能为空");
        }
        User user = this.userService.queryForLogin(username, password);
        if (user == null) {
            return ApiJsonResult.errorMsg("用户名或密码不正确");
        }
        String secret = MD5Utils.getMD5Str(username + password + new Date() + "zu#$_a");
        this.redisOperator.set(USER_LOGIN_KEY + ":" + username, secret, 1800);
        return ApiJsonResult.ok(secret);
    }


}
