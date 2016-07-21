package com.wfj.mq.controller;

import com.alibaba.fastjson.JSONArray;
import com.wfj.exception.util.DataTableDto;
import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.MsgReturnDto;
import com.wfj.mq.dto.DataTable;
import com.wfj.mq.dto.MessageLogDto;
import com.wfj.mq.dto.MessageTraceDto;
import com.wfj.mq.entity.*;
import com.wfj.mq.vo.ExchangeVo;
import com.wfj.mq.vo.MessageVo;
import com.wfj.mq.vo.TxInfoVo;
import com.wfj.util.JacksonJsonUtils;
import com.wfj.util.JsonUtils;
import com.wfj.util.Page;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaYong on 2015/9/5.
 */

@Controller
@RequestMapping("/mq_message")
public class MessageController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Value("#{configProperties[mq_netty_url]}")
    private String netty_url;

    @RequestMapping(params = "main")
    public ModelAndView showIndex(HttpServletRequest request, ModelAndView model) {
        String basePath = request.getContextPath();
        model.addObject("ctx", basePath);
        model.setViewName("forward:view/mq/message.jsp");
        return model;
    }

    @RequestMapping(params = "queryList")
    @ResponseBody
    public DataTableJson queryList(@ModelAttribute TxInfoVo vo, HttpServletRequest request) {
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(vo);
        DataTableJson json = null;
        String jsonStr = JsonUtils.beanToJson(page);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("data", jsonStr));
        String result = null;
        String url = netty_url + "/message/queryList.do";
        result = get(url, nvps);
        json = JsonUtils.jsonToBean(result, DataTableJson.class);
        String aaData = JsonUtils.beanListToJson(json.getAaData());
        List<MessageTraceDto> list = JsonUtils.jsonToBeanList(aaData, MessageTraceDto.class);
        json.setAaData(list);
        json.setsEcho(dataTableDto.getsEcho());
        return json;
    }

    @RequestMapping(params = "deleteById")
    @ResponseBody
    public MsgReturnDto deleteById(@RequestParam Integer sid) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("id", sid.toString()));
        String result = null;
        String url = netty_url + "/outbound/deleteById.do";
        result = get(url, nvps);
        MsgReturnDto json = JsonUtils.jsonToBean(result, MsgReturnDto.class);
        return json;
    }

    @RequestMapping(params = "findById")
    @ResponseBody
    public OutboundConf findById(@RequestParam Integer sid) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("id", sid.toString()));
        String result = null;
        String url = netty_url + "/outbound/findById.do";
        result = get(url, nvps);
        OutboundConf json = JsonUtils.jsonToBean(result, OutboundConf.class);
        return json;
    }

    @RequestMapping(params = "saveOrUpdateOutboundConf")
    @ResponseBody
    public MsgReturnDto saveOrUpdateExchangeConf(@ModelAttribute ExchangeConf exchangeConf, HttpServletRequest request) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        exchangeConf.setStatus(1);
        String jsonStr = JsonUtils.beanToJson(exchangeConf);
        nvps.add(new BasicNameValuePair("data", jsonStr));
        String result = null;
        String url = null;
        if (exchangeConf.getSid() == null) {
            url = netty_url + "/exchange/add.do";
        } else {
            url = netty_url + "/exchange/update.do";
        }
        result = get(url, nvps);
        MsgReturnDto json = JsonUtils.jsonToBean(result, MsgReturnDto.class);
        return json;
    }

    /**
     * 点"查看消息"按钮后出现的接入接出消息列表
     *
     * @param
     * @param request
     * @return
     */
    @RequestMapping("/queryDetailList")
    @ResponseBody
    public DataTableJson queryDetail(@RequestParam Integer sid, HttpServletRequest request) {
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(sid);
        String jsonStr = JsonUtils.beanToJson(page);
        DataTableJson json = null;
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("data", jsonStr));
        String url = netty_url + "/message/queryDetailList.do";
        TxInfoLog txLog = null;
        String result = get(url, nvps);
        json = JsonUtils.jsonToBean(result, DataTableJson.class);
        String aaData = JsonUtils.beanListToJson(json.getAaData());
        System.out.println("==:" + aaData);
//        List<MessageLog> list = JsonUtils.jsonToBeanList(aaData, MessageLog.class);
        List<MessageLogDto> list = null;
        try {
//            list = (List) JacksonJsonUtils.jsonToBean(aaData, MessageLog.class);
            list = JSONArray.parseArray(aaData, MessageLogDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        json.setAaData(list);
        json.setsEcho(dataTableDto.getsEcho());
        return json;
    }


    @RequestMapping("/queryLogList")
    @ResponseBody
    public DataTableJson queryLog(@RequestParam Integer sid, HttpServletRequest request) {
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(sid);
        String jsonStr = JsonUtils.beanToJson(page);
        DataTableJson json = null;
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("data", jsonStr));
        String url = netty_url + "/message/queryLogList.do";
        TxInfoLog txLog = null;
        String result = get(url, nvps);
        json = JsonUtils.jsonToBean(result, DataTableJson.class);
        String aaData = JsonUtils.beanListToJson(json.getAaData());
//        -------------------------------
        List<TxInfoLog> list = JSONArray.parseArray(aaData, TxInfoLog.class);
//        List<TxInfoLog> list = JsonUtils.jsonToBeanList(aaData, TxInfoLog.class);
        json.setAaData(list);
        json.setsEcho(dataTableDto.getsEcho());
        return json;
    }

}
