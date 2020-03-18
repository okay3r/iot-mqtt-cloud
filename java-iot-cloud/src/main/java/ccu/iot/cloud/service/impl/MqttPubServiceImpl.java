package ccu.iot.cloud.service.impl;

import ccu.iot.cloud.entity.MqttPub;
import ccu.iot.cloud.mapper.MqttPubMapper;
import ccu.iot.cloud.service.MqttPubService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MqttPubServiceImpl implements MqttPubService {

    @Autowired
    private MqttPubMapper mqttPubMapper;

    @Override
    public List<MqttPub> queryAll() {
        QueryWrapper<MqttPub> queryWrapper = new QueryWrapper<>();
        List<MqttPub> mqttPubList = this.mqttPubMapper.selectList(queryWrapper);
        return mqttPubList;
    }

    @Override
    public List<MqttPub> queryByTime(Date start, Date end) {
        QueryWrapper<MqttPub> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("time", start, end);
        List<MqttPub> mqttPubList = this.mqttPubMapper.selectList(queryWrapper);
        return mqttPubList;
    }

    @Override
    public List<MqttPub> queryByTopic(String topic) {
        QueryWrapper<MqttPub> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("topic", topic);
        List<MqttPub> mqttPubList = this.mqttPubMapper.selectList(queryWrapper);
        return mqttPubList;
    }
}
