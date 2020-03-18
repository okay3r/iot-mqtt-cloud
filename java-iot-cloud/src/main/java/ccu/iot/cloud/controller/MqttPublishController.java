package ccu.iot.cloud.controller;

import ccu.iot.cloud.entity.MqttPub;
import ccu.iot.cloud.result.Result;
import ccu.iot.cloud.service.MqttPubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pub")
public class MqttPublishController {

    @Autowired
    private MqttPubService mqttPubService;

    /***
     * 查询全部publish记录
     * @return
     */
    @GetMapping("/queryAll")
    public Result<List<MqttPub>> queryAll() {
        List<MqttPub> publishRecordList = this.mqttPubService.queryAll();
        if (publishRecordList != null && publishRecordList.size() > 0) {
            return new Result<>(200, "success", publishRecordList);
        }
        return new Result<>(400, "failed", null);
    }

    /***
     * 根据时间范围查询publish记录
     */
    @GetMapping("/queryByTime")
    public Result<List<MqttPub>> queryByTime(Date start, Date end) {
        List<MqttPub> publishRecords = this.mqttPubService.queryByTime(start, end);
        if (publishRecords != null && publishRecords.size() > 0) {
            return new Result<>(200, "success", publishRecords);
        } else {
            return new Result<>(400, "failed", null);
        }
    }

    /***
     * 根据topic模糊查询
     * @param topic
     * @return
     */
    @GetMapping("/queryByTopic")
    public Result<List<MqttPub>> queryByTopic(String topic) {
        List<MqttPub> publishRecords = this.mqttPubService.queryByTopic(topic);
        if (publishRecords != null && publishRecords.size() > 0) {
            return new Result<>(200, "success", publishRecords);
        } else {
            return new Result<>(400, "failed", null);
        }
    }
}
