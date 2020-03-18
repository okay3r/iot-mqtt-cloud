package ccu.iot.cloud.async;

import ccu.iot.cloud.entity.ClientTopicInfo;
import ccu.iot.cloud.entity.MqttPub;
import ccu.iot.cloud.entity.mqttapi.PublishEntity;
import ccu.iot.cloud.mapper.ClientTopicInfoMapper;
import ccu.iot.cloud.mapper.MqttPubMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class AsyncMqttInfoStoreHandler {

    @Autowired
    private MqttPubMapper mqttPubMapper;

    @Autowired
    private ClientTopicInfoMapper clientTopicInfoMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /***
     * 异步将publish的信息存储到db中
     * @param username
     * @param publishEntity
     * @param success
     */
    @Async
    public void storePubInfo2Db(String username, PublishEntity publishEntity, Boolean success) {
        MqttPub mqttPub = new MqttPub();
        mqttPub.setTopic(publishEntity.getTopic());
        mqttPub.setUsername(username);
        mqttPub.setPayload(publishEntity.getPayload());
        mqttPub.setQos(publishEntity.getQos());
        mqttPub.setSuccess(success);
        mqttPub.setUsername(username);
        mqttPub.setTime(new Date());
        int insert = this.mqttPubMapper.insert(mqttPub);
        if (insert != 0) {
            logger.info("store to db success: " + mqttPub);
            return;
        }
        logger.info("store to db failed: " + mqttPub);
    }

    @Async
    public void storeCliInfo2Db(List<ClientTopicInfo> clientTopicInfoList) {
        for (ClientTopicInfo info : clientTopicInfoList) {
            int insert = this.clientTopicInfoMapper.insert(info);
            if (insert != 0) {
                logger.info("store to db success: " + info);
            }
            logger.info("store to db failed: " + info);
        }
    }
}
