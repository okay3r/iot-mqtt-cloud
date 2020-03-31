package yx.graduation.utils;

import lombok.Data;

import java.util.Date;

@Data
public class LoginAck {

    private String username;
    private String secretKey;
    private Date time;

    public LoginAck(String username, String secretKey, Date time) {
        this.username = username;
        this.secretKey = secretKey;
        this.time = time;
    }
}
