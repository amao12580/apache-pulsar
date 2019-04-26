package com.study.apache.pulsar.dto.response.message;

import com.study.apache.pulsar.dto.response.base.BaseResponseDataDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/26
 * Time:15:22
 */
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("消息发送结果对象")
public class MessageResponseDto extends BaseResponseDataDto {
    @ApiModelProperty(value = "消息Id", required = true, example = "aqo740o1f0gifxpsgl1tqnvg70fj3uoxy5s0fva628i7114rg8yaesef741cfllr")
    String messageId;
}
