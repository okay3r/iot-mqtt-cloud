package yx.graduation.elec.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import yx.graduation.elec.mapper.CategoryMapper;
import yx.graduation.elec.mapper.CategoryParameterMapper;
import yx.graduation.elec.mapper.ParameterMapper;
import yx.graduation.elec.pojo.Category;
import yx.graduation.elec.pojo.CategoryParameter;
import yx.graduation.elec.pojo.Parameter;
import yx.graduation.elec.pojo.vo.CategoryVo;
import yx.graduation.elec.service.CategoryService;
import yx.graduation.elec.service.ParameterService;
import yx.graduation.utils.ApiJsonResult;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ParameterMapper parameterMapper;

    @Autowired
    private CategoryParameterMapper categoryParameterMapper;

    /**
     * 添加新的类别
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ApiJsonResult add(String categoryName, String categoryRemark, String params) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        if (this.categoryMapper.selectOne(category) != null) {
            return ApiJsonResult.errorMsg("该类别已经存在");
        }
        category.setCategoryRemark(categoryRemark);
        int res = this.categoryMapper.insert(category);
        if (res != 1) {
            return ApiJsonResult.errorMsg("添加失败，请检查参数");
        }
        String[] paramList = params.split(",");
        for (String param : paramList) {
            long paramId = Long.parseLong(param);
            Parameter parameter = this.parameterMapper.selectByPrimaryKey(paramId);
            if (parameter == null) {
                return ApiJsonResult.errorMsg("参数id：" + param + " 不存在");
            }
            CategoryParameter cp = new CategoryParameter();
            cp.setParamId(paramId);
            cp.setParamName(parameter.getParameterName());
            cp.setCategoryId(category.getId());
            cp.setCategoryName(categoryName);

            int insertCP = this.categoryParameterMapper.insert(cp);
            if (insertCP != 1) {
                return ApiJsonResult.errorMsg("添加失败，请检查参数");
            }
        }
        return ApiJsonResult.ok();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> queryAll() {
        return this.categoryMapper.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public CategoryVo queryComposeById(Long categoryId) {
        return this.categoryMapper.queryCategoryParamCompose(categoryId);
    }
}
