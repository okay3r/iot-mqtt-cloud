package yx.graduation.elec.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yx.graduation.elec.pojo.Category;
import yx.graduation.elec.pojo.vo.CategoryVo;
import yx.graduation.elec.service.CategoryService;
import yx.graduation.enums.RedisKeyEnum;
import yx.graduation.utils.ApiJsonResult;
import yx.graduation.utils.RedisOperator;

import java.util.List;


@Api(value = "类别", tags = "类别相关接口")
@RestController
@RequestMapping("/cat")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisOperator redisOperator;
    /**
     * 添加类别
     */
    @ApiOperation(value = "添加类别", notes = "添加类别", httpMethod = "POST")
    @PostMapping("/add")
    public ApiJsonResult login(
            @RequestParam String categoryName,
            @RequestParam String categoryRemark,
            @RequestParam String params
    ) {
        if (StringUtils.isBlank(categoryName) || StringUtils.isBlank(categoryRemark) || StringUtils.isBlank(params)) {
            return ApiJsonResult.errorMsg("参数不正确");
        }
        ApiJsonResult result = this.categoryService.add(categoryName, categoryRemark, params);
        if (result.getStatus() == 200) {
            this.redisOperator.del(RedisKeyEnum.CATS.value);
        }
        return result;
    }

    /**
     * 查看全部类别
     */
    @ApiOperation(value = "查看全部类别", notes = "查看全部类别", httpMethod = "GET")
    @GetMapping("/list")
    public ApiJsonResult list() {
        String catListJson = (String) this.redisOperator.get(RedisKeyEnum.CATS.value);
        List<Category> categoryList;
        if (StringUtils.isNotBlank(catListJson)) {
            categoryList = JSON.parseArray(catListJson, Category.class);
        } else {
            categoryList = this.categoryService.queryAll();
            this.redisOperator.set(RedisKeyEnum.CATS.value, JSON.toJSONString(categoryList));
        }
        return ApiJsonResult.ok(categoryList);
    }

    /**
     * 根据id查看类别详细信息
     */
    @ApiOperation(value = "根据id查看类别详细信息", notes = "根据id查看类别详细信息", httpMethod = "GET")
    @GetMapping("/queryById")
    public ApiJsonResult queryById(@RequestParam Long categoryId) {
        CategoryVo categoryVo = this.categoryService.queryComposeById(categoryId);
        return ApiJsonResult.ok(categoryVo);
    }

}
