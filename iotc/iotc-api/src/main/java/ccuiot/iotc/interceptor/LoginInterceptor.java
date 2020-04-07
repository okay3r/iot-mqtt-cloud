package ccuiot.iotc.interceptor;

import ccuiot.iotc.enums.RedisKeyEnum;
import ccuiot.iotc.utils.ApiJsonResult;
import ccuiot.iotc.utils.RedisOperation;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisOperation redisOperation;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = request.getHeader("username");
        String secretKey = request.getHeader("secretKey");
        logger.info("校验秘钥 " + username + " = " + secretKey);
        String key = RedisKeyEnum.USER_KEY.value + ":" + username;
        String trueSecretKey = (String) redisOperation.get(key);
        if (!StringUtils.isEmpty(trueSecretKey) && trueSecretKey.equals(secretKey)) {
            this.redisOperation.expire(key, 1800);
            return true;
        }
        ApiJsonResult errorMsg = ApiJsonResult.errorMsg("信息错误，请在请求头中正确填写用户名和登录时返回的秘钥");
        String json = JSON.toJSONString(errorMsg);

        response.setContentType("text/plain,charset=gbk");
        response.setCharacterEncoding("gbk");
        response.getWriter().write(json);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // logger.info("postHandle execute...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // logger.info("afterCompletion execute...");

    }
}
