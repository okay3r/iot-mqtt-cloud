package ccu.iot.cloud.service.impl;

import ccu.iot.cloud.dao.MqttMessageDao;
import ccu.iot.cloud.entity.MqttMessage;
import ccu.iot.cloud.service.MqttMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MqttMessageServiceImpl implements MqttMessageService {

    @Autowired
    private MqttMessageDao mqttMessageDao;

    @Override
    public List<MqttMessage> queryAll() {
        MqttMessage mqttMessage = new MqttMessage();
        return this.mqttMessageDao.selectBySelective(mqttMessage);
    }

    @Override
    public List<MqttMessage> queryByTime(Date start, Date end) {
        List<MqttMessage> mqttMessageList = this.mqttMessageDao.selectByTimeLimit(start, end);
        return mqttMessageList;
    }

    @Override
    public List<MqttMessage> queryByTopic(String topic) {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setTopic(topic);
        List<MqttMessage> mqttMessageList = this.mqttMessageDao.selectMsgByVague(topic);
        return mqttMessageList;
    }

}
