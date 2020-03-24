package ccuiot.iotc.utils;

import java.util.Date;

public class LoginAck {
    private String cacheKey;
    private String secretKey;
    private Date time;

    public LoginAck(String username, String key, Date time) {
        this.cacheKey = username;
        this.secretKey = key;
        this.time = time;
    }

    public LoginAck() {
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
