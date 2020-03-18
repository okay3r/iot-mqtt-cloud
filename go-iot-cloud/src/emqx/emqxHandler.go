package emqx

import (
	"encoding/json"
	MQTT "github.com/eclipse/paho.mqtt.golang"
	"github.com/streadway/amqp"
	"go-iot-cloud/src/rabbit"
	"go-iot-cloud/src/utils"
	"log"
	"strings"
	"time"
)

var Channel *amqp.Channel

func HandleMsgFromEmqx(client MQTT.Client, message MQTT.Message) {
	time := time.Now().Format("2006-1-2 15:04:05")
	mqttInfo := MqttInformation{
		Topic:   message.Topic(),
		Payload: string(message.Payload()),
		Qos:     int(message.Qos()),
		Time:    time,
	}

	//记录日志
	log.Printf("Receive - [topic: %s]  [Payload: %s]  [QoS: %d]\n", mqttInfo.Topic, mqttInfo.Payload, mqttInfo.Qos)
	//格式化主题字符串
	useTopic := formatTopic(mqttInfo.Topic)

	informationJson, err := json.Marshal(mqttInfo)
	utils.FailOnError("failed format to json", err)

	//发布到rabbit中
	go rabbit.Publish(Channel, useTopic, informationJson)
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
