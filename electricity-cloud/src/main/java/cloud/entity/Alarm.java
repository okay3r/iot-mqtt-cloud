package cloud.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 报警值
 * alarm
 * @author 
 */
@Data
public class Alarm implements Serializable {
    private Integer id;

    private Integer deviceId;

    private Integer parameterId;

    private Integer up;

    private Integer down;

    private static final long serialVersionUID = 1L;
}