package cloud.dao;

import cloud.entity.User;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectBySelective(User user);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}