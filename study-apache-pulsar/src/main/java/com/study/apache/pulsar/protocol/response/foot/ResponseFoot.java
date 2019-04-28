package com.study.apache.pulsar.protocol.response.foot;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
@Setter(AccessLevel.PACKAGE)
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel("基础的响应对象-签名区")
public class ResponseFoot implements Serializable {
    @ApiModelProperty(value = "数据签名", required = true, example = "md5:ts4w2oto5kqwpg6fmsyvyd2tl3m9yqg4pz0ecd8hm9lpba77yu2ff4jglp3hx7ki")
    String sign;
}
