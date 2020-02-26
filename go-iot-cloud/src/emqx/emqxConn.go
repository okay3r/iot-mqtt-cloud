package emqx

import (
	MQTT "github.com/eclipse/paho.mqtt.golang"
	c "go-iot-cloud/src/config"
	"go-iot-cloud/src/utils"
)

//设置mqtt连接参数，建立连接，返回操作客户端
func GetEmqxClient(publishHandler func(client MQTT.Client,
	message MQTT.Message)) *MQTT.Client {
	config := c.GlobalInfo.Emqx
	opts := MQTT.NewClientOptions().AddBroker(config.Address)
	opts.SetClientID(config.ClientId)
	opts.SetUsername(config.Username)
	opts.SetPassword(config.Password)
	opts.SetDefaultPublishHandler(publishHandler)

	client := MQTT.NewClient(opts)

	//创建连接
	if token := client.Connect(); token.Wait() && token.Error() != nil {
		utils.FailOnError("failed to get emqx connection", token.Error())
	}
	return &client
}
