package ccu.iot.cloud.controller;

import ccu.iot.cloud.entity.mqttapi.CurrentMqttSub;
import ccu.iot.cloud.entity.mqttapi.PublishEntity;
import ccu.iot.cloud.result.Result;
import ccu.iot.cloud.service.MqttApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mqtt")
public class MqttApiController {

    @Autowired
    private MqttApiService mqttApiService;

    /***
     * 查询当前mqtt服务器上全部的订阅信息
     * @return
     * @throws Exception
     */
    @GetMapping("/subscriptions")
    public Result<List<CurrentMqttSub>> queryCurrentSubscriptions() throws Exception {
        List<CurrentMqttSub> currentMqttSubs = this.mqttApiService.queryCurrentSubscriptions();
        if (currentMqttSubs.size() > 0) {
            return new Result<>(200, "success", currentMqttSubs);
        }
        return new Result<>(400, "failed to query subscriptions", null);
    }

    /***
     * 像指定主题发送信息
     * @param cacheKey
     * @param publishEntity
     * @return
     */
    @PostMapping("/publish")
    public Result publish(String cacheKey, PublishEntity publishEntity) {
        String username = cacheKey.split("_")[1];
        this.mqttApiService.publish( username, publishEntity);
        return new Result(200, "success", null);
    }


}
