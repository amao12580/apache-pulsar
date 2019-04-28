package com.study.apache.pulsar.controller.message;


import com.study.apache.pulsar.protocol.request.BaseRequest;
import com.study.apache.pulsar.protocol.response.BaseResponse;
import com.study.apache.pulsar.protocol.response.body.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.admin.PulsarAdminException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
@RequestMapping("/message//queue/admin")
public class MessageAdminController {

    @Resource
    PulsarAdmin admin;

    @ApiOperation(value = "查询所有租户信息", produces = DEFAULT_PRODUCES)
    @GetMapping(value = "/tenants")
    public BaseResponse<List<String>> tenants(@ApiParam(value = "查询请求参数", required = true) BaseRequest<Void> params) throws PulsarAdminException {
//        admin.tenants().createTenant("001", TenantInfo);
        return BaseResponse.newInstance(params, ResponseBody.success(admin.tenants().getTenants()));
    }
}
