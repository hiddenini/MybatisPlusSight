package com.xz.util;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
 
import java.net.URI;
import java.util.Map;
 
/**
 * 发送post和get请求
 * 返回值为响应的json字符串
 */
public class HttpClientUtil {
    /**
     * @param url      发送请求的 URL
     * @param postData 需要post的数据
     * @param headers  请求头
     * @return 所代表远程资源的响应结果
     */
    public static String dopost(String url, String postData, Map<String, String> headers) {
        HttpPost post = new HttpPost();
        HttpResponse response = null;
        try {
            post.setURI(new URI(url));
            if (StringUtils.isNotBlank(postData)) {
                post.setEntity(new StringEntity(postData, "UTF-8"));
            }
            if (MapUtils.isNotEmpty(headers)) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }
            HttpClient httpClient = HttpClients.createDefault();
            response = httpClient.execute(post);
            System.out.println("status===="+response.getStatusLine());
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("got an error from HTTP for url:" + url, e);
        } finally {
            if (response != null) {
                EntityUtils.consumeQuietly(response.getEntity());
            }
            post.releaseConnection();
        }
    }
 
    public static String doget(String url, Map<String, String> headers) {
        HttpGet get = new HttpGet();
        HttpResponse response = null;
        try {
            get.setURI(new URI(url));
            if (MapUtils.isNotEmpty(headers)) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    get.addHeader(entry.getKey(), entry.getValue());
                }
            }
            HttpClient httpClient = HttpClients.createDefault();
            response = httpClient.execute(get);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw new RuntimeException("got an error from HTTP for url:" + url, e);
        } finally {
            if (response != null) {
                EntityUtils.consumeQuietly(response.getEntity());
            }
            get.releaseConnection();
        }
    }

}
