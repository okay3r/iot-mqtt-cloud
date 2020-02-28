package ccu.iot.cloud.service;

import ccu.iot.cloud.result.BoolResult;
import ccu.iot.cloud.result.SignInAck;
import ccu.iot.cloud.entity.User;

public interface UserService {

    SignInAck checkSignIn(User user);

    BoolResult signUp(User user);
}
