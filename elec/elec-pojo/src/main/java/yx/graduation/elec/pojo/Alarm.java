package yx.graduation.elec.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
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
     * 参数名称
     */
    @Column(name = "param_name")
    private String paramName;

    /**
     * 上限
     */
    private Integer up;

    /**
     * 下限
     */
    private Integer down;


}