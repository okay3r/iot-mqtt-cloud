package yx.graduation.elec.mapper;

import org.apache.ibatis.annotations.Param;
import yx.graduation.elec.my.mapper.MyMapper;
import yx.graduation.elec.pojo.Category;
import yx.graduation.elec.pojo.vo.CategoryVo;

public interface CategoryMapper extends MyMapper<Category> {

    Long insertGetKey(@Param("category") Category category);

    CategoryVo queryCategoryParamCompose(Long categoryId);
}