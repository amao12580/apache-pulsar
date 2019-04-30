package com.study.apache.pulsar.controller.message;


import com.study.apache.pulsar.dto.request.tenant.AddTenantRequestDto;
import com.study.apache.pulsar.dto.response.tenant.AllTenantsListResponseDto;
import com.study.apache.pulsar.protocol.request.BaseRequest;
import com.study.apache.pulsar.protocol.response.BaseResponse;
import com.study.apache.pulsar.protocol.response.body.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.admin.PulsarAdminException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.study.apache.pulsar.config.Constants.DEFAULT_PRODUCES;


/**
 * Created with IntelliJ IDEA.
 * User:ChengLiang
 * Date:2029/4/26
 * Time:15:17
 *
 * @author steven
 */
@Slf4j
@Api(value = "消息管理控制器", tags = {"消息队列管理接口"})
@RestController
@RequestMapping("/message/queue/admin/")
public class MessageAdminController {

    @Resource
    PulsarAdmin admin;

//    @Resource
//    Validator validator;

    @ApiOperation(value = "查询所有租户信息", produces = DEFAULT_PRODUCES)
    @GetMapping(value = "tenants")
    public BaseResponse<AllTenantsListResponseDto> queryAllTenant(@ApiParam(value = "查询请求参数", required = true) BaseRequest<Void> param) throws PulsarAdminException {
        log.info("param:{}", param.toString());
        return BaseResponse.newInstance(param, ResponseBody
                .success(AllTenantsListResponseDto
                        .builder()
                        .name(admin.tenants().getTenants())
                        .build()));
    }


    @ApiOperation(value = "添加一个租户", produces = DEFAULT_PRODUCES)
    @PostMapping(value = "tenant")
    public BaseResponse<Boolean> addTenant(@RequestBody @ApiParam(value = "添加租户请求参数", required = true) BaseRequest<AddTenantRequestDto> addTenantRequestDto) throws PulsarAdminException {
        log.info("addTenantRequestDto:{}", addTenantRequestDto.toString());
        AddTenantRequestDto addTenantInfo = addTenantRequestDto.getBody().getData();
//        Set<ConstraintViolation<AddTenantRequestDto>> validate = validator.validate(addTenantInfo);
//        validate.forEach(violation -> {
//            log.error(violation.getMessage());
//            throw new BadRequestException(violation.getMessage());
//        });
        if (admin.tenants().getTenants().contains(addTenantInfo.getName())) {
            throw new IllegalArgumentException("tenant " + addTenantInfo.getName() + " already existed.");
        }
        admin.tenants().createTenant(addTenantInfo.getName(), addTenantInfo.getInfo());
        return BaseResponse.newInstance(addTenantRequestDto, ResponseBody.success(admin.tenants().getTenants().contains(addTenantInfo.getName())));
    }
}
