package ccuiot.iotc.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * 日期转换
 */
@Component
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        String pattern = source.length()==10 ? "yyyy/MM/dd" : "yyyy/MM/dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);

        try {
            return format.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}