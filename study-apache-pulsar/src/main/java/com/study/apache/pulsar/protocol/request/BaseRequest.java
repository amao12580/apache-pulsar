package com.study.apache.pulsar.protocol.request;

import com.study.apache.pulsar.protocol.request.body.RequestBody;
import com.study.apache.pulsar.protocol.request.foot.RequestFoot;
import com.study.apache.pulsar.protocol.request.header.RequestHeader;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("基础的请求对象-协议区")
public class BaseRequest<T> implements Serializable {
    @NotNull
    @Valid
    @ApiModelProperty(value = "数据头部", required = true, position = 1)
    RequestHeader head;

    @Valid
    @ApiModelProperty(value = "数据内容", required = true, position = 2)
    RequestBody<T> body;

    @NotNull
    @Valid
    @ApiModelProperty(value = "数据签名", required = true, position = 3)
    RequestFoot foot;
}
