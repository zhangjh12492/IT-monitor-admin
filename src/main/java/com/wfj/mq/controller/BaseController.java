package com.wfj.mq.controller;

import com.wfj.exception.util.DataTableDto;
import com.wfj.util.JsonUtils;
import com.wfj.util.Page;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by MaYong on 2015/9/5.
 */
public class BaseController {
    //    private HttpClient httpClient = new DefaultHttpClient();
    private CloseableHttpClient httpClient = HttpClients.createDefault();


    /**
     * 发送Get请求
     *
     * @param url
     * @param params
     * @return
     */
    public String get(String url, List<NameValuePair> params) {
        String body = null;
        CloseableHttpResponse httpresponse = null;
        try {
            // Get请求
            HttpGet httpget = new HttpGet(url);
            // 设置参数
            String str = EntityUtils.toString(new UrlEncodedFormEntity(params, "UTF-8"));
            httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
            // 发送请求
            httpresponse = httpClient.execute(httpget);
            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();
            body = EntityUtils.toString(entity);
            if (entity != null) {
                entity.consumeContent();
            }
            httpget.releaseConnection();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            try {
                httpresponse.close();
            } catch (Exception e) {

            }
        }
        return body;
    }

    public String getPageJsonStr(Object cond, HttpServletRequest request) {
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(cond);
        String jsonStr = JsonUtils.beanToJson(page);
        return jsonStr;

    }

}
