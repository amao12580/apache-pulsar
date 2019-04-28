package com.study.apache.pulsar.protocol.request;

import com.study.apache.pulsar.protocol.request.body.RequestBody;
import com.study.apache.pulsar.protocol.request.foot.RequestFoot;
import com.study.apache.pulsar.protocol.request.header.RequestHeader;
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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel("基础的请求对象-协议区")
public class BaseRequest<T> implements Serializable {
    @ApiModelProperty(value = "数据头部", required = true)
    RequestHeader head;

    @ApiModelProperty(value = "数据头部", required = true)
    RequestBody<T> body;

    @ApiModelProperty(value = "数据头部", required = true)
    RequestFoot foot;
}
