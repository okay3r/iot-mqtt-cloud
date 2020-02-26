package ccu.iot.cloud.entity;

public class MqttTopic {
    private Integer id;
    private String topic;
    private String host;
    private Integer port;
    private Integer qos;

    public MqttTopic() {
    }

    public MqttTopic(Integer id, String topic, String host, Integer port, Integer qos) {
        this.id = id;
        this.topic = topic;
        this.host = host;
        this.port = port;
        this.qos = qos;
    }

    @Override
    public String toString() {
        return "MqttTopic{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", qos=" + qos +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getQos() {
        return qos;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }
}
