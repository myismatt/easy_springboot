package com.easy.facade.constant;

import io.swagger.annotations.ApiModel;

/**
 * HTTP响应码
 * </p>
 *
 * @Author Matt
 * @Date 2021/4/21 10:03
 */
@ApiModel("响应码")
public final class HttpCode {

    /**
     * 成功
     */
    public static final Integer SUCCESS = 200;

    /**
     * 失败
     */
    public static final Integer ERROR = 500;


    /**
     * 未授权
     */
    public static final Integer UNAuthorIZED = 401;

    /**
     * 禁止访问
     */
    public static final Integer FORBIDDEN = 403;

    /**
     * 方法不允许
     */
    public static final Integer METHOD_NOT_ALLOWED = 405;

    private HttpCode() {
        throw new IllegalAccessError("HttpCode.class");
    }
}