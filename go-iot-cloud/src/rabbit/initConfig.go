package rabbit

import (
	"github.com/streadway/amqp"
	c "go-iot-cloud/src/config"
	"go-iot-cloud/src/utils"
)

//建立rabbit连接
func GetConnection() *amqp.Connection {
	config := c.GlobalInfo.Rabbit
	connUrl := "amqp://" + config.Username + ":" + config.Password + "@" + config.Address
	connection, err := amqp.Dial(connUrl)
	utils.FailOnError("failed get connection", err)

	return connection
}
