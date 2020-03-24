package cloud.entity;

import java.io.Serializable;

import cloud.mapper.CategoryMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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