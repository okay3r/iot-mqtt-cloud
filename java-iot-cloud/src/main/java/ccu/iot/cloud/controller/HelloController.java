package ccu.iot.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hello")
@PropertySource("classpath:properties/rabbit.properties")
public class HelloController {
    @Value("${iot.rabbit.exchange}")
    private static String exchange;

    @Value("${iot.rabbit.queue}")
    private static String queue;

    @Value("${iot.rabbit.topic}")
    private static String topic;

    @GetMapping("test")
    public void test() {
        System.out.println(exchange);
    }
}
