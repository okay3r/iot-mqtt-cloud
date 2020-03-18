package ccu.iot.cloud.mapper;

import ccu.iot.cloud.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
        User user = this.userMapper.selectById(2);
        System.out.println(user);
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void selectBySelective() {
        User user = new User();
        user.setUsername("mike");
        user.setPassword("123123");
        user.setEmail("123123@qq.com");
        List<User> users = this.userMapper.selectList(new QueryWrapper<>(user));
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
}