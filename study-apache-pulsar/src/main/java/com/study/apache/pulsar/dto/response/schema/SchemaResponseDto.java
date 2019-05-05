package com.study.apache.pulsar.dto.response.schema;

import com.study.apache.pulsar.protocol.response.body.ResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.pulsar.common.schema.SchemaInfo;

/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2019/4/29
 * Time:11:23
 *
 * @author:steven
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Builder
@ApiModel("查询schema结果对象")
public class SchemaResponseDto extends ResponseBody<SchemaResponseDto> {
    @ApiModelProperty(value = "名称", required = true, example = "public")
    SchemaInfo schema;
}
