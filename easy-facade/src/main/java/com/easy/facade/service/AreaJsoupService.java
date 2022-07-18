package com.easy.facade.service;

import com.easy.facade.beans.model.AreaInfo;
import com.easy.facade.enums.AreaTypeEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 行政区划爬虫
 *
 * <p>
 * 2022/3/28 15:19
 *
 * @Author Matt
 */
@Service
public class AreaJsoupService {

    private final static Logger logger = LoggerFactory.getLogger(AreaJsoupService.class);

    /**
     * 建立连接
     */
    private static Document connect(String url) throws IOException, InterruptedException {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("无效的url");
        }
        try {
            return Jsoup.connect(url).timeout(600000000).get();
        } catch (IOException e) {
            e.printStackTrace();
            Thread.sleep(20000);
            return connect(url);
        }
    }

    public static void setProxyIp() throws IOException {
        try {
            List<String> ipList = new ArrayList<>();
            InputStream is = new FileInputStream("D:\\ips.txt");
            BufferedReader proxyIpReader = new BufferedReader(new InputStreamReader(is));
            String https = "";
            while ((https = proxyIpReader.readLine()) != null) {
                ipList.add(https);
            }
            Random random = new Random();
            int randomInt = random.nextInt(ipList.size());
            String ipPort = ipList.get(randomInt);
            String proxyIp = ipPort.substring(0, ipPort.lastIndexOf(":"));
            String proxyPort = ipPort.substring(ipPort.lastIndexOf(":") + ipPort.length());
            System.setProperty("http.maxRedirects", "50");
            System.getProperties().setProperty("proxySet", "true");
            System.getProperties().setProperty("http.proxyHost", proxyIp);
            System.getProperties().setProperty("http.proxyPort", proxyPort);
            System.out.println("设置代理ip为:" + proxyIp + "端口号为:" + proxyPort);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("重新设置代理ip");
            setProxyIp();
        }

    }

    /**
     * 获取所有的省份（本示例只爬取宁夏回族自治区五级行政区划的信息）
     *
     * @param url 请求地址：http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2021/index.html
     * @return List<AreaInfo>
     */
    public List<AreaInfo> getProvinces(String url, Integer year) throws InterruptedException, IOException {
        List<AreaInfo> provincesAreas = new ArrayList<>();
        Document connect = connect(url);
        Elements rowProvince = connect.select("tr.provincetr");
        for (Element provinceElement : rowProvince) {
            Elements select = provinceElement.select("a");
            for (Element province : select) {
                String code = province.select("a").attr("href");
                String name = province.text();
                AreaInfo areaInfo = new AreaInfo();
                areaInfo.setAreaCode(code.replace(".html", "0000000000"));
                areaInfo.setAreaName(name);
                areaInfo.setParentName(null);
                areaInfo.setParentCode("0");
                areaInfo.setParentCollection("0");
                areaInfo.setAreaType(AreaTypeEnum.province);
                areaInfo.setDataYear(year);
                provincesAreas.add(areaInfo);
                String provinceUrl = url.replace("index.html", code);
                logger.info("- 开始获取" + name + "下属 [市区] 行政区划信息");
                List<AreaInfo> cityAreaCodeList = getCityAreaCode(provinceUrl, code.replace(".html", "0000000000"), areaInfo);
                provincesAreas.addAll(cityAreaCodeList);
            }
        }
        return provincesAreas;
    }

    /**
     * 获取市行政区划信息
     *
     * @param provinceUrl 省份对应的地址
     * @param parentCode  需要爬取的省份行政区划（对于市的父级代码即为省行政区划）
     * @return List<AreaInfo>
     */
    public List<AreaInfo> getCityAreaCode(String provinceUrl, String parentCode, AreaInfo parentAreaInfo) throws InterruptedException, IOException {
        List<AreaInfo> cityAreas = new ArrayList<>();
        Document connect = connect(provinceUrl);
        Elements rowCity = connect.select("tr.citytr");
        for (Element cityElement : rowCity) {
            String name = cityElement.select("td").text();
            String[] split = name.split(" ");
            AreaInfo areaInfo = new AreaInfo();
            areaInfo.setAreaCode(split[0]);
            areaInfo.setAreaName(split[1]);
            areaInfo.setParentName(parentAreaInfo.getAreaName());
            areaInfo.setParentCode(parentCode);
            areaInfo.setParentCollection(parentAreaInfo.getParentCollection() + "," + parentCode);
            areaInfo.setAreaType(AreaTypeEnum.city);
            areaInfo.setDataYear(parentAreaInfo.getDataYear());
            cityAreas.add(areaInfo);
            String cityUrl = provinceUrl.replace(".html", "/" + split[0].substring(0, 4) + ".html");
            logger.info("-- 开始获取" + split[1] + "下属 [区县] 行政区划信息");
            List<AreaInfo> downAreaCodeList = getDownAreaCode(cityUrl, split[0], areaInfo);
            cityAreas.addAll(downAreaCodeList);
        }
        return cityAreas;
    }

    /**
     * 获取区县行政区划信息
     *
     * @param cityUrl    城市对应的地址
     * @param parentCode 需要爬取的市行政区划（对于区县的父级代码即为市行政区划）
     * @return List<AreaInfo>
     */
    public List<AreaInfo> getDownAreaCode(String cityUrl, String parentCode, AreaInfo parentAreaInfo) throws IOException, InterruptedException {
        List<AreaInfo> districtsAreas = new ArrayList<>();
        Document connect = connect(cityUrl);
        Elements rowDown = connect.select("tr.countytr");
        rowDown.parallelStream().forEach(downElement -> {
            String code = downElement.select("a").attr("href");
            String name = downElement.select("td").text();
            String[] split = name.split(" ");
            if (!"市辖区".equals(split[1])) {
                AreaInfo areaInfo = new AreaInfo();
                areaInfo.setAreaCode(split[0]);
                areaInfo.setAreaName(split[1]);
                areaInfo.setParentName(parentAreaInfo.getAreaName());
                areaInfo.setParentCode(parentCode);
                areaInfo.setParentCollection(parentAreaInfo.getParentCollection() + "," + parentCode);
                areaInfo.setAreaType(AreaTypeEnum.districts);
                areaInfo.setDataYear(parentAreaInfo.getDataYear());
                districtsAreas.add(areaInfo);
                String downUrl = cityUrl.replace(parentCode.substring(0, 4) + ".html", code);
                logger.info("--- 开始获取" + split[1] + "下属 [乡镇] 行政区划信息");
//                List<AreaInfo> countryAreaList = getCountryAreaCodeList(downUrl, split[0], areaInfo);
//                districtsAreas.addAll(countryAreaList);
            }
        });
        return districtsAreas;
    }

    /**
     * 获取乡镇行政区划信息
     *
     * @param downUrl    下载地址
     * @param parentCode 父级code
     * @return List<AreaInfo>
     */
    public List<AreaInfo> getCountryAreaCodeList(String downUrl, String parentCode, AreaInfo parentAreaInfo) throws IOException, InterruptedException {
        List<AreaInfo> countryAreas = new ArrayList<>();
        Document connect = connect(downUrl);
        Elements rowDown = connect.select("tr.towntr");
        for (Element downElement : rowDown) {
            String code = downElement.select("a").attr("href");
            String name = downElement.select("td").text();
            String[] split = name.split(" ");
            AreaInfo areaInfo = new AreaInfo();
            areaInfo.setAreaCode(split[0]);
            areaInfo.setAreaName(split[1]);
            areaInfo.setParentName(parentAreaInfo.getAreaName());
            areaInfo.setParentCode(parentCode);
            areaInfo.setParentCollection(parentAreaInfo.getParentCollection() + "," + parentCode);
            areaInfo.setAreaType(AreaTypeEnum.street);
            areaInfo.setDataYear(parentAreaInfo.getDataYear());
            countryAreas.add(areaInfo);
            String countryUrl = downUrl.replace(parentCode.substring(0, 6) + ".html", code);
            logger.info("---- 开始获取" + split[1] + "下属 [村/居委会] 区划信息");
            List<AreaInfo> villageAreaCodeList = getVillageAreaCodeList(countryUrl, split[0], areaInfo);
            countryAreas.addAll(villageAreaCodeList);
        }
        return countryAreas;
    }

    /**
     * 获取村行政区划信息
     *
     * @param countryUrl 下载地址
     * @param parentCode 父级code
     * @return List<AreaInfo>
     */
    public List<AreaInfo> getVillageAreaCodeList(String countryUrl, String parentCode, AreaInfo parentAreaInfo) throws IOException, InterruptedException {
        List<AreaInfo> villageAreaCodeList = new ArrayList<>();
        Document connect = connect(countryUrl);
        Elements rowDown = connect.select("tr.villagetr");
        for (Element downElement : rowDown) {
            String name = downElement.select("td").text();
            String[] split = name.split(" ");
            AreaInfo areaInfo = new AreaInfo();
            areaInfo.setAreaCode(split[0]);
            areaInfo.setTypeCode(Integer.valueOf(split[1]));
            areaInfo.setAreaName(split[2]);
            areaInfo.setParentName(parentAreaInfo.getAreaName());
            areaInfo.setParentCode(parentCode);
            areaInfo.setParentCollection(parentAreaInfo.getParentCollection() + "," + parentCode);
            areaInfo.setAreaType(AreaTypeEnum.village);
            areaInfo.setDataYear(parentAreaInfo.getDataYear());
            villageAreaCodeList.add(areaInfo);
        }
        return villageAreaCodeList;
    }
}