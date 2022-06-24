package com.easy.facade.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.easy.facade.constants.HttpCode;

/**
 * 响应状态枚举
 * </p>
 *
 * @Author Matt
 * @Date 2021/5/31 16:54
 */
public enum HttpStatus implements IEnum<Integer> {

    /**
     * 响应
     */
    SUCCESS(HttpCode.SUCCESS, "成功"),
    ERROR(HttpCode.ERROR, "错误"),
    UNAUTHORIZED(HttpCode.UNAUTHORIZED, "无权访问"),
    FORBIDDEN(HttpCode.FORBIDDEN, "禁止访问"),
    UNKNOWN_EXCEPTION(HttpCode.ERROR, "未知异常"),
    TOKEN_EXCEPTION(HttpCode.ERROR, "签名异常"),
    TOKEN_EXPIRED(HttpCode.FORBIDDEN, "签名过期,禁止访问"),
    ;

    private final Integer value;
    private final String reasonPhrase;

    private HttpStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}
