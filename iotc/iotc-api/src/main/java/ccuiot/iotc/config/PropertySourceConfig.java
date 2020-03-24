package ccuiot.iotc.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("classpath:properties/redis.properties")
@PropertySource("classpath:properties/http.properties")
@PropertySource("classpath:properties/rabbit.properties")
public class PropertySourceConfig {
}
