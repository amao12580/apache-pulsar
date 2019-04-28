package com.study.apache.pulsar.protocol.response.header;

import com.study.apache.pulsar.protocol.common.algorithm.AlgorithmTypeEnum;
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
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel("基础的响应对象-头部")
public class ResponseHeader implements Serializable {
    @ApiModelProperty(value = "唯一响应id", required = true, example = "9cz65f22uplw0uws")
    String id;
    @ApiModelProperty(value = "唯一请求id", required = true, example = "2udu0whi5v1gygev")
    String requestId;
    @ApiModelProperty(value = "数据加密算法类型", required = true, example = "SIMPLE")
    AlgorithmTypeEnum algorithm;
}
