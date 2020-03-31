package yx.graduation.elec.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
public class Device {
    @Id
    private String id;

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
     * 所属用户
     */
    private String username;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}