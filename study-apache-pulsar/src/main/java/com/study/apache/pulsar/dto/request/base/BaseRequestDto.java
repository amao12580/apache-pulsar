package com.study.apache.pulsar.dto.request.base;

import com.study.apache.pulsar.dto.base.algorithm.AlgorithmTypeEnum;
import com.study.apache.pulsar.dto.request.base.enums.DeviceRequestEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/26
 * Time:15:23
 */
@Data
@ApiModel("基础的请求对象-封装区")
public class BaseRequestDto<Object extends BaseRequestDataDto> implements Serializable {
    @ApiModelProperty(value = "请求唯一id", required = true, example = "2udu0whi5v1gygev")
    String id;
    @ApiModelProperty(value = "请求设备类型", required = true, example = "APP_ANDROID_PAD")
    DeviceRequestEnum type;
    @ApiModelProperty(value = "数据加密算法类型", required = true, example = "SIMPLE")
    AlgorithmTypeEnum algorithm;
    @ApiModelProperty(value = "数据签名", required = true, example = "md5:ts4w2oto5kqwpg6fmsyvyd2tl3m9yqg4pz0ecd8hm9lpba77yu2ff4jglp3hx7ki")
    String sign;
    @ApiModelProperty(value = "业务数据")
    Object data;
}
