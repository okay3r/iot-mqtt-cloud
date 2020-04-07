package ccuiot.iotc.controller;

import ccuiot.iotc.pojo.bo.UserBo;
import ccuiot.iotc.service.UserService;
import ccuiot.iotc.utils.ApiJsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "注册登录", tags = "注册登录相关接口")
@RestController
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     */
    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public ApiJsonResult login(
            @RequestParam String username,
            @RequestParam String password
    ) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return ApiJsonResult.errorMsg("用户名或密码不能为空");
        }
        String secretKey = this.userService.queryUserForLogin(username, password);
        if (StringUtils.isBlank(secretKey)) {
            return ApiJsonResult.errorMsg("用户名或密码错误");
        }
        return ApiJsonResult.ok(secretKey);
    }

    /**
     * 用户注册
     */
    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/register")
    public ApiJsonResult login(@RequestBody UserBo userBo) {
        if (StringUtils.isBlank(userBo.getUsername())
                || StringUtils.isBlank(userBo.getPassword())
                || StringUtils.isBlank(userBo.getEmail())
                || StringUtils.isBlank(userBo.getPhone())
                || StringUtils.isBlank(userBo.getRemark())
        ) {
            return ApiJsonResult.errorMsg("参数不全");
        }
        ApiJsonResult registerResult = this.userService.createUser(userBo);
        return registerResult;
    }

}
