package yx.graduation.elec.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yx.graduation.utils.ApiJsonResult;


@Api(value = "参数", tags = "参数相关接口")
@RestController
@RequestMapping
public class ParameterController {

    /**
     * 模板方法
     */
    @ApiOperation(value = "模板方法", notes = "模板方法", httpMethod = "GET")
    @GetMapping("/hello")
    public ApiJsonResult login() {
        return ApiJsonResult.ok().ok("Hello ELEC！");
    }

}
