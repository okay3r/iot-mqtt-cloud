package cloud.dao;

import cloud.entity.CategoryParameter;

public interface CategoryParameterDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CategoryParameter record);

    int insertSelective(CategoryParameter record);

    CategoryParameter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CategoryParameter record);

    int updateByPrimaryKey(CategoryParameter record);
}