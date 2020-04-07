package ccuiot.iotc.controller;

import ccuiot.iotc.utils.ApiJsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "模板", tags = "模板——相关接口")
@RestController
@RequestMapping
public class TemplateController {

    /**
     * 模板方法
     */
    @ApiOperation(value = "测试方法", notes = "简单测试，不需要实现", httpMethod = "GET")
    @GetMapping("/hello")
    public ApiJsonResult login() {
        return ApiJsonResult.ok("Hello IOTC！");
    }

}
