package yx.graduation.elec.service;

import org.springframework.web.bind.annotation.RequestParam;
import yx.graduation.elec.pojo.Category;
import yx.graduation.elec.pojo.vo.CategoryVo;
import yx.graduation.utils.ApiJsonResult;

import java.util.List;

public interface CategoryService {
    ApiJsonResult add(String categoryName, String categoryRemark, String params);

    List<Category> queryAll();

    CategoryVo queryComposeById(Long categoryId);
}
