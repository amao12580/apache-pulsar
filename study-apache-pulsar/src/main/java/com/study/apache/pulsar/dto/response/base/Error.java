package com.study.apache.pulsar.dto.response.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/26
 * Time:16:26
 */
@Data
@ApiModel("错误信息对象")
class Error {
    @ApiModelProperty(value = "错误信息唯一Id", required = true, example = "2udu0whi5v1gygev")
    String id;
    @ApiModelProperty(value = "错误信息明细")
    Object detail;
}