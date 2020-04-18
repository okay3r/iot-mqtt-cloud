package yx.graduation.elec.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import yx.graduation.elec.mapper.DataRecordMapper;
import yx.graduation.elec.pojo.DataRecord;
import yx.graduation.elec.pojo.vo.CurDataVo;
import yx.graduation.elec.pojo.vo.MessageVo;
import yx.graduation.elec.service.DataRecordService;
import yx.graduation.enums.RedisKeyEnum;
import yx.graduation.utils.PagedGridResult;
import yx.graduation.utils.RedisOperator;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class DataRecordServiceImpl implements DataRecordService {

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private DataRecordMapper dataRecordMapper;

    /**
     * 将消息存储到db,并更新到redis中作为实时数据
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void storeMessage(MessageVo messageVo) throws Exception {
        String deviceId = messageVo.getDeviceId();
        String parameterName = messageVo.getParameter();
        DataRecord dataRecord = new DataRecord();

        //从redis中获取device信息，如果没有则直接抛出异常
        String deviceName = (String) this.redisOperator.get(RedisKeyEnum.DEVICE_KV.value + ":" + deviceId);
        String unit = (String) this.redisOperator.get(RedisKeyEnum.PARAM_KV.value + ":" + parameterName);

        if (StringUtils.isBlank(deviceName) || StringUtils.isBlank(unit)) {
            throw new Exception();
        }
        dataRecord.setHost(messageVo.getHost());
        dataRecord.setDeviceId(deviceId);
        dataRecord.setDeviceName(deviceName);
        dataRecord.setValue(Integer.parseInt(messageVo.getMsg()));
        dataRecord.setParamName(parameterName);
        dataRecord.setParamUnit(unit);
        dataRecord.setTime(new Date());

        this.dataRecordMapper.insert(dataRecord);


        /**
         * 更新实时数据到redis中
         */
        CurDataVo curDataVo;
        String jsonStr = (String) this.redisOperator.get(RedisKeyEnum.CUR_VALUE.value + ":" + deviceId);
        if (jsonStr != null) {
            curDataVo = JSON.parseObject(jsonStr, CurDataVo.class);
        } else {
            curDataVo = new CurDataVo();
            curDataVo.setDeviceId(deviceId);
            curDataVo.setDeviceName(deviceName);
            curDataVo.setHost(messageVo.getHost());
            curDataVo.setParamValueMap(new HashMap<>());
        }
        curDataVo.getParamValueMap().put(parameterName, messageVo.getMsg());
        this.redisOperator.set(RedisKeyEnum.CUR_VALUE.value + ":" + deviceId, JSON.toJSONString(curDataVo));
    }

    /**
     * 根据设备id查询消息记录
     * @return
     */
    @Override
    public PagedGridResult queryByDeviceId(String deviceId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        Example example = new Example(DataRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deviceId", deviceId);
        List<DataRecord> recordList = this.dataRecordMapper.selectByExample(example);
        return setPagedGrid(recordList, page);
    }

    public PagedGridResult setPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
