package ccuiot.iotc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    public static final int DEFAULT_PAGE_SIZE = 20;

    public static final String USER_KEY = "user";

}
