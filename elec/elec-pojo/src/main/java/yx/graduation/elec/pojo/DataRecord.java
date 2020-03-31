package yx.graduation.elec.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "data_record")
public class DataRecord {
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
     * 值
     */
    private Integer value;

    /**
     * 参数id
     */
    @Column(name = "param_id")
    private Long paramId;

    /**
     * 参数名称
     */
    @Column(name = "param_name")
    private String paramName;

    /**
     * 参数单位
     */
    @Column(name = "param_unit")
    private String paramUnit;

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
     * 获取值
     *
     * @return value - 值
     */
    public Integer getValue() {
        return value;
    }

    /**
     * 设置值
     *
     * @param value 值
     */
    public void setValue(Integer value) {
        this.value = value;
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
     * 获取参数名称
     *
     * @return param_name - 参数名称
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * 设置参数名称
     *
     * @param paramName 参数名称
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * 获取参数单位
     *
     * @return param_unit - 参数单位
     */
    public String getParamUnit() {
        return paramUnit;
    }

    /**
     * 设置参数单位
     *
     * @param paramUnit 参数单位
     */
    public void setParamUnit(String paramUnit) {
        this.paramUnit = paramUnit;
    }

    /**
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }
}