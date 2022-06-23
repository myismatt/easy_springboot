package com.easy.utils.io;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数工具类
 * </p>
 *
 * @Author Matt
 * @Date 2022/1/4 13:52
 */
public class RequestUtils {

    private RequestUtils() {
        throw new IllegalAccessError();
    }

    /**
     * 把request转换成json数据
     *
     * @param request 请求
     * @return String
     */
    public static String readReqStr(HttpServletRequest request) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (null != reader) {
                    reader.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 把request转换成xml数据
     *
     * @param request 请求
     * @return String
     */
    public static String readReqXml(HttpServletRequest request) {
        String inputLine;
        StringBuilder notifyXml = new StringBuilder();
        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                notifyXml.append(inputLine);
            }
            request.getReader().close();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return notifyXml.toString();

    }


    /**
     * 把request转换成map数据
     *
     * @param request 请求
     * @return Map<String, String>
     */
    public static Map<String, String> getRequestParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String o : requestParams.keySet()) {
            String[] values = (String[]) requestParams.get(o);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(o, valueStr);
        }
        return params;
    }


}
