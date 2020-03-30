package elec.cloud.result;

import java.util.Date;

public class SignInAck {
    private String cacheKey;
    private String secretKey;
    private Date time;

    public SignInAck(String username, String key, Date time) {
        this.cacheKey = username;
        this.secretKey = key;
        this.time = time;
    }

    public SignInAck() {
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
