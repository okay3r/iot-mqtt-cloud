package ccuiot.iotc.service;

import ccuiot.iotc.pojo.MqttSub;
import ccuiot.iotc.utils.PagedGridResult;

import java.util.Date;

public interface MqttSubService {
    void storeSubMsg(MqttSub mqttSub);

    PagedGridResult list(Integer page, Integer pageSize);

    PagedGridResult queryByTime(Date start, Date end, Integer page, Integer pageSize);

    PagedGridResult queryByTopic(String topic, Integer page, Integer pageSize);
}
