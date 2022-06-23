package com.easy.facade.framework.oauth.exception;

import com.easy.facade.constants.HttpCode;

/**
 * 服务异常
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/20 15:14
 */
public class ServerErrorException extends EasyOauth2Exception {
    public ServerErrorException(String msg, Throwable t) {
        super(msg, t);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "server_error";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpCode.ERROR;
    }
}
