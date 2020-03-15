package ccu.iot.cloud.entity.mqttapi;

public class CurrentMqttSub {
    private String client_id;
    private String node;
    private Integer qos;
    private String topic;

    public CurrentMqttSub() {
    }

    public CurrentMqttSub(String client_id, String node, Integer qos, String topic) {
        this.client_id = client_id;
        this.node = node;
        this.qos = qos;
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "CurrentMqttNode{" +
                "client_id='" + client_id + '\'' +
                ", node='" + node + '\'' +
                ", qos=" + qos +
                ", topic='" + topic + '\'' +
                '}';
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Integer getQos() {
        return qos;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
