package ccuiot.iotc.service.impl;

import ccuiot.iotc.http.HttpClientService;
import ccuiot.iotc.http.HttpResult;
import ccuiot.iotc.mapper.MqttPubMapper;
import ccuiot.iotc.pojo.MqttPub;
import ccuiot.iotc.pojo.bo.PublishBo;
import ccuiot.iotc.service.MqttPubService;
import ccuiot.iotc.utils.PagedGridResult;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class MqttPubServiceImpl extends BaseService implements MqttPubService {

    @Autowired
    private HttpClientService httpClientService;

    @Autowired
    private MqttPubMapper mqttPubMapper;

    /**
     * 使用http发送消息到mqtt服务器
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Boolean doPublish(String username, PublishBo publishBo) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("topic", publishBo.getTopic());
        paramsMap.put("payload", publishBo.getPayload());
        paramsMap.put("qos", publishBo.getQos());
        paramsMap.put("retain", false);
        paramsMap.put("client_id", username);
        String url = baseUrl + "mqtt/publish";
        HttpResult httpResult = null;
        try {
            httpResult = this.httpClientService.doPost(url, paramsMap, headers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Boolean success = httpResult != null && httpResult.getCode() == 200;
        MqttPub mqttPub = new MqttPub();
        BeanUtils.copyProperties(publishBo, mqttPub);
        mqttPub.setUsername(username);
        mqttPub.setSuccess(success ? 1 : 0);
        mqttPub.setTime(new Date());

        this.mqttPubMapper.insert(mqttPub);

        return success;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult list(Integer page, Integer pageSize) {

        PageHelper.startPage(page, pageSize);

        List<MqttPub> mqttPubList = this.mqttPubMapper.selectAll();

        return setPagedGrid(mqttPubList, page);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryByTime(Date start, Date end, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        Example example = new Example(MqttPub.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andBetween("time", start, end);
        List<MqttPub> mqttPubList = this.mqttPubMapper.selectByExample(example);
        return setPagedGrid(mqttPubList, page);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryByTopic(String topic, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        Example example = new Example(MqttPub.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("topic", topic + "%");
        List<MqttPub> mqttPubList = this.mqttPubMapper.selectByExample(example);
        return setPagedGrid(mqttPubList, page);
    }
}
