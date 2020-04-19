package ccuiot.iotc.config;


import ccuiot.iotc.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * springmvc组件配置类
 * 配置登录拦截器
 */

@Configuration
public class MySpringMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(loginInterceptor);
        //需要拦截的请求
        interceptorRegistration.addPathPatterns("/**");
        //不需要拦截的请求
        interceptorRegistration.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc.html/**");
        interceptorRegistration.excludePathPatterns("/passport/**","/hello");
    }
}
