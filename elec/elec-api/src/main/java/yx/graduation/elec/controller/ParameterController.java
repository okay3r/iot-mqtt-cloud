package yx.graduation.elec.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yx.graduation.elec.pojo.Parameter;
import yx.graduation.elec.service.ParameterService;
import yx.graduation.enums.RedisKeyEnum;
import yx.graduation.utils.ApiJsonResult;
import yx.graduation.utils.RedisOperator;

import java.util.List;


@Api(value = "参数", tags = "参数相关接口")
@RestController
@RequestMapping("/param")
public class ParameterController {

    @Autowired
    private ParameterService parameterService;

    @Autowired
    private RedisOperator redisOperator;

    /**
     * 添加参数
     */
    @ApiOperation(value = "添加参数", notes = "添加参数", httpMethod = "POST")
    @PostMapping("/add")
    public ApiJsonResult add(
            @RequestParam String paramName,
            @RequestParam String unit) {
        if (StringUtils.isBlank(paramName) || StringUtils.isBlank(unit)) {
            return ApiJsonResult.errorMsg("参数不能为空");
        }
        ApiJsonResult result = this.parameterService.createParameter(paramName, unit);
        if (result.getStatus() == 200) {
            Parameter parameter = (Parameter) result.getData();
            //添加单个参数映射到redis中
            this.redisOperator.set(RedisKeyEnum.PARAM_KV.value + ":" + parameter.getParameterName(),
                    parameter.getParameterUnit());
            //将参数集合删除
            this.redisOperator.del(RedisKeyEnum.PARAMS.value);
        }
        return result;
    }

    /**
     * 查看参数列表
     */
    @ApiOperation(value = "查看参数列表", notes = "查看参数列表", httpMethod = "GET")
    @GetMapping("/list")
    public ApiJsonResult list() {
        String paramsJson = (String) this.redisOperator.get(RedisKeyEnum.PARAMS.value);
        List<Parameter> parameterList;
        if (StringUtils.isNotBlank(paramsJson)) {
            parameterList = JSON.parseArray(paramsJson, Parameter.class);
        } else {
            parameterList = this.parameterService.queryAll();
            this.redisOperator.set(RedisKeyEnum.PARAMS.value, JSON.toJSONString(parameterList));
        }
        return ApiJsonResult.ok(parameterList);
    }

}
