package com.easy.facade.beans.base;

import com.easy.facade.enums.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 响应数据封装
 * </p>
 *
 * @Author Matt
 * @Date 2021/3/12 15:56
 */
@ApiModel("响应数据")
public class ResultBean<T> implements Serializable {

    /**
     * 成功信息
     */
    public static final int SUCCESS = 200;
    public static final String SUCCESS_MSG = "成功";

    /**
     * 失败信息
     */
    public static final int ERROR = 500;
    public static final String ERROR_MSG = "error";

    /**
     * 响应码
     */
    @ApiModelProperty("响应码")
    private int code;

    /**
     * 响应信息
     */
    @ApiModelProperty("响应信息")
    private String msg;

    /**
     * 响应数据
     */
    @ApiModelProperty("响应数据")
    private T data;

    /**
     * 接口响应时间,此值不会主动返回
     */
    @ApiModelProperty("接口响应时间")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String responseTime;

    public static <T> ResultBean<T> success() {
        return restResult(SUCCESS, SUCCESS_MSG, null);
    }

    public static <T> ResultBean<T> success(String msg) {
        return restResult(SUCCESS, msg, null);
    }

    public static <T> ResultBean<T> success(T data) {
        return restResult(SUCCESS, SUCCESS_MSG, data);
    }

    public static <T> ResultBean<T> success(String msg, T data) {
        return restResult(SUCCESS, msg, data);
    }

    public static <T> ResultBean<T> error() {
        return restResult(ERROR, ERROR_MSG, null);
    }

    public static <T> ResultBean<T> error(String msg) {
        return restResult(ERROR, msg, null);
    }

    public static <T> ResultBean<T> error(String msg, T data) {
        return restResult(ERROR, msg, data);
    }

    public static <T> ResultBean<T> custom(int code, String msg) {
        return restResult(code, msg, null);
    }

    public static <T> ResultBean<T> custom(int code, String msg, T data) {
        return restResult(code, msg, data);
    }

    public static ResultBean<String> custom(HttpStatus httpStatus) {
        return restResult(httpStatus.getValue(), httpStatus.getReasonPhrase(), null);
    }

    private static <T> ResultBean<T> restResult(int code, String msg, T data) {
        ResultBean<T> apiResult = new ResultBean<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }
}
