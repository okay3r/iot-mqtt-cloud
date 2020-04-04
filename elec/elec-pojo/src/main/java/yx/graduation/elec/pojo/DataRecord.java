package yx.graduation.elec.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "data_record")
public class DataRecord {
    @Id
    private Long id;

    /**
     * ip
     */
    private String host;

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


}