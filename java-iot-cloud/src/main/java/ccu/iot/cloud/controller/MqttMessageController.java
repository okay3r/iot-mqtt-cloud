package ccu.iot.cloud.controller;

import ccu.iot.cloud.entity.MqttMessage;
import ccu.iot.cloud.result.Result;
import ccu.iot.cloud.service.MqttMessageService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/msg")
public class MqttMessageController {

    @Autowired
    private MqttMessageService mqttMessageService;

    @GetMapping("/queryAll")
    public Result<List<MqttMessage>> queryMqttMsg() {
        List<MqttMessage> messageList = this.mqttMessageService.queryMqttMsgHistory();
        return new Result<List<MqttMessage>>(HttpStatus.OK.value(), "query success", messageList);
    }

    @GetMapping("/queryByTime")
    public List<MqttMessage> queryMqttMsgByTime(Date start, Date end) {
        System.out.println(start);
        System.out.println(end);
        //TODO
        return null;
    }
}
