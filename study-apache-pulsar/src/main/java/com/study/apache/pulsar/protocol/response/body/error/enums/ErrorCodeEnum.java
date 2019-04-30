package com.study.apache.pulsar.protocol.response.body.error.enums;

import io.swagger.annotations.ApiModel;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/29
 * Time:20:05
 *
 * @author:steven
 */
@ApiModel("基础的响应对象-通用错误代码")
public enum ErrorCodeEnum {
    //成功
    OK(0, "成功"),

    //未知错误
    UNKNOWN_ERROR(10000, "未知错误"),

    //参数错误
    PARAM_INVALID(10001, "参数错误");

    static {
        checkRepeat();
    }

    private int code;

    private String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private static void checkRepeat() {
        ErrorCodeEnum[] array = ErrorCodeEnum.values();
        Set<Integer> codes = new HashSet<>(array.length);
        for (ErrorCodeEnum item : array) {
            if (!codes.add(item.getCode())) {
                throw new IllegalArgumentException("错误代码出现重复定义:" + item.getCode());
            }
        }
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
