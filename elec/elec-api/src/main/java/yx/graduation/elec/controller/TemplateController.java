package yx.graduation.elec.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yx.graduation.utils.ElecResult;


@Api(value = "模板", tags = "模板——相关接口")
@Controller
@RequestMapping
public class TemplateController {

    /**
     * 模板方法
     */
    @ApiOperation(value = "模板方法", notes = "模板方法", httpMethod = "POST")
    @GetMapping("/hello")
    public ElecResult login() {
        return ElecResult.ok().ok("Hello ELEC！");
    }

}
