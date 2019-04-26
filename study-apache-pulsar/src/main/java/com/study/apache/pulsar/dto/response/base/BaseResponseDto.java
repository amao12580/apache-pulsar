package com.study.apache.pulsar.dto.response.base;

import com.study.apache.pulsar.dto.base.algorithm.AlgorithmTypeEnum;
import com.study.apache.pulsar.dto.request.base.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/26
 * Time:15:23
 */
@Data
@ApiModel("基础的返回对象-封装区")
public class BaseResponseDto<T> implements Serializable {
    @ApiModelProperty(value = "唯一响应id", required = true, example = "9cz65f22uplw0uws")
    String id;
    @ApiModelProperty(value = "唯一请求id", required = true, example = "2udu0whi5v1gygev")
    String requestId;
    @ApiModelProperty(value = "数据加密算法类型", required = true, example = "SIMPLE")
    AlgorithmTypeEnum algorithm;
    @ApiModelProperty(value = "数据签名", required = true, example = "md5:ts4w2oto5kqwpg6fmsyvyd2tl3m9yqg4pz0ecd8hm9lpba77yu2ff4jglp3hx7ki")
    String sign;
    @ApiModelProperty(value = "业务数据")
    BaseResponseDataDto<T> data;

    public static <M> BaseResponseDto<M> newResponse(BaseRequestDto request, BaseResponseDataDto<M> data) {
        BaseResponseDto<M> dto = new BaseResponseDto<>();
        dto.setRequestId(request.getId());
        dto.setData(data);
        dto.setId(UUID.randomUUID().toString());
        return dto;
    }
}
