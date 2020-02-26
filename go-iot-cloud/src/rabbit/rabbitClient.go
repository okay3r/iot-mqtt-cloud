package rabbit

import (
	"github.com/streadway/amqp"
	c "go-iot-cloud/src/config"
	"go-iot-cloud/src/utils"
)

//根据配置信息注册交换机
func DeclareExchange(channel *amqp.Channel) {
	config:=c.GlobalInfo.Exchange
	err := channel.ExchangeDeclare(
		config.Name,
		config.Kind,
		config.Durable,
		config.AutoDelete,
		config.Internal,
		config.NoWait,
		nil,
	)
	utils.FailOnError("failed to declare exchange", err)
}
