package ccuiot.iotc.utils;

import lombok.Data;

import java.util.Date;

@Data
public class LoginAck {

    private String cacheKey;
    private String secretKey;
    private Date time;

    public LoginAck(String cacheKey, String secretKey, Date time) {
        this.cacheKey = cacheKey;
        this.secretKey = secretKey;
        this.time = time;
    }
}
