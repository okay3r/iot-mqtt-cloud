package elec.cloud.mapper;

import elec.cloud.entity.DeviceCategoryVo;
import elec.cloud.service.DeviceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private DeviceCategoryVoDao deviceCategoryVoDao;

    @Autowired
    private DeviceService deviceService;

    @Test
    public void test1() {
        User user = this.userDao.selectByPrimaryKey(9);
        System.out.println(user);
        Device device = this.deviceDao.selectByPrimaryKey(1);
        System.out.println(device);
    }

    @Test
    public void test2() {
        List<DeviceCategoryVo> voList = this.deviceService.queryDevice(9);
        for (DeviceCategoryVo vo : voList) {
            System.out.println(vo);
        }
    }

}
