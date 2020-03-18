package ccu.iot.cloud.controller;

import ccu.iot.cloud.result.Result;
import ccu.iot.cloud.service.MqttCliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cli")
public class MqttClientController {

    @Autowired
    private MqttCliService mqttCliService;


    /***
     * 设置client的name、remark
     * @param clientId
     * @param clientName
     * @param remark
     * @return
     */
    @PostMapping("/setCliInfo")
    public Result setCliName(
            @RequestParam(value = "client_id") String clientId,
            @RequestParam(value = "client_name", required = false) String clientName,
            @RequestParam(value = "remark", required = false) String remark
    ) {
        Boolean success = this.mqttCliService.updateCliInfo(clientId, clientName, remark);
        if (success) {
            return new Result(200, "update success", null);
        }
        return new Result(400, "update failed", null);
    }
}
