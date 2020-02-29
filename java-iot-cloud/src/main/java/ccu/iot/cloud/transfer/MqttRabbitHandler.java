package ccu.iot.cloud.transfer;


import ccu.iot.cloud.dao.MqttMessageDao;
import ccu.iot.cloud.entity.MqttMessage;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MqttRabbitHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MqttMessageDao mqttMessageDao;

    @Async("transferTaskExecutor")
    public void handleMsg(String informationJson) {
        MqttMessage mqttMessage = JSON.parseObject(informationJson, MqttMessage.class);
        int lines = this.mqttMessageDao.insert(mqttMessage);
        if (lines != 0) {
            logger.info("rabbit # store # " + mqttMessage);
        } else {
            logger.error("rabbit # store # error # " + mqttMessage);
        }

    }
}
