package ccuiot.iotc.controller;

import ccuiot.iotc.pojo.MqttPub;
import ccuiot.iotc.pojo.bo.PublishBo;
import ccuiot.iotc.service.MqttPubService;
import ccuiot.iotc.service.impl.BaseService;
import ccuiot.iotc.utils.ApiJsonResult;
import ccuiot.iotc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Api(value = "发送消息", tags = "发送消息相关接口")
@RestController
@RequestMapping("/pub")
public class MqttPublishController extends BaseController {

    @Autowired
    private MqttPubService mqttPubService;

    /**
     * 发送消息
     */
    @ApiOperation(value = "发送消息", notes = "发送消息", httpMethod = "POST")
    @PostMapping("/doSend")
    public ApiJsonResult login(
            @RequestBody PublishBo publishBo,
            HttpServletRequest request
    ) {
        String cacheKey = request.getHeader("cacheKey");
        this.mqttPubService.doPublish(cacheKey, publishBo);
        return ApiJsonResult.ok();
    }

    /***
     * 查询publish记录
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询publish记录", notes = "查询publish记录", httpMethod = "GET")
    public ApiJsonResult list(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize) {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        PagedGridResult pagedGridResult = this.mqttPubService.list(page, pageSize);
        return ApiJsonResult.ok(pagedGridResult);
    }

    /***
     * 根据时间范围查询publish记录
     * 2020/03/24 21:09:35
     */
    @GetMapping("/queryByTime")
    @ApiOperation(value = "根据时间范围查询publish记录", notes = "根据时间范围查询publish记录", httpMethod = "GET")
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
        PagedGridResult pagedGridResult = this.mqttPubService.queryByTime(start, end, page, pageSize);
        return ApiJsonResult.ok(pagedGridResult);
    }

    /***
     * 根据topic模糊查询
     */
    @GetMapping("/queryByTopic")
    @ApiOperation(value = "根据topic模糊查询", notes = "根据topic模糊查询", httpMethod = "GET")
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

        PagedGridResult pagedGridResult = this.mqttPubService.queryByTopic(topic, page, pageSize);
        return ApiJsonResult.ok(pagedGridResult);
    }

}
