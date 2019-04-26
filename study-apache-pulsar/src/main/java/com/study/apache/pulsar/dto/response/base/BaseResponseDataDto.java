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
public class BaseResponseDataDto<Message> implements Serializable {
    @ApiModelProperty(value = "业务消息代码", required = true, example = "0，即成功")
    String code;

    @ApiModelProperty(value = "业务数据内容")
    Message message;

    @ApiModelProperty(value = "错误信息")
    Error error;
}
