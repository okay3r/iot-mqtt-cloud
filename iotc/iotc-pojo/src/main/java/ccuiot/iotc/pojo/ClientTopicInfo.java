package ccuiot.iotc.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "client_topic_info")
public class ClientTopicInfo {
    @Id
    private Long id;

    /**
     * 客户端id
     */
    @Column(name = "client_id")
    private String clientId;

    /**
     * mqtt节点
     */
    private String node;

    /**
     * 主题
     */
    private String topic;

    /**
     * qos
     */
    private Integer qos;

    /**
     * 客户端名称
     */
    @Column(name = "client_name")
    private String clientName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;
}