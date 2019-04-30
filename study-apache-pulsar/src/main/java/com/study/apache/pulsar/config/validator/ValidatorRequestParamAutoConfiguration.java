package com.study.apache.pulsar.config.validator;

import com.study.apache.pulsar.config.AspectjOrder;
import com.study.apache.pulsar.protocol.request.BaseRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.Validator;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/29
 * Time:19:12
 *
 * @author:steven
 */
@Slf4j
@EnableAspectJAutoProxy
@Configuration
class ValidatorRequestParamAutoConfiguration {
    @Resource
    Validator validator;

    @Aspect
    @Order(AspectjOrder.VALIDATOR)
    @Component
    public class ControllerRequestParamValidator {

        @Pointcut("execution(public * com.study..controller..*.*(..)) && args(com.study.apache.pulsar.protocol.request.BaseRequest+,..)")
        private void anyPublicController() {
        }

        @Pointcut("@within(org.springframework.web.bind.annotation.RestController)||@within(org.springframework.stereotype.Controller)")
        private void anyAnnotationController() {
        }

        @Pointcut("anyPublicController() && anyAnnotationController()")
        private void pointcut() {
        }

        @Before("pointcut()")
        private void check(JoinPoint joinPoint) {
            if (joinPoint == null) {
                return;
            }
            Object[] args = joinPoint.getArgs();
            if (args == null || args.length == 0) {
                return;
            }
            Object obj;
            for (Object arg : args) {
                obj = arg;
                if (obj instanceof BaseRequest) {
                    doCheck((BaseRequest) obj);
                }
            }
        }


        @Around("pointcut()")
        private Object check(ProceedingJoinPoint joinPoint) throws Throwable {
            if (joinPoint == null) {
                return null;
            }
            Object[] args = joinPoint.getArgs();
            if (args == null || args.length == 0) {
                return joinPoint.proceed();
            }
            Object obj;
            for (Object arg : args) {
                obj = arg;
                if (obj instanceof BaseRequest) {
                    doCheck((BaseRequest) obj);
                }
            }
            return joinPoint.proceed();
        }

        private void doCheck(BaseRequest request) {
            doCheckOne(request);
            if (request != null && request.getBody() != null) {
                Object data = request.getBody().getData();
                if (data == null) {
                    return;
                }
                if (data instanceof Collection) {
                    Collection collection = (Collection) data;
                    for (Object object : collection) {
                        doCheckOne(object);
                    }

                } else if (data instanceof Map) {
                    Map map = (Map) data;
                    Set set = map.keySet();
                    for (Object object : set) {
                        doCheckOne(map.get(object));
                    }
                } else {
                    doCheckOne(data);
                }
            }
        }

        private void doCheckOne(Object object) {
            if (object == null) {
                return;
            }
            validator.validate(object).forEach(violation -> {
                throw new IllegalArgumentException("字段名:" + violation.getPropertyPath() + "," + violation.getMessage());
            });
        }
    }
}