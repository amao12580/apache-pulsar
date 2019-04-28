package com.study.apache.pulsar.protocol.common.algorithm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/26
 * Time:15:25
 *
 * @author steven
 */
@Getter
@ApiModel("数据加密方式枚举")
public enum AlgorithmTypeEnum {
    //SIMPLE
    @ApiModelProperty(value = "明文")
    SIMPLE,
    //AES
    @ApiModelProperty(value = "AES")
    AES
}
