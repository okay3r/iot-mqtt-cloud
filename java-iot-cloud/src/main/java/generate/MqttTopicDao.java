package generate;

import generate.MqttTopic;

public interface MqttTopicDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MqttTopic record);

    int insertSelective(MqttTopic record);

    MqttTopic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MqttTopic record);

    int updateByPrimaryKey(MqttTopic record);
}