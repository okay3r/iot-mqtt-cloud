package emqx

import (
	MQTT "github.com/eclipse/paho.mqtt.golang"
	c "go-iot-cloud/src/config"
	"log"
)

type MqttInformation struct {
	Topic   string
	Payload string
	Qos     int
	Time    string
}

/*
	订阅所有配置文件中的主题
*/
func DoSubEmqx(client *MQTT.Client) {
	count := len(c.GlobalInfo.EmqxTopic.TopicList)
	for i := 0; i < count; i++ {
		topic := c.GlobalInfo.EmqxTopic.TopicList[i]
		qos := c.GlobalInfo.EmqxTopic.QosList[i]
		DoSubscribe(client, topic, byte(qos))
	}
}

//开启订阅
//qos:允许监听的qos，2则可以监听0、1、2，  1则监听0，1   0监听0
func DoSubscribe(client *MQTT.Client, topic string, qos byte) {
	if token := (*client).Subscribe(topic, qos, MsgReceivedHandler); token.Wait() && token.Error() != nil {
		log.Println(token.Error())
		//os.Exit(1)
	}
	//开启监听，记录日志
	log.Println("listening to ", topic)
}

//mqtt监听到消息后的回调函数
func MsgReceivedHandler(client MQTT.Client, message MQTT.Message) {
	//异步对新消息进行处理
	go HandleMsgFromEmqx(client, message)
}
