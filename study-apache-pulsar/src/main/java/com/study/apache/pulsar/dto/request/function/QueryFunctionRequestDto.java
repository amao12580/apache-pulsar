package com.study.apache.pulsar.dto.request.function;

import com.study.apache.pulsar.protocol.request.body.RequestBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

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
@ApiModel("查询function请求参数对象")
public class QueryFunctionRequestDto extends RequestBody<QueryFunctionRequestDto> {
    @NotBlank
    @Length(min = 1, max = 11, message = "名称的长度，需要在1-10之间")
    @ApiModelProperty(value = "tenant名称", required = true, example = "public", position = 1)
    String tenant;

    @NotBlank
    @Length(min = 1, max = 11, message = "名称的长度，需要在1-10之间")
    @ApiModelProperty(value = "namespace名称", required = true, example = "default", position = 2)
    String namespace;
}
