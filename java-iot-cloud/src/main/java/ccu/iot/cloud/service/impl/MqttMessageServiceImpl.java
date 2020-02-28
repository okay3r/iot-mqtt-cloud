package ccu.iot.cloud.service.impl;

import ccu.iot.cloud.dao.MqttMessageDao;
import ccu.iot.cloud.entity.MqttMessage;
import ccu.iot.cloud.service.MqttMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MqttMessageServiceImpl implements MqttMessageService {

    @Autowired
    private MqttMessageDao mqttMessageDao;

    @Override
    public List<MqttMessage> queryMqttMsgHistory() {
        MqttMessage mqttMessage = new MqttMessage();
        return this.mqttMessageDao.selectBySelective(mqttMessage);
    }
}
