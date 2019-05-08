package com.study.apache.pulsar.protocol.request.body;

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
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("基础的请求对象-数据区")
public class RequestBody<T> implements Serializable {
    @ApiModelProperty(value = "业务数据")
    T data;

    public static <M> RequestBody<M> newInstance(M data) {
        RequestBody<M> body = new RequestBody<>();
        body.setData(data);
        return body;
    }
}
