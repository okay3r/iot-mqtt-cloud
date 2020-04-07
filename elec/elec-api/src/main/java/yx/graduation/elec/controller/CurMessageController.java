package yx.graduation.elec.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yx.graduation.elec.pojo.DataRecord;
import yx.graduation.elec.pojo.vo.CurDataVo;
import yx.graduation.elec.service.DataRecordService;
import yx.graduation.enums.RedisKeyEnum;
import yx.graduation.utils.ApiJsonResult;
import yx.graduation.utils.PagedGridResult;
import yx.graduation.utils.RedisOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Api(value = "设备消息", tags = "设备消息相关接口")
@RestController
@RequestMapping("/msg")
public class CurMessageController extends BaseController{

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private DataRecordService dataRecordService;

    /**
     * 查看全部最新消息
     */
    @ApiOperation(value = "查看全部最新消息", notes = "查看全部最新消息", httpMethod = "GET")
    @GetMapping("/list")
    public ApiJsonResult list() {
        Set<String> keys = this.redisOperator.getKeys(RedisKeyEnum.CUR_VALUE.value + "*");
        List<CurDataVo> res = new ArrayList<>();
        for (String key : keys) {
            String jsonStr = (String) this.redisOperator.get(key);
            res.add(JSON.parseObject(jsonStr, CurDataVo.class));
        }
        return ApiJsonResult.ok(res);
    }

    /**
     * 查看指定设备的消息记录
     */
    @ApiOperation(value = "查看指定设备的消息记录", notes = "查看指定设备的消息记录", httpMethod = "GET")
    @GetMapping("/queryByDeviceId")
    public ApiJsonResult queryByDeviceId(
            @RequestParam String deviceId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize
    ) {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        PagedGridResult pagedGridResult = this.dataRecordService.queryByDeviceId(deviceId, page, pageSize);
        return ApiJsonResult.ok(pagedGridResult);
    }

}
