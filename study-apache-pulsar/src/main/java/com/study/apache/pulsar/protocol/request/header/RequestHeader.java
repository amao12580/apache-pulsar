package com.study.apache.pulsar.protocol.request.header;

import com.study.apache.pulsar.protocol.common.algorithm.AlgorithmTypeEnum;
import com.study.apache.pulsar.protocol.request.enums.device.RequestDeviceEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/26
 * Time:15:23
 *
 * @author steven
 */
@Getter
@Setter(AccessLevel.PACKAGE)
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel("基础的请求对象-头部")
public class RequestHeader implements Serializable {
    @ApiModelProperty(value = "请求唯一id", required = true, example = "2udu0whi5v1gygev")
    String id;
    @ApiModelProperty(value = "请求设备类型", required = true, example = "APP_ANDROID_PAD")
    RequestDeviceEnum type;
    @ApiModelProperty(value = "数据加密算法类型", required = true, example = "SIMPLE")
    AlgorithmTypeEnum algorithm;
}
