package com.study.apache.pulsar.dto.request.tenant;

import com.study.apache.pulsar.protocol.request.body.RequestBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

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
@ApiModel("删除租户请求参数对象")
public class DeleteTenantRequestDto extends RequestBody<DeleteTenantRequestDto> {

    @NotEmpty
    @Size(min = 1, max = 11, message = "名称的个数，需要在1-10之间")
    @ApiModelProperty(value = "名称", required = true, position = 1)
    Set<String> name;
}
