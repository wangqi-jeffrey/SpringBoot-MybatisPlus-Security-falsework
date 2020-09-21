package com.jeffrey.utils;

import com.jeffrey.context.constant.CommonConstant;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Description: Http工具类
 *
 * @author WQ
 * @date 2020/8/21 11:20 AM
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String get(String url) throws Exception {
        return get(url, null, null);
    }

    public static String get(String url, Map<String, String> params) throws Exception {
        return get(url, params, null);
    }

    /**
     * get
     * @param url
     * @param params
     * @param charset
     * @return String
     * @throws Exception
     */
    public static String get(String url, Map<String, String> params, String charset) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        try {
            if (MapUtils.isNotEmpty(params)) {
                if (!url.contains("?")) {
                    url += "?";
                }

                if (!url.endsWith("&") && !url.endsWith("?")) {
                    url += "&";
                }
                url = String.format("%s%s", url, toQueryString(params));
            }

            HttpGet httpGet = new HttpGet(url);

            logger.info("[GET]request sent, url=" + url);

            long start = System.currentTimeMillis();

            response = httpClient.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();

            long cost = System.currentTimeMillis() - start;
            String result = null;
            if (StringUtil.isBlank(charset)) {
                result = EntityUtils.toString(responseEntity);
            } else {
                result = EntityUtils.toString(responseEntity, charset);
            }

            logger.info("[GET]response received, url=" + url + "cost=" + cost + ", result=" + result);

            return result;
        } finally {
            try {
                httpClient.close();

                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
            }
        }
    }

    /**
     * 发起post请求
     * @param url
     * @param params
     * @return String
     * @throws Exception
     */
    public static String post(String url, Map<String, String> params) throws Exception {
        return post(url, params, null);
    }

    /**
     * 发起post请求
     * @param url
     * @param params
     * @param headers
     * @return String
     * @throws Exception
     */
    public static String post(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        try {
            HttpPost httpPost = new HttpPost(url);
            if (MapUtils.isNotEmpty(headers)) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }

            List<NameValuePair> httpParams = new ArrayList<>();
            for (Entry<String, String> entry : params.entrySet()) {
                httpParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(httpParams, CommonConstant.DEFAULT_CHARSET));

            logger.info("[POST]url=" + url + ", request param=" + params);

            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            String result = EntityUtils.toString(responseEntity, CommonConstant.DEFAULT_CHARSET);

            logger.info("[POST]url=" + url + ", response=" + result);

            return result;
        } finally {
            try {
                httpClient.close();

                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
            }
        }
    }

    /**
     * 发起post请求
     * @param url
     * @param entity
     * @return String
     * @throws Exception
     */
    public static String post(String url, String entity) throws Exception {
        return post(url, entity, null);
    }

    /**
     * 发起post请求
     *
     * @param url
     * @param entity
     * @param headers
     * @return String
     * @throws Exception
     */
    public static String post(String url, String entity, Map<String, String> headers) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        try {
            HttpPost httpPost = new HttpPost(url);
            if (MapUtils.isNotEmpty(headers)) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }

            StringEntity stringEntity = new StringEntity(entity, CommonConstant.DEFAULT_CHARSET);

            httpPost.setEntity(stringEntity);

            logger.info("[POST]url=" + url + ", request stringEntity=" + stringEntity);

            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            String result = EntityUtils.toString(responseEntity, CommonConstant.DEFAULT_CHARSET);

            logger.info("[POST]url=" + url + ", response=" + result);

            return result;
        } finally {
            try {
                httpClient.close();

                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
            }
        }
    }

    /**
     * 发起post请求
     *
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws Exception
     */
    public static byte[] postReceiveByte(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        try {
            HttpPost httpPost = new HttpPost(url);
            if (MapUtils.isNotEmpty(headers)) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }

            List<NameValuePair> httpParams = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                httpParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(httpParams, "UTF-8"));

            logger.info("[POST]url=" + url + ", request param=" + params);
            //避免出现超时，如果不配置，httpClient4.3以后默认>24小时
            httpPost.setConfig(getReqeustConfig());
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            byte[] bytes = EntityUtils.toByteArray(responseEntity);
            return bytes;
        } finally {
            try {
                httpClient.close();

                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
            }
        }
    }

    /**
     * 获取HTTP请求的配置
     *
     * @return String
     */
    public static RequestConfig getReqeustConfig() {
        Integer timeout = 60000;
        //logger.info("Timeout time is:" + timeout);
        //1.ConnectionRequestTimeout: 经历多久之后，这次请求被视为过期
        //2.ConnectTimeout: 等待与服务器建立连接的时间
        //3.SocketTimeout: 与服务器的连接建立了，等待服务器发送数据的时间
        RequestConfig requstConfigure = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
        return requstConfigure;
    }

    /**
     * 获取要查询的字符串
     *
     * @param data
     * @return String
     * @throws UnsupportedEncodingException
     */
    public static String toQueryString(Map<?, ?> data) throws UnsupportedEncodingException {
        StringBuilder queryString = new StringBuilder();
        for (Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append(URLEncoder.encode((String) pair.getValue(), "UTF-8") + "&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return queryString.toString();
    }

    /**
     * post请求参数为json格式
     * @param url
     * @param json
     * @return String
     */
    public static String postWithJson(String url, String json) {
        logger.info("==> postwithjson url:{}", url);
        logger.info("==> postwithjson param:{}", json);
        String returnValue = "这是默认返回值，接口调用失败";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            //第一步：创建HttpClient对象
            httpClient = HttpClients.createDefault();

            //第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost(url);

            //第三步：给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(json, "utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);

            //第四步：发送HttpPost请求，获取返回值
            returnValue = httpClient.execute(httpPost, responseHandler); //调接口获取返回值时，必须用此方法
            logger.info("<== postwithjson returnData:{}", returnValue);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //第五步：处理返回值
        return returnValue;
    }


}
