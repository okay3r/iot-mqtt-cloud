package ccuiot.iotc.service.impl;

import ccuiot.iotc.mapper.MqttSubMapper;
import ccuiot.iotc.pojo.MqttPub;
import ccuiot.iotc.pojo.MqttSub;
import ccuiot.iotc.service.MqttSubService;
import ccuiot.iotc.utils.PagedGridResult;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class MqttSubServiceImpl extends BaseService implements MqttSubService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MqttSubServiceImpl.class);

    @Autowired
    private MqttSubMapper mqttSubMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void storeSubMsg(MqttSub mqttSub) {
        int res = this.mqttSubMapper.insert(mqttSub);
        if (res != 1) {
            LOGGER.error("存储错误：" + mqttSub);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult list(Integer page, Integer pageSize) {

        PageHelper.startPage(page, pageSize);

        List<MqttSub> mqttSubList = this.mqttSubMapper.selectAll();

        return setPagedGrid(mqttSubList, page);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryByTime(Date start, Date end, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        Example example = new Example(MqttPub.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andBetween("time", start, end);
        List<MqttSub> mqttSubList = this.mqttSubMapper.selectByExample(example);
        return setPagedGrid(mqttSubList, page);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryByTopic(String topic, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        Example example = new Example(MqttPub.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("topic", topic + "%");
        List<MqttSub> mqttSubList = this.mqttSubMapper.selectByExample(example);
        return setPagedGrid(mqttSubList, page);
    }
}
