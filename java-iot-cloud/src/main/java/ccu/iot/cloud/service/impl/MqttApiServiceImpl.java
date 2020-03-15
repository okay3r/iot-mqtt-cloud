package ccu.iot.cloud.service.impl;

import ccu.iot.cloud.dao.PublishRecordDao;
import ccu.iot.cloud.entity.PublishRecord;
import ccu.iot.cloud.entity.mqttapi.CurrentMqttSub;
import ccu.iot.cloud.entity.mqttapi.PublishEntity;
import ccu.iot.cloud.http.HttpClientService;
import ccu.iot.cloud.http.HttpResult;
import ccu.iot.cloud.service.MqttApiService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@PropertySource("classpath:properties/http.properties")
public class MqttApiServiceImpl implements MqttApiService {

    @Autowired
    private HttpClientService httpClientService;

    private String key;

    @Value("${emqx.api.appId}")
    private String appId;

    @Value("${emqx.api.pwd}")
    private String pwd;

    @Value("${emqx.api.baseUrl}")
    private String baseUrl;

    @Value("${emqx.api.node}")
    private String node;

    @Autowired
    private PublishRecordDao publishRecordDao;

    Map<String, String> headers = new HashMap<>();

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void init() {
        key = Base64.getEncoder().encodeToString((appId + ":" + pwd).getBytes());
        key = "Basic " + key;
        headers.put("Authorization", key);
    }


    @Override
    public List<CurrentMqttSub> queryCurrentSubscriptions() throws Exception {
        String url = baseUrl + "nodes/" + node + "/" + "subscriptions";
        String httpRes = this.httpClientService.doGet(url, null, headers);
        JSONObject jsonObject = JSON.parseObject(httpRes);
        String dataJson = jsonObject.getString("data");
        List<CurrentMqttSub> currentMqttSubs = JSON.parseArray(dataJson, CurrentMqttSub.class);
        return currentMqttSubs;
    }

    @Override
    public Boolean publish(String username, PublishEntity publishEntity) {
        publishEntity.setRetain(false);
        publishEntity.setClient_id(username);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("topic", publishEntity.getTopic());
        paramMap.put("payload", publishEntity.getPayload());
        paramMap.put("qos", publishEntity.getQos());
        paramMap.put("retain", publishEntity.getRetain());
        paramMap.put("client_id", publishEntity.getClient_id());
        String url = baseUrl + "mqtt/publish";
        HttpResult httpResult = null;
        try {
            httpResult = this.httpClientService.doPost(url, paramMap, headers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Boolean success = httpResult != null && httpResult.getCode() == 200;

        //TODO 抽离为异步存储到数据库
        PublishRecord publishRecord = new PublishRecord();
        publishRecord.setTopic(publishEntity.getTopic());
        publishRecord.setPayload(publishEntity.getPayload());
        publishRecord.setQos(publishEntity.getQos());
        publishRecord.setIsSuccess(success);
        publishRecord.setUsername(username);
        publishRecord.setTime(new Date());
        this.publishRecordDao.insert(publishRecord);

        return success;
    }
}
