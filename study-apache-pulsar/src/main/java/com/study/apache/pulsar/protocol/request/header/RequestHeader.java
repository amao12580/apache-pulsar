package com.study.apache.pulsar.protocol.request.header;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.apache.pulsar.protocol.common.algorithm.AlgorithmTypeEnum;
import com.study.apache.pulsar.protocol.request.enums.device.RequestDeviceEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

import static com.study.apache.pulsar.config.Constants.*;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/26
 * Time:15:23
 *
 * @author steven
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("基础的请求对象-头部")
public class RequestHeader implements Serializable {
    @NotBlank(message = "请求唯一id，不能为空")
    @ApiModelProperty(value = "请求唯一id", required = true, example = "2udu0whi5v1gygev", position = 1)
    String id;

    @NotNull(message = "请求设备类型，不能为空")
    @ApiModelProperty(value = "请求设备类型", required = true, example = "APP_ANDROID_PAD", position = 2)
    RequestDeviceEnum type;

    @NotNull(message = "数据加密算法类型，不能为空")
    @ApiModelProperty(value = "数据加密算法类型", required = true, example = "SIMPLE", position = 3)
    AlgorithmTypeEnum algorithm;

    @NotNull(message = "发起请求时间，不能为空")
    @Past(message = "发起请求时间，不能为晚于当前时间")
    @ApiModelProperty(value = "发起请求时间", required = true, example = "2019-04-28 06:08:15.245", position = 4)
    @JsonFormat(pattern = DATE_FORMAT_FULL, locale = LOCALE_DEFAULT, timezone = TIMEZONE_DEFAULT)
    Date timestamp;
}
