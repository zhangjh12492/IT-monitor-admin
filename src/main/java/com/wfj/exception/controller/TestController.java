package com.wfj.exception.controller;

import com.wfj.exception.dal.service.SmsService;
import com.wfj.exception.dal.vo.SmsSendVo;
import com.wfj.exception.util.HttpUtil;
import com.wfj.exception.util.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaYong on 2015/8/28.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Resource(name = "smsService")
    private SmsService smsService;

    @RequestMapping(params = "sendSms")
    @ResponseBody
    public String sendSms() {
        SmsSendVo vo = new SmsSendVo();
        vo.setMessagecontent("欢迎开启您的灵感之旅，您的手机校验码为：585858，30分钟内有效。wangfujing.com");
        List<String> list = new ArrayList<String>();
        list.add("15201355119");
//        list.add("15801231310");
        vo.setMobilephone(list);
        vo.setRequestorid("WFJ_VC"); 
        vo.setType("01");
        try {
            Boolean b = smsService.sendSms(vo);
//            Boolean b = smsService.sendSms_2(vo);
            System.out.println(b);
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    public static void main(String[] args) throws Exception {
		HttpUtil.http("http://172.16.3.202:8080/exceptionSocketPro/test.do?sendSms", null);
	}
}
