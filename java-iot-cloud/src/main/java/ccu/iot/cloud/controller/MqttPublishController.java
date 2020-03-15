package ccu.iot.cloud.controller;

import ccu.iot.cloud.entity.PublishRecord;
import ccu.iot.cloud.result.Result;
import ccu.iot.cloud.service.PublishRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pub")
public class MqttPublishController {

    @Autowired
    private PublishRecordService publishRecordService;

    /***
     * 查询全部publish记录
     * @return
     */
    @GetMapping("/queryAll")
    public Result<List<PublishRecord>> queryAll() {
        List<PublishRecord> publishRecordList = this.publishRecordService.queryAll();
        if (publishRecordList != null && publishRecordList.size() > 0) {
            return new Result<>(200, "success", publishRecordList);
        }
        return new Result<>(400, "failed", null);
    }

    /***
     * 根据时间范围查询publish记录
     */
    @GetMapping("/queryByTime")
    public Result<List<PublishRecord>> queryByTime(Date start, Date end) {
        List<PublishRecord> publishRecords = this.publishRecordService.queryByTime(start, end);
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
    public Result<List<PublishRecord>> queryByTopic(String topic) {
        List<PublishRecord> publishRecords = this.publishRecordService.queryByTopic(topic);
        if (publishRecords != null && publishRecords.size() > 0) {
            return new Result<>(200, "success", publishRecords);
        } else {
            return new Result<>(400, "failed", null);
        }
    }
}
