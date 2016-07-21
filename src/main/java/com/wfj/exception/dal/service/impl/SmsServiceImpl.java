package com.wfj.exception.dal.service.impl;

import com.wfj.exception.common.SysConstants;
import com.wfj.exception.dal.service.SmsService;
import com.wfj.exception.dal.util.JsonUtils;
import com.wfj.exception.dal.vo.SmsSendVo;
import com.wfj.exception.util.PropertiesLoad;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by MaYong on 2015/8/28.
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String APPLICATION_JSON = "application/json";

    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";




    public Boolean sendSms(SmsSendVo vo) throws Exception {
        String result = null;
        String json = JsonUtils.beanToJson(vo);
        Boolean r = false;
        try {
            String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);

//            DefaultHttpClient httpClient = new DefaultHttpClient();
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(PropertiesLoad.getProperties(SysConstants.SMS_URL));
            httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

            StringEntity se = new StringEntity(json, HTTP.UTF_8);
            se.setContentType(CONTENT_TYPE_TEXT_JSON);
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);//执行请求
            Integer statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                result = EntityUtils.toString(response.getEntity());
            } else {
                throw new Exception(statusCode.toString());
            }
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
            throw new Exception("999");
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new Exception("999");
        }
        JSONObject jsonObject = JSONObject.fromObject(result);
        return jsonObject.getString("success").equals("true");
    }

    public Boolean sendSms_2(SmsSendVo vo) throws Exception {
        String json = JsonUtils.beanToJson(vo);
//        String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
        String result = null;
        Boolean r = false;
        HttpPost request = new HttpPost(PropertiesLoad.getProperties(SysConstants.SMS_URL));
        request.addHeader(HTTP.CONTENT_TYPE, "application/json; charset=utf-8");
        try {
            //3.1写法
//            HttpClient client = new DefaultHttpClient();
            //4.3写法
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(PropertiesLoad.getProperties(SysConstants.SMS_URL));
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000)
                    .setConnectTimeout(20000).build();//设置请求和传输超时时间
            httpPost.setConfig(requestConfig);
            if (json != null) {
//                HttpEntity paramEntity = new StringEntity(json, "utf8");
                StringEntity se = new StringEntity(json, "UTF-8");
                se.setContentType("text/json");
                se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                httpPost.setEntity(se);
            }
            HttpResponse response = httpClient.execute(httpPost);//执行请求
            Integer statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                result = EntityUtils.toString(response.getEntity());
            } else {
                throw new Exception(statusCode.toString());
            }
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
            throw new Exception("999");
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new Exception("999");
        }
        JSONObject jsonObject = JSONObject.fromObject(result);
        return (Boolean) jsonObject.get("success");
    }


}
