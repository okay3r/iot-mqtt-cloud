package ccuiot.iotc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/***
 * AOP记录请求、响应日志
 */
@Component
@Aspect
public class WebLogAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * ccuiot.iotc.controller.*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        StringBuilder sb = new StringBuilder();
        sb.append("REQUEST :  ");
        sb.append(request.getMethod() + " @ ");
        sb.append(request.getRequestURL().toString());
        sb.append(" [" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() +
                "] ");
        sb.append("[ARGS : " + Arrays.toString(joinPoint.getArgs()) + "]");
        // sb.append("[IP : " + request.getRemoteAddr() + "] * ");
        logger.info(sb.toString());

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }

}