package ccu.iot.cloud.interceptor;

import ccu.iot.cloud.redis.RedisUtils;
import ccu.iot.cloud.result.Result;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String cacheKey = request.getParameter("cacheKey");
        String secretKey = request.getParameter("secretKey");
        logger.info("Intercept Request token # " + cacheKey + " = " + secretKey);
        String trueKey = (String) redisUtils.get(cacheKey);
        if (!StringUtils.isEmpty(trueKey) && trueKey.equals(secretKey)) {
            this.redisUtils.expire(cacheKey, 1800);
            return true;
        }
        Result<String> result = new Result<>(HttpStatus.BAD_REQUEST.value(), "Please login", null);
        String json = JSON.toJSONString(result);
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
