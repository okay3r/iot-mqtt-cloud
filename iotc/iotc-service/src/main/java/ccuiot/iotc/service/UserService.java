package ccuiot.iotc.service;

import ccuiot.iotc.pojo.bo.UserBo;
import ccuiot.iotc.utils.ApiJsonResult;

public interface UserService {

    String queryUserForLogin(String username, String password);

    ApiJsonResult userRegister(UserBo userBo);

    Boolean checkUsername(String username);

    Boolean checkEmail(String email);

    Boolean checkPhone(String phone);

}
