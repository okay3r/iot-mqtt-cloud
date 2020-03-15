package ccu.iot.cloud.entity.mqttapi;

public class PublishEntity {
    private String topic;
    private String payload;
    private Integer qos;
    private Boolean retain;
    private String client_id;

    public PublishEntity() {
    }

    public PublishEntity(String topic, String payload, Integer qos, Boolean retain, String client_id) {
        this.topic = topic;
        this.payload = payload;
        this.qos = qos;
        this.retain = retain;
        this.client_id = client_id;
    }

    @Override
    public String toString() {
        return "PublishEntity{" +
                "topic='" + topic + '\'' +
                ", payload='" + payload + '\'' +
                ", qos=" + qos +
                ", retain=" + retain +
                ", client_id='" + client_id + '\'' +
                '}';
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Integer getQos() {
        return qos;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }

    public Boolean getRetain() {
        return retain;
    }

    public void setRetain(Boolean retain) {
        this.retain = retain;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
}
