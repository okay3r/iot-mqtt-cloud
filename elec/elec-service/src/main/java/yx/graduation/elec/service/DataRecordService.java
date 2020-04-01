package yx.graduation.elec.service;

import yx.graduation.elec.pojo.vo.MessageVo;

public interface DataRecordService {

    void storeMessage(MessageVo messageVo) throws Exception;

}
