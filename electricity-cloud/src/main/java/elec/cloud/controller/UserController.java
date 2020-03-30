package elec.cloud.controller;

import elec.cloud.entity.User;
import elec.cloud.result.Result;
import elec.cloud.result.SignInAck;
import elec.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signIn")
    public Result<SignInAck> signIn(String username, String password) {
        Result<SignInAck> result = this.userService.signIn(username, password);
        return result;
    }

    @PostMapping("signUp")
    public Result<SignInAck> signUp(User user) {
        Result<SignInAck> result = this.userService.signUp(user);
        return result;
    }
}
