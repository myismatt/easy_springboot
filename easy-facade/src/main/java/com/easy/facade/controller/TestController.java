package com.easy.facade.controller;

import com.easy.utils.date.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/14 15:35
 */
@RestController
@RequestMapping("test")
@Api(tags = "测试")
public class TestController {

    @GetMapping("time")
    @ApiOperation(value = "获取当前时间", httpMethod = "GET")
    public String getTime() {
        return DateUtils.getTime();
    }
}
