package yx.graduation.elec.service;

import yx.graduation.elec.pojo.User;
import yx.graduation.elec.pojo.bo.UserBo;
import yx.graduation.utils.ApiJsonResult;

public interface UserService {

    ApiJsonResult createUser(UserBo userBo);

    User queryForLogin(String username, String password);

    User queryUserByName(String username);

}
