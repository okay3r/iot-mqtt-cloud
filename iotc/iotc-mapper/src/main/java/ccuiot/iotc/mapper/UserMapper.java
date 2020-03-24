package ccuiot.iotc.mapper;

import ccuiot.iotc.my.mapper.MyMapper;
import ccuiot.iotc.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends MyMapper<User> {
}