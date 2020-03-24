package ccuiot.iotc.handler;

import ccuiot.iotc.mapper.MqttSubMapper;
import ccuiot.iotc.pojo.MqttSub;
import ccuiot.iotc.service.MqttSubService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncMqttSubTransferHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MqttSubService mqttSubService;

    /***
     * 将从rabbit中获取到的消息存储到数据库
     * 异步执行
     * @param informationJson
     */
    @Async("transferTaskExecutor")
    public void handleMsg(String informationJson) {
        MqttSub mqttSub = JSON.parseObject(informationJson, MqttSub.class);
        this.mqttSubService.storeSubMsg(mqttSub);
    }
}
