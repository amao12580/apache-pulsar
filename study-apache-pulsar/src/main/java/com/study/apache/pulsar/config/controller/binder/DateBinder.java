package com.study.apache.pulsar.config.controller.binder;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.study.apache.pulsar.config.Constants.DATE_FORMAT_FULL;
import static com.study.apache.pulsar.config.Constants.LOCALE_INSTANCE_DEFAULT;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/29
 * Time:11:07
 *
 * @author:steven
 */
@ControllerAdvice
public class DateBinder {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_FULL, LOCALE_INSTANCE_DEFAULT);
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
