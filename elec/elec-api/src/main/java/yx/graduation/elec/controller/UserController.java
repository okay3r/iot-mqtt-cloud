package yx.graduation.elec.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import yx.graduation.elec.pojo.bo.UserBo;
import yx.graduation.elec.service.UserService;
import yx.graduation.utils.ElecResult;


@Api(value = "用户", tags = "用户相关接口")
@Controller
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册新用户
     */
    @ApiOperation(value = "注册新用户", notes = "注册新用户", httpMethod = "POST")
    @PostMapping("/regist")
    public ModelAndView register(@RequestBody UserBo userBo) {
        ElecResult result = this.userService.register(userBo);

        ModelAndView modelAndView = new ModelAndView();
        if (result.getStatus() != 200) {
            modelAndView.setViewName("regist");
            modelAndView.addObject("msg", result.getMsg());
        }
        modelAndView.setViewName("login");
        modelAndView.addObject("msg", "注册成功");
        return modelAndView;
    }

}
