package com.study.apache.pulsar.protocol.response.body;

import com.study.apache.pulsar.protocol.response.body.error.ErrorTrace;
import com.study.apache.pulsar.protocol.response.body.error.enums.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

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
    ErrorTrace error;

    public static <M> ResponseBody<M> success(M data) {
        ResponseBody<M> body = new ResponseBody<>();
        body.setCode(String.valueOf(ErrorCodeEnum.OK.getCode()));
        body.setMessage(ErrorCodeEnum.OK.getMessage());
        body.setData(data);
        return body;
    }

    public static <M> ResponseBody<M> success() {
        ResponseBody<M> body = new ResponseBody<>();
        body.setCode(String.valueOf(ErrorCodeEnum.OK.getCode()));
        body.setMessage(ErrorCodeEnum.OK.getMessage());
        return body;
    }

    public static <M> ResponseBody<M> failed(ErrorCodeEnum code, Throwable cause) {
        return failed(code, null, cause);
    }

    public static <M> ResponseBody<M> failed(ErrorCodeEnum code, String message, Throwable cause) {
        ResponseBody<M> body = new ResponseBody<>();
        body.setCode(String.valueOf(code.getCode()));
        body.setMessage(code.getMessage() + (message == null ? "" : (":" + message)));
        body.setError(ErrorTrace
                .builder()
                .id(UUID.randomUUID()
                        .toString())
                .cause(cause)
                .build());
        return body;
    }
}
