package com.study.apache.pulsar.config.controller.request;

import com.study.apache.pulsar.config.AspectjOrder;
import com.study.apache.pulsar.protocol.request.BaseRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
class ControllerRequestAutoConfiguration {

    @Resource
    BaseRequest<Object> baseRequest;

    @Aspect
    @Order(AspectjOrder.REQUEST)
    @Component
    class ControllerRequestParamBeanRegister {

        @Pointcut("execution(public * com.study..controller..*.*(..)) && args(com.study.apache.pulsar.protocol.request.BaseRequest+,..)")
        private void anyPublicController() {
        }

        @Pointcut("@within(org.springframework.web.bind.annotation.RestController)||@within(org.springframework.stereotype.Controller)")
        private void anyAnnotationController() {
        }

        @Pointcut("anyPublicController() && anyAnnotationController()")
        private void pointcut() {
        }

        @Around("pointcut()")
        private Object bind(ProceedingJoinPoint joinPoint) throws Throwable {
            if (joinPoint == null) {
                return null;
            }
            Object[] args = joinPoint.getArgs();
            if (args == null || args.length == 0) {
                return joinPoint.proceed();
            }
            for (Object arg : args) {
                if (arg instanceof BaseRequest) {
                    bind((BaseRequest) arg);
                    break;
                }
            }
            return joinPoint.proceed();
        }

        private void bind(BaseRequest request) {
            BeanUtils.copyProperties(request, baseRequest);
            log.debug(baseRequest.toString());
        }
    }
}