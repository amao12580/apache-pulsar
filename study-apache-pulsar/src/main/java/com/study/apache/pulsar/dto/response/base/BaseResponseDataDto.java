package com.study.apache.pulsar.dto.response.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/26
 * Time:15:51
 */
@Data
@ApiModel("基础的返回对象-数据区")
public class BaseResponseDataDto<T> implements Serializable {
    @ApiModelProperty(value = "业务消息代码", required = true, example = "0，即成功")
    String code = "0";

    @ApiModelProperty(value = "业务数据内容")
    T message;

    @ApiModelProperty(value = "错误信息")
    Error error;

    public static <M> BaseResponseDataDto<M> newResponse(M message) {
        BaseResponseDataDto<M> dto = new BaseResponseDataDto<>();
        dto.setMessage(message);
        return dto;
    }
}
