package yx.graduation.elec.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yx.graduation.elec.async.AsynTaskBean;
import yx.graduation.utils.ApiJsonResult;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Api(value = "socket连接", tags = "socket连接相关接口")
@RestController
@RequestMapping
public class SocketController {

    @Autowired
    private AsynTaskBean asynTaskBean;

    /**
     * 开启socket
     */
    @ApiOperation(value = "开启socket", notes = "开启socket", httpMethod = "GET")
    @GetMapping("/openSocket")
    public ApiJsonResult openSocket() throws InterruptedException {
        asynTaskBean.handleConn();
        return ApiJsonResult.ok();
    }

    /**
     * 查看连接列表
     */
    @ApiOperation(value = "查看连接列表", notes = "查看连接列表", httpMethod = "GET")
    @GetMapping("/list")
    public ApiJsonResult list() {
        Set<String> currentConns = this.asynTaskBean.getCurrentConns();
        return ApiJsonResult.ok(currentConns);
    }

    /**
     * 断开指定连接
     */
    @ApiOperation(value = "断开指定连接", notes = "断开指定连接", httpMethod = "GET")
    @GetMapping("/close")
    public ApiJsonResult close(@RequestParam String address) {
        this.asynTaskBean.closeConn(address);
        return ApiJsonResult.ok();
    }

}
