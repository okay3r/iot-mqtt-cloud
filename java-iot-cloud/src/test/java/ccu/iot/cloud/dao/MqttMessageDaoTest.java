package ccu.iot.cloud.dao;

import ccu.iot.cloud.entity.MqttMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MqttMessageDaoTest {

    @Autowired
    private MqttMessageDao mqttMessageDao;

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
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setTopic("asd");
        mqttMessage.setQos(1);
        List<MqttMessage> mqttMessages = this.mqttMessageDao.selectBySelective(mqttMessage);
        System.out.println(mqttMessages.size());
    }

    @Test
    public void selectBySelective() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}