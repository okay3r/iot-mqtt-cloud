package cloud.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 设备类
 * device
 * @author 
 */
@Data
public class Device implements Serializable {
    private Integer id;

    private String deviceName;

    private Integer categoryId;

    private Integer userId;

    // private Boolean online;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}