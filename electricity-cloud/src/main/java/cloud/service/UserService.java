package cloud.service;

import cloud.entity.User;
import cloud.result.Result;
import cloud.result.SignInAck;

public interface UserService {

    Result<SignInAck> signIn(String username, String password);

    Result<SignInAck> signUp(User user);
}
