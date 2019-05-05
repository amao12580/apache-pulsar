package com.study.apache.pulsar.controller.message;


import com.study.apache.pulsar.dto.request.tenant.AddTenantRequestDto;
import com.study.apache.pulsar.dto.request.tenant.DeleteTenantRequestDto;
import com.study.apache.pulsar.dto.response.broker.AllActiveBrokersListResponseDto;
import com.study.apache.pulsar.dto.response.namespace.AllNamespacesListResponseDto;
import com.study.apache.pulsar.dto.response.schema.SchemaResponseDto;
import com.study.apache.pulsar.dto.response.tenant.AllTenantsListResponseDto;
import com.study.apache.pulsar.dto.response.topic.AllTopicsListResponseDto;
import com.study.apache.pulsar.protocol.request.BaseRequest;
import com.study.apache.pulsar.protocol.response.BaseResponse;
import com.study.apache.pulsar.protocol.response.body.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.admin.PulsarAdminException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ApiOperation(value = "查询所有集群信息", produces = DEFAULT_PRODUCES)
    @GetMapping(value = "clusters")
    public BaseResponse<AllTenantsListResponseDto> queryAllCluster(@ApiParam(value = "查询请求参数", required = true) BaseRequest<Void> param) throws PulsarAdminException {
        log.info("param:{}", param.toString());
        return BaseResponse.newInstance(param, ResponseBody
                .success(AllTenantsListResponseDto
                        .builder()
                        .name(admin.clusters().getClusters())
                        .build()));
    }

    @ApiOperation(value = "查询所有命名空间信息", produces = DEFAULT_PRODUCES)
    @GetMapping(value = "namespaces")
    public BaseResponse<AllNamespacesListResponseDto> queryAllNamespace(@ApiParam(value = "查询请求参数,租户名称", required = true) BaseRequest<String> param) throws PulsarAdminException {
        log.info("param:{}", param.toString());
        return BaseResponse.newInstance(param, ResponseBody
                .success(AllNamespacesListResponseDto
                        .builder()
                        .name(param.getBody().getData() == null ? null : admin.namespaces().getNamespaces(param.getBody().getData()))
                        .build()));
    }

    @ApiOperation(value = "查询所有topic信息", produces = DEFAULT_PRODUCES)
    @GetMapping(value = "topics")
    public BaseResponse<AllTopicsListResponseDto> queryAllTopic(@ApiParam(value = "查询请求参数,namespace名称", required = true) BaseRequest<String> param) throws PulsarAdminException {
        log.info("param:{}", param.toString());
        return BaseResponse.newInstance(param, ResponseBody
                .success(AllTopicsListResponseDto
                        .builder()
                        .name(param.getBody().getData() == null ? null : admin.topics().getList(param.getBody().getData()))
                        .build()));
    }

    @ApiOperation(value = "查询所有schema信息", produces = DEFAULT_PRODUCES)
    @GetMapping(value = "schema")
    public BaseResponse<SchemaResponseDto> querySchema(@ApiParam(value = "查询请求参数,topic名称", required = true) BaseRequest<String> param) throws PulsarAdminException {
        log.info("param:{}", param.toString());
        return BaseResponse.newInstance(param, ResponseBody
                .success(SchemaResponseDto
                        .builder()
                        .schema(param.getBody().getData() == null ? null : admin.schemas().getSchemaInfo(param.getBody().getData()))
                        .build()));
    }

    @ApiOperation(value = "查询所有active broker信息", produces = DEFAULT_PRODUCES)
    @GetMapping(value = "broker")
    public BaseResponse<AllActiveBrokersListResponseDto> queryActiveBroker(@ApiParam(value = "查询请求参数,cluster名称", required = true) BaseRequest<String> param) throws PulsarAdminException {
        log.info("param:{}", param.toString());
        return BaseResponse.newInstance(param, ResponseBody
                .success(AllActiveBrokersListResponseDto
                        .builder()
                        .name(param.getBody().getData() == null ? null : admin.brokers().getActiveBrokers(param.getBody().getData()))
                        .build()));
    }


    @ApiOperation(value = "保存一个租户", produces = DEFAULT_PRODUCES)
    @PostMapping(value = "tenant")
    public BaseResponse<Boolean> addTenant(@RequestBody @ApiParam(value = "保存租户请求参数", required = true) BaseRequest<AddTenantRequestDto> addTenantRequestDto) throws PulsarAdminException {
        log.info("addTenantRequestDto:{}", addTenantRequestDto.toString());
        AddTenantRequestDto addTenantInfo = addTenantRequestDto.getBody().getData();
        if (admin.tenants().getTenants().contains(addTenantInfo.getName())) {
            admin.tenants().updateTenant(addTenantInfo.getName(), addTenantInfo.getInfo());
        } else {
            admin.tenants().createTenant(addTenantInfo.getName(), addTenantInfo.getInfo());
        }
        return BaseResponse.newInstance(addTenantRequestDto, ResponseBody.success(admin.tenants().getTenants().contains(addTenantInfo.getName())));
    }

    @ApiOperation(value = "删除多个租户", produces = DEFAULT_PRODUCES)
    @DeleteMapping(value = "tenant")
    public BaseResponse<Boolean> deleteTenant(@RequestBody @ApiParam(value = "删除租户请求参数", required = true) BaseRequest<DeleteTenantRequestDto> deleteTenantRequestDto) throws PulsarAdminException {
        log.info("deleteTenantRequestDto:{}", deleteTenantRequestDto.toString());
        DeleteTenantRequestDto deleteTenant = deleteTenantRequestDto.getBody().getData();
        Set<String> names = deleteTenant.getName();
        List<String> deletedNames = new ArrayList<>(names.size());
        Set<String> existed = new HashSet<>(admin.tenants().getTenants());
        for (String name : names) {
            if (!StringUtils.isEmpty(name) && existed.contains(name)) {
                admin.tenants().deleteTenant(name);
                deletedNames.add(name);
            }
        }
        return BaseResponse.newInstance(deleteTenantRequestDto, ResponseBody.success(deletedNames.isEmpty() || !admin.tenants().getTenants().containsAll(deletedNames)));
    }
}
