package ccuiot.iotc.service;

import ccuiot.iotc.pojo.ClientTopicInfo;

import java.util.List;

public interface MqttClientService {
    void setClientInfo(String clientId, String clientName, String remark);

    List<ClientTopicInfo> queryCurrentSubscriptions();

}
