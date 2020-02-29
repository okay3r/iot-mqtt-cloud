package ccu.iot.cloud.http;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpClientServiceTest {

    @Autowired
    HttpClientService httpClientService;

    @Test
    public void doGet() throws Exception {
        String key = Base64.getEncoder().encodeToString(("f41f3756310c8" + ":" + "MjkxODIwMDMxNDgzMDAwNDUyNDM3MDQzMTAyNjM1MTMwODI").getBytes());
        key = "Basic " + key;
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", key);
        String url = "http://47.93.10.179:8080/api/v3/nodes/emqx@127.0.0.1/subscriptions/?_page=1&_limit=10000";
        String res = httpClientService.doGet(url, null, headers);
        System.out.println(res);
    }
}