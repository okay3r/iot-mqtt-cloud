package rabbit

import (
	"github.com/streadway/amqp"
	"go-iot-cloud/src/config"
	"go-iot-cloud/src/utils"
	"log"
)

//将消息发送到rabbit中
func Publish(channel *amqp.Channel, topic string, msg []byte) {
	basicKey := config.GlobalInfo.RabbitPublish.BasicKey
	//拼接topic
	pubTopic := basicKey + topic
	exchangeInfo := config.GlobalInfo.Exchange
	publishInfo := config.GlobalInfo.RabbitPublish

	err := channel.Publish(
		exchangeInfo.Name,
		pubTopic,
		publishInfo.Mandatory,
		publishInfo.Immediate,
		amqp.Publishing{
			ContentType: publishInfo.ContentType,
			Body:        msg,
		},
	)
	utils.FailOnError("publish error", err)
	//发送成功，记录日志
	log.Printf("transfer to rabbit [%s] : [%s]", pubTopic, string(msg))
}
