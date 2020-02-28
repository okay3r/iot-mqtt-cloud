package ccu.iot.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class IotCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotCloudApplication.class, args);
	}

}
