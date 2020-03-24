package ccuiot.iotc.service;

import ccuiot.iotc.pojo.bo.PublishBo;
import ccuiot.iotc.utils.PagedGridResult;

import java.util.Date;

public interface MqttPubService {
    Boolean doPublish(String cacheKey, PublishBo publishBo);

    PagedGridResult list(Integer page, Integer pageSize);

    PagedGridResult queryByTime(Date start, Date end, Integer page, Integer pageSize);

    PagedGridResult queryByTopic(String topic, Integer page, Integer pageSize);
}
