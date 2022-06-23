package com.easy.facade.framework.oauth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 异常信息转译
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/20 15:03
 */
@JsonSerialize(using = EasyExtendOauth2ExceptionSerializer.class)
public class EasyOauth2Exception extends OAuth2Exception {

    /**
     * OAuth2Exception
     */
    public static final String ERROR = "error";
    public static final String DESCRIPTION = "error_description";
    public static final String URI = "error_uri";
    public static final String INVALID_REQUEST = "invalid_request";
    public static final String INVALID_CLIENT = "invalid_client";
    public static final String INVALID_GRANT = "invalid_grant";
    public static final String UNAUTHORIZED_CLIENT = "unauthorized_client";
    public static final String UNSUPPORTED_GRANT_TYPE = "unsupported_grant_type";
    public static final String INVALID_SCOPE = "invalid_scope";
    public static final String INSUFFICIENT_SCOPE = "insufficient_scope";
    public static final String INVALID_TOKEN = "invalid_token";
    public static final String REDIRECT_URI_MISMATCH = "redirect_uri_mismatch";
    public static final String UNSUPPORTED_RESPONSE_TYPE = "unsupported_response_type";
    public static final String ACCESS_DENIED = "access_denied";
    /**
     * 其他异常
     */
    public static final String METHOD_NOT_ALLOWED = "method_not_allowed";
    public static final String SERVER_ERROR = "server_error";
    public static final String UNAUTHORIZED = "unauthorized";

    private static final Logger log = LoggerFactory.getLogger(EasyOauth2Exception.class);

    private static final ConcurrentHashMap<String, String> OAUTH_ERROR_MAP = new ConcurrentHashMap<>();

    /*
      映射了一部分
     */
    static {
        OAUTH_ERROR_MAP.put(INVALID_CLIENT, "无效的客户端");
        OAUTH_ERROR_MAP.put(INVALID_GRANT, "无效的授权模式");
        OAUTH_ERROR_MAP.put(INVALID_SCOPE, "权限不足");
        OAUTH_ERROR_MAP.put(UNSUPPORTED_GRANT_TYPE, "不支持的授权模式类型");
        OAUTH_ERROR_MAP.put(ACCESS_DENIED, "拒绝访问");

        OAUTH_ERROR_MAP.put(METHOD_NOT_ALLOWED, "方法不允许访问");
        OAUTH_ERROR_MAP.put(SERVER_ERROR, "服务器内部异常");
        OAUTH_ERROR_MAP.put(UNAUTHORIZED, "未授权");
    }

    /**
     * oAuth2ErrorCode的扩展信息
     */
    private String myExtendMessage;

    public EasyOauth2Exception(String msg, Throwable t) {
        super(msg, t);
    }

    public EasyOauth2Exception(String msg, String oAuth2ErrorCode, Throwable t) {
        super(msg, t);
        log.info("自定义扩展OAuth2异常处理-> msg={}，oAuth2ErrorCode={}", msg, oAuth2ErrorCode);
        String oAuth2ErrorMessage = OAUTH_ERROR_MAP.get(oAuth2ErrorCode);
        this.myExtendMessage = oAuth2ErrorMessage != null ? oAuth2ErrorMessage : msg;
    }

    public String getMyExtendMessage() {
        return myExtendMessage;
    }

    public void setMyExtendMessage(String myExtendMessage) {
        this.myExtendMessage = myExtendMessage;
    }
}
