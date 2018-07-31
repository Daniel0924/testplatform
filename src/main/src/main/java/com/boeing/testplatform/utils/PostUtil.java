package com.boeing.testplatform.utils;



import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URLDecoder;


public class PostUtil {

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
                    jsonResult = JSONObject.fromObject(str);
                } catch (Exception var10) {
                    logger.error("post请求提交失败1:" + url, var10);
                }
            }
        } catch (IOException var11) {
            logger.error("post请求提交失败2:" + url, var11);
        }

        return jsonResult;
    }

}
