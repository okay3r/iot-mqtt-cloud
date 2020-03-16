package ccu.iot.cloud.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/***
 * rabbitMQ配置
 */
@Configuration
@PropertySource("classpath:properties/rabbit.properties")
public class RabbitConfig {

    @Value("${iot.rabbit.exchange}")
    private String exchange;

    @Value("${iot.rabbit.queue}")
    private String queue;

    @Value("${iot.rabbit.topic}")
    private String topic;

    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange, false, false);
    }

    @Bean
    public Binding bindingExchangeQueueA(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(topic);
    }

}
