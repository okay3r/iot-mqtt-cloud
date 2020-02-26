package ccu.iot.cloud.dao;

import ccu.iot.cloud.entity.OperationRecord;

import java.util.List;

public interface OperationRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(OperationRecord record);

    int insertSelective(OperationRecord record);

    OperationRecord selectByPrimaryKey(Long id);

    List<OperationRecord> selectBySelective(OperationRecord operationRecord);

    int updateByPrimaryKeySelective(OperationRecord record);

    int updateByPrimaryKey(OperationRecord record);
}