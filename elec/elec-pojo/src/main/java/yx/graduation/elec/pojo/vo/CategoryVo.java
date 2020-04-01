package yx.graduation.elec.pojo.vo;

import lombok.Data;
import org.apache.ibatis.annotations.Param;
import yx.graduation.elec.pojo.Parameter;

import javax.persistence.Column;
import java.util.List;

@Data
public class CategoryVo {

    private String categoryName;

    private String categoryRemark;

    private List<ParameterVo> parameterVoList;

}
