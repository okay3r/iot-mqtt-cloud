package yx.graduation.elec.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

public class Alarm {
    @Id
    private Long id;

    /**
     * 设备id
     */
    @Column(name = "device_id")
    private String deviceId;

    /**
     * 设备名称
     */
    @Column(name = "device_name")
    private String deviceName;

    /**
     * 参数id
     */
    @Column(name = "param_id")
    private Long paramId;

    /**
     * 上限
     */
    private Integer up;

    /**
     * 下限
     */
    private Integer down;

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
     * 获取设备id
     *
     * @return device_id - 设备id
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备id
     *
     * @param deviceId 设备id
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取设备名称
     *
     * @return device_name - 设备名称
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * 设置设备名称
     *
     * @param deviceName 设备名称
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * 获取参数id
     *
     * @return param_id - 参数id
     */
    public Long getParamId() {
        return paramId;
    }

    /**
     * 设置参数id
     *
     * @param paramId 参数id
     */
    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    /**
     * 获取上限
     *
     * @return up - 上限
     */
    public Integer getUp() {
        return up;
    }

    /**
     * 设置上限
     *
     * @param up 上限
     */
    public void setUp(Integer up) {
        this.up = up;
    }

    /**
     * 获取下限
     *
     * @return down - 下限
     */
    public Integer getDown() {
        return down;
    }

    /**
     * 设置下限
     *
     * @param down 下限
     */
    public void setDown(Integer down) {
        this.down = down;
    }
}