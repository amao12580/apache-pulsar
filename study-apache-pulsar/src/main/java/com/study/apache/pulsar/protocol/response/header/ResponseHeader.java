package com.study.apache.pulsar.protocol.response.header;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.apache.pulsar.protocol.common.algorithm.AlgorithmTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

import static com.study.apache.pulsar.config.Constants.*;

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
    @ApiModelProperty(value = "唯一响应id", required = true, example = "9cz65f22uplw0uws", position = 1)
    String id;
    @ApiModelProperty(value = "唯一请求id", required = true, example = "2udu0whi5v1gygev", position = 2)
    String requestId;
    @ApiModelProperty(value = "数据加密算法类型", required = true, example = "SIMPLE", position = 3)
    AlgorithmTypeEnum algorithm;
    @ApiModelProperty(value = "响应时间", required = true, example = "2019-04-28 06:08:15.582", position = 4)
    @JsonFormat(pattern = DATE_FORMAT_FULL, locale = LOCALE_DEFAULT, timezone = TIMEZONE_DEFAULT)
    Date timestamp;
}
