package config

//全局配置信息
type GlobalConfig struct {
	Emqx          EmqxConfig
	EmqxTopic     EmqxTopicConfig
	Rabbit        RabbitConfig
	Exchange      RabbitExchangeConfig
	RabbitPublish RabbitPublishConfig
}

//Emqx配置信息
type EmqxConfig struct {
	Address  string
	Username string
	Password string
	ClientId string
}

//监听Mqtt主题的配置信息
type EmqxTopicConfig struct {
	TopicList []string
	QosList   []int
}

//Rabbitmq连接配置信息
type RabbitConfig struct {
	Address      string
	Username     string
	Password     string
	BasicTopic   string
	ExchangeName string
	Kind         string
}

//Rabbitmq交换机参数配置
type RabbitExchangeConfig struct {
	Name       string
	Kind       string
	Durable    bool
	AutoDelete bool
	Internal   bool
	NoWait     bool
}

//Rabbitmq发送时参数配置
type RabbitPublishConfig struct {
	BasicKey    string
	Mandatory   bool
	Immediate   bool
	ContentType string
}

var GlobalInfo GlobalConfig

func init() {
	GlobalInfo = loadConfig()
}
