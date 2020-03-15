package ccu.iot.cloud.service.impl;

import ccu.iot.cloud.dao.PublishRecordDao;
import ccu.iot.cloud.entity.MqttMessage;
import ccu.iot.cloud.entity.PublishRecord;
import ccu.iot.cloud.service.PublishRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PublishRecordServiceImpl implements PublishRecordService {

    @Autowired
    private PublishRecordDao publishRecordDao;

    @Override
    public List<PublishRecord> queryAll() {
        List<PublishRecord> publishRecordList = this.publishRecordDao.selectBySelective(new PublishRecord());
        return publishRecordList;
    }

    @Override
    public List<PublishRecord> queryByTime(Date start, Date end) {
        List<PublishRecord> publishRecords = this.publishRecordDao.selectByTimeLimit(start, end);
        return publishRecords;
    }

    @Override
    public List<PublishRecord> queryByTopic(String topic) {
        List<PublishRecord> publishRecords = this.publishRecordDao.selectPubByVague(topic);
        return publishRecords;
    }
}
