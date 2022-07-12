package com.easy.facade.beans.entity;

import com.easy.facade.constants.SystemConsts;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 登录token信息
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/24 16:47
 */
@Data
@ApiModel("登录token信息")
@Builder
public class TokenInfo {

    @ApiModelProperty("请求token")
    private String token;
    @ApiModelProperty("响应ID")
    private String responseId;

    public TokenInfo(String token, String responseId) {
        this.token = SystemConsts.TOKEN_PREFIX + token;
        this.responseId = responseId;
    }
}
