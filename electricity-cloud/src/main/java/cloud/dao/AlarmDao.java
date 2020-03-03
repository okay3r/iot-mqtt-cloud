package cloud.dao;

import cloud.entity.Alarm;

public interface AlarmDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Alarm record);

    int insertSelective(Alarm record);

    Alarm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Alarm record);

    int updateByPrimaryKey(Alarm record);
}