package ccu.iot.cloud.service;

import ccu.iot.cloud.entity.PublishRecord;

import java.util.Date;
import java.util.List;

public interface PublishRecordService {
    List<PublishRecord> queryAll();

    List<PublishRecord> queryByTime(Date start, Date end);

    List<PublishRecord> queryByTopic(String topic);
}
