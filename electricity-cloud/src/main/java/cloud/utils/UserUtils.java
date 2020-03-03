package cloud.utils;

import cloud.entity.User;
import cloud.result.SignInAck;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUtils {
    public static SignInAck getAck(User user) {
        String toKey = user.getUsername() + user.getPassword() + new Date() + "#_e%^Z@Q(w#";
        //redis中的key
        String cacheKey = "login:" + user.getId() + "_" + user.getUsername();
        String secretKey = MD5Utils.getMD5(toKey);
        return new SignInAck(cacheKey, secretKey, new Date());
    }

    public static Boolean checkComplete(User user) {
        Boolean complete = !StringUtils.isEmpty(user.getUsername())
                && user.getUsername().length() > 3
                && !StringUtils.isEmpty(user.getPassword())
                && user.getPassword().length() >= 6
                && !StringUtils.isEmpty(user.getEmail())
                && !StringUtils.isEmpty(user.getPhone())
                && !StringUtils.isEmpty(user.getRemark());
        if (!complete) {
            return false;
        }
        String emailCheck = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Matcher emailMatcher = Pattern.compile(emailCheck).matcher(user.getEmail());
        String phoneCheck = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        Matcher phoneMatcher = Pattern.compile(phoneCheck).matcher(user.getPhone());
        return emailMatcher.matches() && phoneMatcher.matches();
    }
}
