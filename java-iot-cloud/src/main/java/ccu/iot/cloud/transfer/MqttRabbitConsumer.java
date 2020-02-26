package ccu.iot.cloud.transfer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MqttRabbitConsumer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = "iot-mqtt-queue")
    public void receive(@Payload String msg,
                        @Headers Map<String, Object> headers,
                        Channel channel) {
        logger.info(msg);
    //    TODO 接收到Mqtt消息的逻辑
    }
}
