package com.study.apache.pulsar.protocol.response.body;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/26
 * Time:15:51
 *
 * @author steven
 */
@Getter
@Setter(AccessLevel.PACKAGE)
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@ApiModel("基础的响应对象-数据区")
public class ResponseBody<T> implements Serializable {
    @ApiModelProperty(value = "返回码", required = true, example = "0，即成功", position = 1)
    String code;

    @ApiModelProperty(value = "返回码描述，在code不为零时有值", position = 2)
    String message;

    @ApiModelProperty(value = "业务数据内容", position = 3)
    T data;

    @ApiModelProperty(value = "错误信息", position = 4)
    Error error;

    public static <M> ResponseBody<M> success(M data) {
        ResponseBody<M> body = new ResponseBody<>();
        body.setCode("0");
        body.setData(data);
        return body;
    }

    public static <M> ResponseBody<M> success() {
        ResponseBody<M> body = new ResponseBody<>();
        body.setCode("0");
        return body;
    }

    @Data
    @ApiModel("错误信息对象")
    class Error {
        @ApiModelProperty(value = "错误信息唯一Id", required = true, example = "2udu0whi5v1gygev", position = 1)
        String id;
        @ApiModelProperty(value = "错误信息明细", position = 2)
        Throwable cause;
    }
}
