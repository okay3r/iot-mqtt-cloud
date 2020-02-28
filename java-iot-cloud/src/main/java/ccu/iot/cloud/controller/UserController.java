package ccu.iot.cloud.controller;


import ccu.iot.cloud.result.BoolResult;
import ccu.iot.cloud.result.SignInAck;
import ccu.iot.cloud.result.Result;
import ccu.iot.cloud.entity.User;
import ccu.iot.cloud.service.UserService;
import ccu.iot.cloud.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO 修改信息
@RestController
@RequestMapping("/user")
public class UserController {

    // private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @PostMapping("/signIn")
    public Result<SignInAck> signIn(User user) {
        // logger.info("signIn # " + user);
        SignInAck signInAck = this.userService.checkSignIn(user);
        Result<SignInAck> result = null;
        if (signInAck == null) {
            result = new Result<>(HttpStatus.BAD_REQUEST.value(), "Username or password is incorrect", null);
        } else {
            result = new Result<>(HttpStatus.OK.value(), "SignIn success", signInAck);
        }
        return result;
    }

    @PostMapping("/signUp")
    public Result<?> signUp(User user) {
        // logger.info("signUp # " + user);
        Boolean complete = UserUtils.checkComplete(user);
        if (!complete) {
            return new Result(HttpStatus.BAD_REQUEST.value(), "Data format is incorrect", null);
        }
        BoolResult boolResult = this.userService.signUp(user);
        if (!boolResult.getOk()) {
            return new Result<>(HttpStatus.BAD_REQUEST.value(), boolResult.getMsg(), null);
        }
        return new Result<>(HttpStatus.OK.value(), boolResult.getMsg(), null);
    }

}
