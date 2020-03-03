package cloud.dao;

import cloud.entity.DataRecord;

public interface DataRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DataRecord record);

    int insertSelective(DataRecord record);

    DataRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataRecord record);

    int updateByPrimaryKey(DataRecord record);
}