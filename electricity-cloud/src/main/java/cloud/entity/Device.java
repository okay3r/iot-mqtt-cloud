package cloud.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * device
 * @author 
 */
public class Device implements Serializable {
    private Integer id;

    private String deviceName;

    private Integer categoryId;

    private Integer userId;

    private Boolean using;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Device() {
    }

    public Device(Integer id, String deviceName, Integer categoryId, Integer userId, Boolean using, Date createTime, Date updateTime) {
        this.id = id;
        this.deviceName = deviceName;
        this.categoryId = categoryId;
        this.userId = userId;
        this.using = using;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", deviceName='" + deviceName + '\'' +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                ", using=" + using +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getUsing() {
        return using;
    }

    public void setUsing(Boolean using) {
        this.using = using;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}