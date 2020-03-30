package elec.cloud.interceptor;

import elec.cloud.redis.RedisUtils;
import elec.cloud.result.Result;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String cacheKey = request.getParameter("cacheKey");
        String secretKey = request.getParameter("secretKey");
        String trueKey = (String) this.redisUtils.get(cacheKey);
        if (trueKey == null || !secretKey.equals(trueKey)) {
            logger.info("CHECK FAILED" + cacheKey + " = " + secretKey);
            String json = JSON.toJSONString(new Result<>(HttpStatus.BAD_REQUEST.value(), "请登录", null));
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(json);
            return false;
        }
        logger.info("CHECK SUCCESS   " + cacheKey + " = " + secretKey);
        redisUtils.expire(cacheKey, 1800);
        return true;
    }
}
