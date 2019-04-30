package com.study.apache.pulsar.config.controller.exception;

import com.study.apache.pulsar.protocol.request.BaseRequest;
import com.study.apache.pulsar.protocol.response.BaseResponse;
import com.study.apache.pulsar.protocol.response.body.ResponseBody;
import com.study.apache.pulsar.protocol.response.body.error.enums.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.shade.javax.ws.rs.BadRequestException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/29
 * Time:11:07
 *
 * @author:steven
 */
@Slf4j
@Configuration
@RestControllerAdvice
public class ExceptionResponseAutoConfiguration implements ApplicationContextAware {

    private AbstractApplicationContext context;

    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse paramInvalid(Exception e) {
        log.error(e.getMessage(), e);
        return BaseResponse.newInstance(context.getBean(BaseRequest.class),
                ResponseBody.failed(ErrorCodeEnum.PARAM_INVALID, e.getMessage(), e));
    }


    @ExceptionHandler({Throwable.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse defaultHandler(Throwable cause) {
        log.error(cause.getMessage(), cause);
        return BaseResponse.newInstance(context.getBean(BaseRequest.class),
                ResponseBody.failed(ErrorCodeEnum.UNKNOWN_ERROR, cause.getMessage(), cause));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = (AbstractApplicationContext) applicationContext;
    }
}
