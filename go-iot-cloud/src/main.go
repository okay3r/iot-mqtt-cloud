package main

import (
	"go-iot-cloud/src/emqx"
	"go-iot-cloud/src/rabbit"
	"go-iot-cloud/src/utils"
)

func main() {
	rabbitConn := rabbit.GetConnection()
	defer rabbitConn.Close()
	channel, err := rabbitConn.Channel()
	utils.FailOnError("failed get channel", err)
	defer channel.Close()
	rabbit.DeclareExchange(channel)
	emqx.Channel = channel

	client := emqx.GetEmqxClient(nil)
	emqx.DoSubEmqx(client)
	select {}
}
