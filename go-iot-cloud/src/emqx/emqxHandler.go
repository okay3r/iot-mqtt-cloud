package emqx

import (
	MQTT "github.com/eclipse/paho.mqtt.golang"
	"github.com/streadway/amqp"
	"go-iot-cloud/src/rabbit"
	"log"
	"strings"
)

var Channel *amqp.Channel

func HandleMsgFromEmqx(client MQTT.Client, message MQTT.Message) {
	topic := message.Topic()
	msg := message.Payload()
	qos := message.Qos()
	//记录日志
	log.Printf("Receive - [topic: %s]  [Message: %s]  [QoS: %d]\n", topic, msg, qos)
	//格式化主题字符串
	useTopic := formatTopic(topic)
	//发布到rabbit中
	rabbit.Publish(Channel, useTopic, msg)
}

func formatTopic(topic string) string {
	if strings.HasSuffix(topic, "/") {
		topic = topic[1:]
	}
	if strings.HasSuffix(topic, "/") {
		topic = topic[:len(topic)-1]
	}
	split := strings.Split(topic, "/")
	var useTopic string
	for _, v := range split {
		useTopic += "."
		useTopic += v
	}
	return useTopic
}
