package com.study.apache.pulsar.dto.request.message;

import com.study.apache.pulsar.dto.request.message.enums.MessageRequestEnum;
import com.study.apache.pulsar.protocol.request.body.RequestBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/26
 * Time:15:22
 *
 * @author steven
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("消息发送请求参数对象")
public class MessageRequestDto extends RequestBody<MessageRequestDto> {
    @ApiModelProperty(value = "消息类型", required = true, example = "SIMPLE", position = 1)
    MessageRequestEnum type;
    @ApiModelProperty(value = "消息接收者", required = true, example = "测试消息-接收者", position = 2)
    String destination;
    @ApiModelProperty(value = "标题", example = "测试消息-标题", position = 3)
    String title;
    @ApiModelProperty(value = "内容", required = true, example = "测试消息-内容", position = 4)
    String content;
}
