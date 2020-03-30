package yx.graduation.elec.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import yx.graduation.elec.my.mapper.MyMapper;
import yx.graduation.elec.pojo.User;

@Repository
public interface UserMapper extends MyMapper<User> {
}