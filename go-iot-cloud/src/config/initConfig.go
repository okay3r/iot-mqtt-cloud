package config

import (
	"errors"
	"github.com/spf13/viper"
	"go-iot-cloud/src/utils"
	"strconv"
	"strings"
)

func loadConfig() GlobalConfig {
	path := "src/config"
	v := viper.New()
	//配置文件名称
	v.SetConfigName("config")
	//配置文件目录
	v.AddConfigPath(path)
	//读取配置文件
	err := v.ReadInConfig()
	utils.FailOnError("failed to read config", err)

	//读取Emqx连接配置
	emqxConfig := loadEmqxConfig(v)
	//读取Mqtt主题配置
	emqxTopicConfig := loadEmqxTopicConfig(v)
	//读取Rabbitmq连接配置
	rabbitConfig := loadRabbitConfig(v)
	//读取Rabbitmq交换机参数配置
	rabbitExchangeConfig := loadRabbitExchangeConfig(v)
	//读取Rabbitmq发送参数配置
	rabbitPublishInfoConfig := loadRabbitPublishConfig(v)

	//将读取到的所有信息封装到全局配置信息中
	globalConfig := GlobalConfig{
		Emqx:          emqxConfig,
		EmqxTopic:     emqxTopicConfig,
		Rabbit:        rabbitConfig,
		Exchange:      rabbitExchangeConfig,
		RabbitPublish: rabbitPublishInfoConfig,
	}

	return globalConfig
}

func loadRabbitPublishConfig(v *viper.Viper) RabbitPublishConfig {
	rabbitPublishInfo := RabbitPublishConfig{
		BasicKey:    v.GetString("rabbitmq.publish.basicKey"),
		Mandatory:   v.GetBool("rabbitmq.publish.mandatory"),
		Immediate:   v.GetBool("rabbitmq.publish.immediate"),
		ContentType: v.GetString("rabbitmq.publish.contentType"),
	}
	return rabbitPublishInfo
}

func loadRabbitConfig(v *viper.Viper) RabbitConfig {
	rabbitInfo := RabbitConfig{
		Address:    v.GetString("rabbitmq.address"),
		Username:   v.GetString("rabbitmq.username"),
		Password:   v.GetString("rabbitmq.password"),
		BasicTopic: v.GetString("rabbitmq.basicTopic"),
	}
	return rabbitInfo
}

func loadEmqxTopicConfig(v *viper.Viper) EmqxTopicConfig {
	topics := v.GetString("emqx.topic.topicList")
	qoss := v.GetString("emqx.topic.qosList")
	topicList := strings.Split(topics, ",")
	tmpQosList := strings.Split(qoss, ",")
	if len(topicList) != len(tmpQosList) {
		utils.FailOnError("topic and qos 's count should be same", errors.New("count not sum"))
	}
	var qosList []int
	for _, s := range tmpQosList {
		i, err := strconv.Atoi(s)
		utils.FailOnError("failed string format num", err)
		qosList = append(qosList, i)
	}
	emqxTopicInfo := EmqxTopicConfig{
		TopicList: topicList,
		QosList:   qosList,
	}
	return emqxTopicInfo
}

func loadEmqxConfig(v *viper.Viper) EmqxConfig {
	emqxConfig := EmqxConfig{
		Address:  v.GetString("emqx.address"),
		Username: v.GetString("emqx.username"),
		Password: v.GetString("emqx.password"),
		ClientId: v.GetString("emqx.clientId"),
	}
	return emqxConfig
}

func loadRabbitExchangeConfig(v *viper.Viper) RabbitExchangeConfig {
	rabbitExchangeConfig := RabbitExchangeConfig{
		Name:       v.GetString("rabbitmq.exchange.name"),
		Kind:       v.GetString("rabbitmq.exchange.kind"),
		Durable:    v.GetBool("rabbitmq.exchange.durable"),
		AutoDelete: v.GetBool("rabbitmq.exchange.autoDelete"),
		Internal:   v.GetBool("rabbitmq.exchange.internal"),
		NoWait:     v.GetBool("rabbitmq.exchange.noWait"),
	}
	return rabbitExchangeConfig
}
