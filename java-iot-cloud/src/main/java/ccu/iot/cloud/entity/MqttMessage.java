package ccu.iot.cloud.entity;

import java.util.Date;

public class MqttMessage {
    private Long id;
    private String topic;
    private String msg;
    private Integer qos;
    private Date time;

    public MqttMessage() {
    }

    public MqttMessage(Long id, String topic, String msg, Integer qos, Date time) {
        this.id = id;
        this.topic = topic;
        this.msg = msg;
        this.qos = qos;
        this.time = time;
    }

    @Override
    public String toString() {
        return "MqttMessage{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", msg='" + msg + '\'' +
                ", qos=" + qos +
                ", time=" + time +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getQos() {
        return qos;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
