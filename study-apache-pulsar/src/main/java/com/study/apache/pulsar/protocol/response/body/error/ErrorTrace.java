package com.study.apache.pulsar.protocol.response.body.error;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/29
 * Time:20:12
 *
 * @author:steven
 */
@Data
@Builder
@ApiModel("错误信息对象")
public class ErrorTrace {
    @ApiModelProperty(value = "错误信息唯一Id", required = true, example = "2udu0whi5v1gygev", position = 1)
    String id;
    @ApiModelProperty(value = "错误信息明细", position = 2)
    Throwable cause;
}