package com.study.apache.pulsar.protocol.response;

import com.study.apache.pulsar.protocol.request.BaseRequest;
import com.study.apache.pulsar.protocol.response.body.ResponseBody;
import com.study.apache.pulsar.protocol.response.foot.ResponseFoot;
import com.study.apache.pulsar.protocol.response.header.ResponseHeader;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

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
@ApiModel("基础的响应对象-协议区")
public class BaseResponse<T> implements Serializable {
    @ApiModelProperty(value = "数据头部", required = true)
    ResponseHeader head;

    @ApiModelProperty(value = "数据头部", required = true)
    ResponseBody<T> body;

    @ApiModelProperty(value = "数据头部", required = true)
    ResponseFoot foot;

    public static <M> BaseResponse<M> newInstance(BaseRequest request, ResponseBody<M> data) {
        BaseResponse<M> response = new BaseResponse<>();
        response.setHead(ResponseHeader.builder()
                .id(UUID.randomUUID().toString())
                .algorithm(request.getHead().getAlgorithm())
                .requestId(request.getHead().getId())
                .build());
        response.setBody(data);
        response.setFoot(ResponseFoot.builder()
                .build());
        return response;
    }
}
