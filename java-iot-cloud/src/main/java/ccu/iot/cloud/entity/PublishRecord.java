package ccu.iot.cloud.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * publish_record
 * @author 
 */
public class PublishRecord implements Serializable {
    private Long id;

    private String username;

    private String topic;

    private String payload;

    /**
     * 0失败  1成功
     */
    private Boolean isSuccess;

    private Integer qos;

    private Date time;

    private static final long serialVersionUID = 1L;

    public PublishRecord() {
    }

    public PublishRecord(Long id, String username, String topic, String payload, Boolean isSuccess, Integer qos, Date time) {
        this.id = id;
        this.username = username;
        this.topic = topic;
        this.payload = payload;
        this.isSuccess = isSuccess;
        this.qos = qos;
        this.time = time;
    }

    @Override
    public String toString() {
        return "PublishRecord{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", topic='" + topic + '\'' +
                ", payload='" + payload + '\'' +
                ", isSuccess=" + isSuccess +
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean success) {
        isSuccess = success;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}