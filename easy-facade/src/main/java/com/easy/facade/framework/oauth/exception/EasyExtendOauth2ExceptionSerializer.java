package com.easy.facade.framework.oauth.exception;

import com.easy.facade.beans.base.ResultBean;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 异常序列化处理
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/20 15:04
 */
@Component
public class EasyExtendOauth2ExceptionSerializer extends StdSerializer<EasyOauth2Exception> {

    private static final Logger log = LoggerFactory.getLogger(EasyExtendOauth2ExceptionSerializer.class);

    public EasyExtendOauth2ExceptionSerializer() {
        super(EasyOauth2Exception.class);
    }


    @Override
    public void serialize(EasyOauth2Exception e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        log.info("EasyExtendOauth2ExceptionSerializer返回格式序列化处理-> e={}", e.getMessage());
        // 自定义返回格式内容
        ResultBean<String> resultBean = new ResultBean<>();
        resultBean.setMsg(e.getMyExtendMessage());
        jsonGenerator.writeObject(resultBean);
    }
}
