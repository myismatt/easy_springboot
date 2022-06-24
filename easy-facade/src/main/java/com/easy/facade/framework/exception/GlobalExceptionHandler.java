package com.easy.facade.framework.exception;

import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.enums.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.Set;

/**
 * 全局异常处理
 * </p>
 *
 * @Author Matt
 * @Date 2021/5/31 11:00
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 基础异常
     */
    @ExceptionHandler(Exception.class)
    public ResultBean<String> handleException(Exception e) {
        logger.error("基础异常-> e={}", e.getMessage());
        e.printStackTrace();
        return ResultBean.custom(HttpStatus.ERROR);
    }

    /**
     * 权限异常拦截
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResultBean<String> handleAuthorizationException(AccessDeniedException e) {
        logger.error("权限异常拦截-> e={}", e.getMessage());
        e.printStackTrace();
        return ResultBean.custom(HttpStatus.UNAUTHORIZED);
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(value = CustomException.class)
    public ResultBean<String> handleCustom(CustomException e) {
        logger.error("自定义异常拦截-> e={}", e.getMessage());
        e.printStackTrace();
        return e.getResult();
    }

    /**
     * 参数校验异常拦截
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultBean<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        logger.error("参数校验异常拦截-> e={}", e.getMessage());
        e.printStackTrace();
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return ResultBean.error(objectError.getDefaultMessage());
    }

    /**
     * 参数异常拦截 （方法上的）
     **/
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultBean<String> constraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            String message = constraintViolation.getMessage();
            builder.append(message);
        }
        logger.error("接口-> " + request.getServletPath(), e.toString());
        e.printStackTrace();
        return ResultBean.error(builder.toString());
    }

}
