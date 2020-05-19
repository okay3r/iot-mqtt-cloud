package yx.graduation.elec.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import yx.graduation.utils.ApiJsonResult;
import yx.graduation.utils.RedisOperator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisOperator redisOperator;

    /**
     * 拦截器，在请求头中添加校验信息
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = request.getHeader("username");
        String secret = request.getHeader("secretKey");
        // System.out.println(request.getCharacterEncoding());
        System.out.println(username + ">>>" + secret);
        response.setContentType("text/plain;charset=gbk");
        response.setCharacterEncoding("gbk");
        if (StringUtils.isBlank(username) || StringUtils.isBlank(secret)) {
            response.getWriter().write(JSON.toJSONString(ApiJsonResult.errorMsg("请在header中添加username和secret")));
            return false;
        }
        String redisSecret = (String) this.redisOperator.get("user:" + username);
        if (!secret.equals(redisSecret)) {
            response.getWriter().write(JSON.toJSONString(ApiJsonResult.errorMsg("username或secret错误")));
            return false;
        }
        return true;
    }
}
