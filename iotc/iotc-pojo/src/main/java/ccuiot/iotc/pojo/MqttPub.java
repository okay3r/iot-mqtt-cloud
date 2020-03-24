package ccuiot.iotc.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "mqtt_pub")
public class MqttPub {
    @Id
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 主题
     */
    private String topic;

    /**
     * 消息
     */
    private String payload;

    /**
     * qos
     */
    private Integer qos;

    /**
     * 是否成功 1是，0否
     */
    private Integer success;

    /**
     * 时间
     */
    private Date time;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取主题
     *
     * @return topic - 主题
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 设置主题
     *
     * @param topic 主题
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * 获取消息
     *
     * @return payload - 消息
     */
    public String getPayload() {
        return payload;
    }

    /**
     * 设置消息
     *
     * @param payload 消息
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }

    /**
     * 获取qos
     *
     * @return qos - qos
     */
    public Integer getQos() {
        return qos;
    }

    /**
     * 设置qos
     *
     * @param qos qos
     */
    public void setQos(Integer qos) {
        this.qos = qos;
    }

    /**
     * 获取是否成功 1是，0否
     *
     * @return success - 是否成功 1是，0否
     */
    public Integer getSuccess() {
        return success;
    }

    /**
     * 设置是否成功 1是，0否
     *
     * @param success 是否成功 1是，0否
     */
    public void setSuccess(Integer success) {
        this.success = success;
    }

    /**
     * 获取时间
     *
     * @return time - 时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置时间
     *
     * @param time 时间
     */
    public void setTime(Date time) {
        this.time = time;
    }
}