package com.study.apache.pulsar.config.controller.request;

import com.study.apache.pulsar.config.AspectjOrder;
import com.study.apache.pulsar.protocol.request.BaseRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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

    @Aspect
    @Order(AspectjOrder.REQUEST)
    @Component
    class ControllerRequestParamBeanRegister implements ApplicationContextAware {

        private final static String REQUEST_KEY = "baseRequest";

        private AbstractApplicationContext context;

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
        private Object register(ProceedingJoinPoint joinPoint) throws Throwable {
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
                    register((BaseRequest) obj);
                    break;
                }
            }
            return joinPoint.proceed();
        }

        private void register(BaseRequest request) {
            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
            if (!beanFactory.containsBeanDefinition(REQUEST_KEY)) {
                GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
                beanDefinition.setBeanClass(BaseRequest.class);
                MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
                mutablePropertyValues.addPropertyValue("head", request.getHead());
                mutablePropertyValues.addPropertyValue("body", request.getBody());
                mutablePropertyValues.addPropertyValue("foot", request.getFoot());
                beanDefinition.setPropertyValues(mutablePropertyValues);
                beanDefinition.setScope(GenericBeanDefinition.SCOPE_PROTOTYPE);
                beanFactory.registerBeanDefinition(REQUEST_KEY, beanDefinition);
                log.info("BaseRequest has auto register for prototype bean.");
            } else {
                BaseRequest requestBean = context.getBean(BaseRequest.class);
                requestBean.setHead(request.getHead());
                requestBean.setBody(request.getBody());
                requestBean.setFoot(request.getFoot());
                log.info("BaseRequest has auto updated for prototype bean.");
            }
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            context = (AbstractApplicationContext) applicationContext;
        }
    }
}