package ccu.iot.cloud.service.impl;

import ccu.iot.cloud.entity.MqttSub;
import ccu.iot.cloud.mapper.MqttSubMapper;
import ccu.iot.cloud.service.MqttSubService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MqttSubServiceImpl implements MqttSubService {

    @Autowired
    private MqttSubMapper mqttSubMapper;

    @Override
    public List<MqttSub> queryAll() {
        QueryWrapper<MqttSub> queryWrapper = new QueryWrapper<>();
        return this.mqttSubMapper.selectList(queryWrapper);
    }

    @Override
    public List<MqttSub> queryByTime(Date start, Date end) {
        QueryWrapper<MqttSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("time", start, end);
        List<MqttSub> mqttMessageList = this.mqttSubMapper.selectList(queryWrapper);
        return mqttMessageList;
    }

    @Override
    public List<MqttSub> queryByTopic(String topic) {

        QueryWrapper<MqttSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("topic", topic);
        List<MqttSub> selectList = this.mqttSubMapper.selectList(queryWrapper);
        return selectList;
    }

}
