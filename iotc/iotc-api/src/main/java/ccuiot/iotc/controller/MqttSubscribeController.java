package ccuiot.iotc.controller;

import ccuiot.iotc.pojo.MqttSub;
import ccuiot.iotc.service.MqttSubService;
import ccuiot.iotc.utils.ApiJsonResult;
import ccuiot.iotc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(value = "订阅到的消息", tags = "订阅到的消息相关接口")
@RestController
@RequestMapping("/sub")
public class MqttSubscribeController extends BaseController{

    @Autowired
    private MqttSubService mqttSubService;

    /***
     * 查询publish记录
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询subscribe记录", notes = "查询subscribe记录", httpMethod = "GET")
    public ApiJsonResult list(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize) {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        PagedGridResult pagedGridResult = this.mqttSubService.list(page, pageSize);
        return ApiJsonResult.ok(pagedGridResult);
    }

    /***
     * 根据时间范围查询publish记录
     * 2020/03/24 21:09:35
     */
    @GetMapping("/queryByTime")
    @ApiOperation(value = "根据时间范围查询subscribe记录", notes = "根据时间范围查询subscribe记录", httpMethod = "GET")
    public ApiJsonResult queryByTime(
            @RequestParam(required = true) Date start,
            @RequestParam(required = true) Date end,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize) {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        PagedGridResult pagedGridResult = this.mqttSubService.queryByTime(start, end, page, pageSize);
        return ApiJsonResult.ok(pagedGridResult);
    }

    /***
     * 根据topic模糊查询
     */
    @GetMapping("/queryByTopic")
    @ApiOperation(value = "模糊查询subscribe记录", notes = "模糊查询subscribe记录", httpMethod = "GET")
    public ApiJsonResult queryByTopic(
            @RequestParam String topic,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize) {

        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        PagedGridResult pagedGridResult = this.mqttSubService.queryByTopic(topic, page, pageSize);
        return ApiJsonResult.ok(pagedGridResult);
    }

}
