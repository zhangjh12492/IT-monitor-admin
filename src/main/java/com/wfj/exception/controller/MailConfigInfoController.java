package com.wfj.exception.controller;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.common.RequestResult;
import com.wfj.exception.dal.cond.MailConfigInfoCond;
import com.wfj.exception.dal.entity.MailConfigInfo;
import com.wfj.exception.dal.service.MailConfigInfoService;
import com.wfj.exception.email.vo.MailSenderVo;
import com.wfj.exception.util.DataTableDto;
import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.HttpClientUtil;
import com.wfj.exception.util.HttpUtil;
import com.wfj.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/12.
 */
@Controller
@RequestMapping("/mailConfigInfo")
public class MailConfigInfoController {

    private static final Logger log= LoggerFactory.getLogger(MailConfigInfoController.class);

    @Resource
    private MailConfigInfoService mailConfigInfoService;

    @RequestMapping(params = "intoMailConfigInfoView")
    public String intoMailConfigInfoView(){
        return "/view/jsp/mail/mailConfigInfo";
    }

    @RequestMapping(params="insertMailConfigInfo",method = RequestMethod.POST ,produces ="application/json;charset=UTF-8")
    @ResponseBody
    public String insertMailConfigInfo(MailConfigInfo mailConfigInfo){
        try {
            if (mailConfigInfo.getId()!=null){
                mailConfigInfoService.updateMailConfigInfo(mailConfigInfo);
            }else{
                mailConfigInfoService.insertMailConfigInfo(mailConfigInfo);
            }
        } catch (Exception e) {
            log.error("修改或者增加邮件配置信息错误:{}",e.getMessage());
            return "false";
        }
        return "true";
    }

    @RequestMapping(params = "deleteMailConfigInfo")
    @ResponseBody
    public String deleteMailConfigInfo(MailConfigInfo mailConfigInfo){
        try {
            mailConfigInfoService.deleteMailConfigInfo(mailConfigInfo);
        } catch (Exception e) {
            log.error("删除邮件配置信息错误:{}",e.getMessage());
            return "false";
        }
        return "true";
    }

    @RequestMapping(params="pageMailConfigInfoAjax")
    @ResponseBody
    public DataTableJson pageMailConfigInfoAjax(HttpServletRequest request, @ModelAttribute MailConfigInfoCond mailConfigInfoCond)  {
        log.info("mailConfigInfoCond:{}", mailConfigInfoCond.toString());
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(mailConfigInfoCond);
        try {
            json = mailConfigInfoService.selectMailConfigInfoPage(page);
            json.setsEcho(dataTableDto.getsEcho());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 发送邮件的接口
     * @param request
     * @return
     */
    @RequestMapping(value = "/remoteSendMailInfo", method = RequestMethod.POST ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public RequestResult remoteSendMailInfo(HttpServletRequest request){
        RequestResult<Boolean> result=new RequestResult<>();
        try {
            String message= HttpUtil.getRequestInputStreamString(request);
            MailSenderVo mailSenderVo= JSONObject.parseObject(message, MailSenderVo.class);
            if(mailSenderVo==null){
                result.setContent(false);
                result.setResultMsg("邮件内容为空,请查看原因!");
                result.setSuccess(false);
            }else{
                Boolean flag=mailConfigInfoService.sendMail(mailSenderVo);
                if(flag=true){
                    result.setContent(flag);
                    result.setSuccess(flag);
                    result.setResultMsg("邮件发送成功!");
                }else{
                    result.setSuccess(flag);
                    result.setResultMsg("邮件发送失败!");
                }
            }
        } catch (Exception e) {
            log.error("发送邮件接口出错:{}", e.getMessage());
            result.setContent(false);
            result.setSuccess(false);
            result.setResultMsg(e.getMessage());
        }
        return result;
    }

    public static void main(String [] args) throws Exception {
        MailSenderVo mailSenderVo=new MailSenderVo();
        mailSenderVo.setMaiBody("hello");
        mailSenderVo.setSubject("测试");
        List<String> receivers=new ArrayList<String>();
        receivers.add("zhangjh12492@163.com");
        receivers.add("1249202780@qq.com");
        mailSenderVo.setReceivers(receivers);
        String message= JSONObject.toJSONString(mailSenderVo);
        System.out.println(message);
        String result=HttpClientUtil.postJSON("http://172.16.3.202:8080/exceptionSocketPro/mailConfigInfo/remoteSendMailInfo.do", message);
        System.out.println(result);
    }
}
