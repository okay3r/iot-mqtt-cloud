package ccu.iot.cloud.controller;

import ccu.iot.cloud.entity.MqttSub;
import ccu.iot.cloud.result.Result;
import ccu.iot.cloud.service.MqttSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/msg")
public class MqttMessageController {

    @Autowired
    private MqttSubService mqttSubService;

    /***
     * 查询全部消息记录
     * @return
     */
    @GetMapping("/queryAll")
    public Result<List<MqttSub>> queryAll() {
        List<MqttSub> messageList = this.mqttSubService.queryAll();
        return new Result<>(HttpStatus.OK.value(), "query success", messageList);
    }

    /***
     * 根据时间范围查询消息记录
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/queryByTime")
    public Result<List<MqttSub>> queryByTime(
            Date start,
            Date end) {
        List<MqttSub> mqttMessageList = this.mqttSubService.queryByTime(start, end);
        return new Result<>(HttpStatus.OK.value(), "query success", mqttMessageList);
    }

    /***
     * 根据topic模糊查询消息记录
     * @param topic
     * @return
     */
    @GetMapping("/queryByTopic")
    public Result<List<MqttSub>> queryByTopic(String topic) {
        List<MqttSub> mqttMessageList = this.mqttSubService.queryByTopic(topic);
        if (mqttMessageList != null) {
            return new Result<>(HttpStatus.OK.value(), "query success", mqttMessageList);
        }
        return new Result<>(HttpStatus.BAD_REQUEST.value(), "failed", null);
    }
}
