package com.study.apache.pulsar.dto.request.tenant;

import com.study.apache.pulsar.protocol.request.body.RequestBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.pulsar.common.policies.data.TenantInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@ApiModel("添加租户请求参数对象")
public class AddTenantRequestDto extends RequestBody<AddTenantRequestDto> {
    @NotBlank
    @Length(min = 1, max = 10, message = "名称的长度，需要在1-10之间")
    @ApiModelProperty(value = "名称", required = true, example = "test", position = 1)
    String name;

    @NotNull
    @ApiModelProperty(value = "明细", required = true, position = 2)
    TenantInfo info;
}
