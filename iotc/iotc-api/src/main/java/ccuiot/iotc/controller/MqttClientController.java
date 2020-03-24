package ccuiot.iotc.controller;

import ccuiot.iotc.pojo.ClientTopicInfo;
import ccuiot.iotc.service.MqttClientService;
import ccuiot.iotc.utils.ApiJsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "客户端信息", tags = "客户端信息相关接口")
@RestController
@RequestMapping("/client")
public class MqttClientController {

    @Autowired
    private MqttClientService mqttClientService;

    /***
     * 查询当前mqtt服务器上全部的订阅信息
     */
    @ApiOperation(value = "查询当前mqtt服务器上全部的订阅信息", notes = "查询当前mqtt服务器上全部的订阅信息", httpMethod = "GET")
    @GetMapping("/subscriptions")
    public ApiJsonResult queryCurrentSubscriptions(){
        List<ClientTopicInfo> clientTopicInfos = this.mqttClientService.queryCurrentSubscriptions();
        return ApiJsonResult.ok(clientTopicInfos);
    }

    /**
     * 设置客户端信息
     */
    @ApiOperation(value = "设置客户端信息", notes = "设置客户端信息", httpMethod = "POST")
    @PostMapping("/setClientInfo")
    public ApiJsonResult setClientInfo(
            @RequestParam String clientId,
            @RequestParam(required = false) String clientName,
            @RequestParam(required = false) String remark
    ) {
        this.mqttClientService.setClientInfo(clientId, clientName, remark);
        return ApiJsonResult.ok();
    }

}
