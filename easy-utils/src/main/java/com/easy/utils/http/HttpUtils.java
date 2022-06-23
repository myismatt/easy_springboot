package com.easy.utils.http;

import com.alibaba.fastjson.JSONObject;
import com.easy.utils.json.FastJsonUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;

/**
 * http发送方法
 * </p>
 *
 * @Author Matt
 * @Date 2021/1/10 15:38
 */
public final class HttpUtils {
    private HttpUtils() {
        throw new IllegalAccessError("HttpUtils.class");
    }

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        return sendGet(url, param, "UTF8");
    }

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url         发送请求的 URL
     * @param param       请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param contentType 编码类型
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param, String contentType) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), contentType));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        }
        catch (ConnectException e) {
            throw new RuntimeException("调用HttpUtils.sendGet ConnectException, url=" + url + ",param=" + param, e);
        }
        catch (SocketTimeoutException e) {
            throw new RuntimeException("调用HttpUtils.sendGet SocketTimeoutException, url=" + url + ",param=" + param, e);
        }
        catch (IOException e) {
            throw new RuntimeException("调用HttpUtils.sendGet IOException, url=" + url + ",param=" + param, e);
        }
        catch (Exception e) {
            throw new RuntimeException("调用HttpsUtil.sendGet Exception, url=" + url + ",param=" + param, e);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (Exception ex) {
                throw new RuntimeException("调用in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        }
        catch (ConnectException e) {
            throw new RuntimeException("调用HttpUtils.sendPost ConnectException, url=" + url + ",param=" + param, e);
        }
        catch (SocketTimeoutException e) {
            throw new RuntimeException("调用HttpUtils.sendPost SocketTimeoutException, url=" + url + ",param=" + param,
                    e);
        }
        catch (IOException e) {
            throw new RuntimeException("调用HttpUtils.sendPost IOException, url=" + url + ",param=" + param, e);
        }
        catch (Exception e) {
            throw new RuntimeException("调用HttpsUtil.sendPost Exception, url=" + url + ",param=" + param, e);
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ex) {
                throw new RuntimeException("调用in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
    }

    public static String sendSSLPost(String url, String param) {
        StringBuilder result = new StringBuilder();
        String urlNameString = url + "?" + param;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            URL console = new URL(urlNameString);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String ret = "";
            while ((ret = br.readLine()) != null) {
                if (ret != null && !"".equals(ret.trim())) {
                    result.append(new String(ret.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                }
            }
            conn.disconnect();
            br.close();
        }
        catch (ConnectException e) {
            throw new RuntimeException("调用HttpUtils.sendSSLPost ConnectException, url=" + url + ",param=" + param, e);
        }
        catch (SocketTimeoutException e) {
            throw new RuntimeException("调用HttpUtils.sendSSLPost SocketTimeoutException, url=" + url + ",param=" + param,
                    e);
        }
        catch (IOException e) {
            throw new RuntimeException("调用HttpUtils.sendSSLPost IOException, url=" + url + ",param=" + param, e);
        }
        catch (Exception e) {
            throw new RuntimeException("调用HttpsUtil.sendSSLPost Exception, url=" + url + ",param=" + param, e);
        }
        return result.toString();
    }

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get ( key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuilder buffer = new StringBuilder();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new TrustAnyTrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes(StandardCharsets.UTF_8));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            httpUrlConn.disconnect();
            jsonObject = FastJsonUtils.jsonToObject(buffer.toString(), JSONObject.class);
        }
        catch (ConnectException ce) {
            throw new RuntimeException("Weixin server connection timed out.");
        }
        catch (Exception e) {
            throw new RuntimeException("https request error:{}", e);
        }
        return jsonObject;
    }

    public static String doPost(String url, JSONObject json) {
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            StringEntity entity = new StringEntity(json.toString(), "utf-8");
            // 发送json数据需要设置contentType
            entity.setContentType("application/json");
            post.setEntity(entity);
            response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回json格式
                return EntityUtils.toString(response.getEntity());
            }
        }
        catch (Exception e) {
            throw new RuntimeException("通讯异常，异常信息:[{}]", e);
        }
        finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    private static class TrustAnyTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

}
