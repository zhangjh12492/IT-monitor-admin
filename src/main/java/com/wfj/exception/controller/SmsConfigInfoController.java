package com.wfj.exception.controller;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.common.RequestResult;
import com.wfj.exception.dal.cond.SmsConfigInfoCond;
import com.wfj.exception.dal.entity.SmsConfigInfo;
import com.wfj.exception.dal.service.SmsConfigInfoService;
import com.wfj.exception.dal.service.SmsService;
import com.wfj.exception.dal.vo.SmsSendVo;
import com.wfj.exception.util.*;
import com.wfj.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/12.
 */
@Controller
@RequestMapping("/smsConfigInfo")
public class SmsConfigInfoController {

    private static final Logger log= LoggerFactory.getLogger(SmsConfigInfoController.class);

    @Resource
    private SmsConfigInfoService smsConfigInfoService;
    @Autowired
    private SmsService smsService;

    @RequestMapping(params = "intoSmsConfigInfoView")
    public String intoSmsConfigInfoView(){
        return "/view/jsp/sms/smsConfigInfo";
    }

    @RequestMapping(params="insertSmsConfigInfo",method = RequestMethod.POST ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String insertSmsConfigInfo(SmsConfigInfo smsConfigInfo){
        try {
            if (smsConfigInfo.getId()!=null){
                smsConfigInfoService.updateSmsConfigInfo(smsConfigInfo);
            }else{
                smsConfigInfoService.insertSmsConfigInfo(smsConfigInfo);
            }
        } catch (Exception e) {
            log.error("修改或者增加短信配置信息错误:{}",e.getMessage());
            return "false";
        }
        return "true";
    }

    @RequestMapping(params = "deleteSmsConfigInfo")
    @ResponseBody
    public String deleteSmsConfigInfo(SmsConfigInfo smsConfigInfo){
        try {
            smsConfigInfoService.deleteSmsConfigInfo(smsConfigInfo);
        } catch (Exception e) {
            log.error("删除短信配置信息错误:{}", e.getMessage());
            return "false";
        }
        return "true";
    }

    @RequestMapping(params="pageSmsConfigInfoAjax")
    @ResponseBody
    public DataTableJson pageSmsConfigInfoAjax(HttpServletRequest request, @ModelAttribute SmsConfigInfoCond smsConfigInfoCond)  {
        log.info("smsConfigInfoCond:{}", smsConfigInfoCond.toString());
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(smsConfigInfoCond);
        try {
            json = smsConfigInfoService.selectSmsConfigInfoPage(page);
            json.setsEcho(dataTableDto.getsEcho());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 发送短信的接口
     * @param request
     * @return
     */
    @RequestMapping(value = "/remoteSendSmsInfo", method = RequestMethod.POST ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public RequestResult remoteSendSmsInfo(HttpServletRequest request){
        RequestResult<Boolean> result=new RequestResult<>();
        try {
            String message= HttpUtil.getRequestInputStreamString(request);
            SmsSendVo smsSendVo= JSONObject.parseObject(message,SmsSendVo.class);
            if(smsSendVo==null){
                result.setContent(false);
                result.setResultMsg("短息内容为空,请查看原因!");
                result.setSuccess(false);
            }else{
                Boolean flag=smsService.sendSms(smsSendVo);
                if(flag=true){
                    result.setContent(flag);
                    result.setSuccess(flag);
                    result.setResultMsg("短息发送成功!");
                }else{
                    result.setSuccess(flag);
                    result.setResultMsg("短信发送失败!");
                }
            }
        } catch (Exception e) {
            log.error("发送短信接口出错:{}", e.getMessage());
        }
        return result;
    }

    public static void main(String [] args) throws Exception {
        SmsSendVo vo = new SmsSendVo();
        vo.setMessagecontent("欢迎开启您的灵感之旅，您的手机校验码为：585858，30分钟内有效。wangfujing.com");
        List<String> list = new ArrayList<String>();
//        list.add("15201355119");
        list.add("15801231310");
        vo.setMobilephone(list);
        vo.setRequestorid("WFJ_VC");
        vo.setType("01");
        System.out.println(JSONObject.toJSONString(vo));



        String message=JSONObject.toJSONString(vo);
        System.out.println(message);
//        message="{\"busiCode\":\"002\",\"busiDesc\":\"ceshi_订单支付\",\"createDate\":\"2015-10-15 13:33:53\",\"errCode\":\"001\",\"errDesc\":\"ceshi_商品信息不全\",\"errId\":\"101000200100120151015133353904\",\"errLevel\":\"1\",\"flag\":\"0\",\"processStatus\":\"0\",\"sysCode\":\"10\",\"sysErrCode\":\"001\",\"sysErrDesc\":\"算术异常\",\"throwableDesc\":\"java.lang.ArithmeticException:/ by zero\\ncom.wfj.exception.Test.c(Test.java:23)\\ncom.wfj.exception.Test.addtMes(Test.java:55)\\ncom.wfj.exception.Test.main(Test.java:28)\\nsun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\\nsun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\\nsun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\\njava.lang.reflect.Method.invoke(Method.java:601)\\ncom.intellij.rt.execution.application.AppMain.main(AppMain.java:140)\\n\"}";
//       String result= HttpClientUtilPost.postBody("http://172.16.3.202:8080/exceptionSocketPro/smsConfigInfo/remoteSendSmsInfo.do", message);
//        System.out.println(result);
//        HttpClientUtil.postJSON("http://172.16.3.202:6090/ExceptionServer/dataProcess/josnInboundFromMq.do",message);
    }


}
