package elec.cloud.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 数据历史记录
 * data_record
 * @author 
 */
@Data
public class DataRecord implements Serializable {
    private Integer id;

    private Integer deviceId;

    private Integer value;

    private Integer parameterId;

    private Date time;

    private static final long serialVersionUID = 1L;
}