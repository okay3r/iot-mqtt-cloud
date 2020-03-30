package elec.cloud.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 设备分类
 * category
 *
 * @author
 */
@Data
public class Category implements Serializable {
    private Integer id;

    private String categoryName;

    private String categoryRemark;

    private static final long serialVersionUID = 1L;

}