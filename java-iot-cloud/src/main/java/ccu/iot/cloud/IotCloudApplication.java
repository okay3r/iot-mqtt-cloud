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
public class IotCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotCloudApplication.class, args);
    }

}
