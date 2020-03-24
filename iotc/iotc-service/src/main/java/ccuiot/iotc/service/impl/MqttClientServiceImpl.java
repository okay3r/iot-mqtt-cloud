package ccuiot.iotc.service.impl;

import ccuiot.iotc.http.HttpClientService;
import ccuiot.iotc.mapper.ClientTopicInfoMapper;
import ccuiot.iotc.pojo.ClientTopicInfo;
import ccuiot.iotc.service.MqttClientService;
import ccuiot.iotc.utils.RedisUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MqttClientServiceImpl extends BaseService implements MqttClientService {

    @Autowired
    private ClientTopicInfoMapper clientTopicInfoMapper;

    @Autowired
    private HttpClientService httpClientService;

    @Autowired
    private RedisUtils redisUtils;

    @Value("${redis.cli.info.name.key}")
    private String cliInfoNameKey;

    @Value("${redis.cli.info.remark.key}")
    private String cliInfoRemarkKey;

    @Override
    public void setClientInfo(String clientId, String clientName, String remark) {
        this.redisUtils.hset(cliInfoNameKey, clientId, clientName);
        this.redisUtils.hset(cliInfoRemarkKey, clientId, remark);
    }

    @Override
    public List<ClientTopicInfo> queryCurrentSubscriptions() {
        String url = baseUrl + "nodes/" + node + "/" + "subscriptions";
        String httpRes = null;
        try {
            httpRes = this.httpClientService.doGet(url, null, headers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSON.parseObject(httpRes);
        String dataJson = jsonObject.getString("data");

        //从emqx获取到当前在线的client列表
        List<ClientTopicInfo> clientTopicInfos = JSON.parseArray(dataJson, ClientTopicInfo.class);

        for (ClientTopicInfo currentClient : clientTopicInfos) {
            String cliName = (String) this.redisUtils.hget(cliInfoNameKey, currentClient.getClientId());
            String cliRemark = (String) this.redisUtils.hget(cliInfoRemarkKey, currentClient.getClientId());
            currentClient.setClientName(cliName);
            currentClient.setRemark(cliRemark);
        }
        return clientTopicInfos;
    }
}
