package ccu.iot.cloud;

import ccu.iot.cloud.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("ccu.iot.cloud.mapper")
@PropertySource("classpath:properties/redis.properties")
@PropertySource("classpath:properties/http.properties")
@PropertySource("classpath:properties/rabbit.properties")
public class IotCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotCloudApplication.class, args);
    }

}
