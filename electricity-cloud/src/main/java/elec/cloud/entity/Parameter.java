package elec.cloud.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 设备的参数，例如温度、电压
 * parameter
 * @author 
 */
@Data
public class Parameter implements Serializable {
    private Integer id;

    private String parameterName;

    private String parameterUnit;

    private static final long serialVersionUID = 1L;
}