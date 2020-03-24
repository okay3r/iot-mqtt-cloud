package ccuiot.iotc.controller;

import ccuiot.iotc.utils.ApiJsonResult;
import ccuiot.iotc.utils.LoginAck;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "模板", tags = "模板——相关接口")
@RestController
@RequestMapping("/template")
public class TemplateController {

    /**
     * 模板方法
     */
    @ApiOperation(value = "模板方法", notes = "模板方法", httpMethod = "POST")
    @PostMapping("/login")
    public ApiJsonResult login() {
        return ApiJsonResult.ok();
    }

}
