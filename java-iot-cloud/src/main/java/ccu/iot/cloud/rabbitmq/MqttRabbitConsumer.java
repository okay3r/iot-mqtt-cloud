package ccu.iot.cloud.rabbitmq;

import ccu.iot.cloud.async.AsyncMqttSubTransferHandler;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MqttRabbitConsumer {

    @Autowired
    private AsyncMqttSubTransferHandler asyncMqttSubTransferHandler;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = "iot-mqtt-queue")
    public void receive(@Payload String informationJson,
                        @Headers Map<String, Object> headers,
                        Channel channel) {
        logger.info("mqtt receive # " + informationJson);

        this.asyncMqttSubTransferHandler.handleMsg(informationJson);
    }
}
