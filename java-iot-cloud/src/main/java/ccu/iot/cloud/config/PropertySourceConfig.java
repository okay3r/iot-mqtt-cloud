package ccu.iot.cloud.config;


import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:properties/redis.properties")
@PropertySource("classpath:properties/http.properties")
@PropertySource("classpath:properties/rabbit.properties")
public class PropertySourceConfig {
}
