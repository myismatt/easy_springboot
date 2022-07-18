package com.easy.facade.controller;

import com.easy.facade.beans.model.AreaInfo;
import com.easy.facade.service.AreaInfoService;
import com.easy.facade.service.AreaJsoupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * 行政区划
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/14 15:58
 */
@RestController
@RequestMapping("admin/area")
@Api(tags = "行政区划")
public class AreaInfoController {

    private final AreaJsoupService areaJsoupService;
    private final AreaInfoService areaInfoService;

    public AreaInfoController(AreaJsoupService areaJsoupService, AreaInfoService areaInfoService) {
        this.areaJsoupService = areaJsoupService;
        this.areaInfoService = areaInfoService;
    }

    @GetMapping("pull")
    @ApiOperation(value = "拉取行政区划数据", httpMethod = "GET")
    public void grabArea(@RequestParam("year") Integer year, @RequestParam("url") String url) throws InterruptedException, IOException {
//        String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2021/index.html";
//        String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/index.html";
//        String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/index.html";
//        String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html";
//        String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/index.html";
//        String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/index.html";
        List<AreaInfo> sysAreas = areaJsoupService.getProvinces(url, year);
        areaInfoService.saveBatch(sysAreas, 1000);
    }
}