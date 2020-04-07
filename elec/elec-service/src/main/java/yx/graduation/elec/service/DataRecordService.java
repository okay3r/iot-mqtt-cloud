package yx.graduation.elec.service;

import yx.graduation.elec.pojo.vo.MessageVo;
import yx.graduation.utils.PagedGridResult;

public interface DataRecordService {


    void storeMessage(MessageVo messageVo) throws Exception;

    PagedGridResult queryByDeviceId(String deviceId, Integer page, Integer pageSize);
}
