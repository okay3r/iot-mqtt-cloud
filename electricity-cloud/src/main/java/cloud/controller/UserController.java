package cloud.controller;

import cloud.dao.UserDao;
import cloud.entity.User;
import cloud.result.Result;
import cloud.result.SignInAck;
import cloud.service.UserService;
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
}
