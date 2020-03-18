package ccu.iot.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class ClientTopicInfo {

    @TableId(type= IdType.AUTO)
    private Long id;

    private String clientId;

    private String node;

    private String topic;

    private Integer qos;

    private String clientName;

    private String remark;

    private Date updateTime;
}
