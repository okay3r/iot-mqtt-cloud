package ccuiot.iotc.utils;

public class MyStringUtils {
    public static String getUserName(String cacheKey) {
        return cacheKey.split("_")[1];
    }

    public static Integer getUserId(String cacheKey) {
        String s = cacheKey.split(":")[1];
        return Integer.parseInt(s.split("_")[0]);
    }
}
