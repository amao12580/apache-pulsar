package com.study.apache.pulsar.config.controller.binder;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static com.study.apache.pulsar.config.Constants.*;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/29
 * Time:11:07
 *
 * @author:steven
 */
@Configuration
public class DateBinderAutoConfiguration {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_FULL, LOCALE_INSTANCE_DEFAULT);
        dateFormat.setLenient(false);
        dateFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE_DEFAULT));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
