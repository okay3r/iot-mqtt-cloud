package elec.cloud.service;

import elec.cloud.entity.User;
import elec.cloud.result.Result;
import elec.cloud.result.SignInAck;

public interface UserService {

    Result<SignInAck> signIn(String username, String password);

    Result<SignInAck> signUp(User user);
}
