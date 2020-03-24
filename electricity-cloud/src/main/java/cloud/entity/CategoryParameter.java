package cloud.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 设备类别的参数
 * category_parameter
 * @author 
 */
@Data
public class CategoryParameter implements Serializable {
    private Integer id;

    private Integer categoryId;

    private Integer parameterId;

    private static final long serialVersionUID = 1L;
}