package com.study.apache.pulsar.protocol.request.enums.device;

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
@ApiModel("请求设备类型对象枚举")
public enum RequestDeviceEnum {
    //ios app
    @ApiModelProperty(value = "ios app")
    APP_IOS,
    //ipad app
    @ApiModelProperty(value = "ipad app")
    APP_IPAD,
    //android app
    @ApiModelProperty(value = "android  app")
    APP_ANDROID,
    //android pad add
    @ApiModelProperty(value = "android pad  app")
    APP_ANDROID_PAD,
    //h5 pc
    @ApiModelProperty(value = "h5 pc")
    PC_H5
}
