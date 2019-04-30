package com.study.apache.pulsar.protocol.request.foot;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
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
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("基础的请求对象-签名区")
public class RequestFoot implements Serializable {
    @NotBlank
    @ApiModelProperty(value = "数据签名", required = true, example = "md5:ts4w2oto5kqwpg6fmsyvyd2tl3m9yqg4pz0ecd8hm9lpba77yu2ff4jglp3hx7ki")
    String sign;
}
