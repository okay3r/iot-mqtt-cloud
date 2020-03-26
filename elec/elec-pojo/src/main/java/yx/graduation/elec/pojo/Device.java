package yx.graduation.elec.pojo;

import java.util.Date;
import javax.persistence.*;

public class Device {
    @Id
    private Long id;

    /**
     * 设备名称
     */
    @Column(name = "device_name")
    private String deviceName;

    /**
     * 类别id
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 类别名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 所属用户id
     */
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取类别id
     *
     * @return category_id - 类别id
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 设置类别id
     *
     * @param categoryId 类别id
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取类别名称
     *
     * @return category_name - 类别名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置类别名称
     *
     * @param categoryName 类别名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 获取所属用户id
     *
     * @return user_id - 所属用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置所属用户id
     *
     * @param userId 所属用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}