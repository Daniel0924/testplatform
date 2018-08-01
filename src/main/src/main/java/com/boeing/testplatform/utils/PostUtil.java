package com.boeing.testplatform.utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;


public class PostUtil {

    /**
     * 通过传入url，json转成的string，还有是否有返回值，来设置post请求，并返回json格式的响应
     *
     * @param url
     * @param jsonStringParam
     * @param noNeedResponse
     * @return
     */
    public static JSONObject httpPostRequest(String url, String jsonStringParam, boolean noNeedResponse) {


        Logger logger = Logger.getLogger(PostUtil.class);
        HttpClient httpClient = HttpClientBuilder.create().build();
        //DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);

        try {
            if (jsonStringParam != null) {
                StringEntity entity = new StringEntity(jsonStringParam, "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }

            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    jsonResult = JSONObject.parseObject(str);
                    //jsonResult = JSONObject.fromObject(str);
                } catch (Exception var10) {
                    logger.error("post请求提交失败1:" + url, var10);
                }
            }
        } catch (IOException var11) {
            logger.error("post请求提交失败2:" + url, var11);
        }

        return jsonResult;
    }


    public static String sendPost(String urlParam, Map<String, Object> params, String charset) {
        StringBuffer resultBuffer = null;
        // 构建请求参数
        StringBuffer sbParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> e : params.entrySet()) {
                sbParams.append(e.getKey());
                sbParams.append("=");
                sbParams.append(e.getValue());
                sbParams.append("&");
            }
        }
        HttpURLConnection con = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        // 发送请求
        try {
            URL url = new URL(urlParam);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if (sbParams != null && sbParams.length() > 0) {
                osw = new OutputStreamWriter(con.getOutputStream(), charset);
                osw.write(sbParams.substring(0, sbParams.length() - 1));
                osw.flush();
            }
            // 读取返回内容
            resultBuffer = new StringBuffer();
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
            String temp;
            while ((temp = br.readLine()) != null) {
                resultBuffer.append(temp);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (osw != null) {
                    osw.close();
                }
                if (br != null) {
                    br.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
            }
        }

        return resultBuffer.toString();
    }


    public static void main(String[] args) {
        String url = "http://10.112.32.109:8088/api/getZhongLingAxis";

        Map<String, Object> params = new HashMap<>();

        params.put("encodePhoto", "123321123321");
        String charSet = "utf-8";
        JSONObject jsonObject = httpPostRequest(url, params.toString(), false);

        System.out.println(jsonObject.toString());

    }


}
