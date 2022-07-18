package com.easy.facade.framework.exception;

import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.enums.HttpStatus;

/**
 * 自定义异常
 * </p>
 *
 * @Author Matt
 * @Date 2021/5/26 17:15
 */
public class CustomizeException extends RuntimeException {

    private final ResultBean<String> result;

    public CustomizeException(HttpStatus httpStatus) {
        super(httpStatus.getReasonPhrase().toString());
        this.result = ResultBean.custom(httpStatus.getValue(), httpStatus.getReasonPhrase());
    }

    public CustomizeException(int code, String message) {
        super(message.toString());
        this.result = ResultBean.custom(code, message);
    }

    public CustomizeException(String message) {
        super(message.toString());
        this.result = ResultBean.error(message);
    }

    public ResultBean<String> getResult() {
        return result;
    }
}