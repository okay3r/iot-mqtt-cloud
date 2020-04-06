package ccuiot.iotc.service.impl;

import ccuiot.iotc.enums.RedisKeyEnum;
import ccuiot.iotc.http.HttpClientService;
import ccuiot.iotc.mapper.ClientTopicInfoMapper;
import ccuiot.iotc.pojo.ClientTopicInfo;
import ccuiot.iotc.service.MqttClientService;
import ccuiot.iotc.utils.RedisOperation;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MqttClientServiceImpl extends BaseService implements MqttClientService {

    @Autowired
    private ClientTopicInfoMapper clientTopicInfoMapper;

    @Autowired
    private HttpClientService httpClientService;

    @Autowired
    private RedisOperation redisOperation;

    private String cliInfoNameKey = RedisKeyEnum.CLIENT_INFO_NAME.value;

    private String cliInfoRemarkKey = RedisKeyEnum.CLIENT_INFO_REMARK.value;

    /**
     * 设置客户端信息
     */
    @Override
    public void setClientInfo(String clientId, String clientName, String remark) {
        this.redisOperation.hset(cliInfoNameKey, clientId, clientName);
        this.redisOperation.hset(cliInfoRemarkKey, clientId, remark);
    }

    /**
     * 查询当前在线的设备
     */
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
            String cliName = (String) this.redisOperation.hget(cliInfoNameKey, currentClient.getClientId());
            String cliRemark = (String) this.redisOperation.hget(cliInfoRemarkKey, currentClient.getClientId());
            currentClient.setClientName(cliName);
            currentClient.setRemark(cliRemark);
        }
        return clientTopicInfos;
    }
}
