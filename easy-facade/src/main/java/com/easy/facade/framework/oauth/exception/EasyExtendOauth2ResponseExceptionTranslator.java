package com.easy.facade.framework.oauth.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * 认证异常转换处理
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/20 14:57
 */
@Component
public class EasyExtendOauth2ResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    private final ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {

        // Try to extract a SpringSecurityException from the stacktrace
        Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);
        // 异常栈获取 OAuth2Exception 异常
        OAuth2Exception ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(OAuth2Exception.class, causeChain);
        // 否则服务器内部错误
        return handleOauthException(Objects.requireNonNullElseGet(ase, () -> new ServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e)));

    }

    private ResponseEntity<OAuth2Exception> handleOauthException(OAuth2Exception e) throws IOException {
        int status = e.getHttpErrorCode();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        if (status == HttpStatus.UNAUTHORIZED.value() || (e instanceof InsufficientScopeException)) {
            headers.set("WWW-Authenticate", String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getSummary()));
        }

        // 使用自定义扩展OAuth2Exception
        e = new EasyOauth2Exception(e.getMessage(), e.getOAuth2ErrorCode(), e);
        return new ResponseEntity<OAuth2Exception>(e, headers, HttpStatus.valueOf(status));
    }

}
