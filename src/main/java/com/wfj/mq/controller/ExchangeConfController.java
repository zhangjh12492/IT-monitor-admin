package com.wfj.mq.controller;

import com.wfj.exception.util.DataTableDto;
import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.MsgReturnDto;
import com.wfj.mq.entity.ExchangeConf;
import com.wfj.mq.entity.OutboundConf;
import com.wfj.mq.vo.ExchangeVo;
import com.wfj.mq.vo.OutboundVo;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaYong on 2015/9/5.
 */

@Controller
@RequestMapping("/mq_exchangeConf")
public class ExchangeConfController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(ExchangeConfController.class);

    @Value("#{configProperties[mq_netty_url]}")
    private String netty_url;

    @RequestMapping(params = "main")
    public ModelAndView showIndex(HttpServletRequest request, ModelAndView model) {
        String basePath = request.getContextPath();
        model.addObject("ctx", basePath);
        model.setViewName("forward:view/mq/exchangeConf.jsp");
        return model;
    }

    @RequestMapping(params = "queryList")
    @ResponseBody
    public DataTableJson queryList(@ModelAttribute ExchangeConf vo, HttpServletRequest request) {
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
        String url = netty_url + "/exchange/queryAll.do";
        result = get(url, nvps);
        json = JsonUtils.jsonToBean(result, DataTableJson.class);
        String aaData = JsonUtils.beanListToJson(json.getAaData());
        List<ExchangeVo> list = JsonUtils.jsonToBeanList(aaData, ExchangeVo.class);
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

    @RequestMapping(params = "getExchangeList")
    @ResponseBody
    public List<ExchangeConf> getExchangeList(@ModelAttribute ExchangeConf exchangeConf, HttpServletRequest request) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        String jsonStr = JsonUtils.beanToJson(exchangeConf);
        nvps.add(new BasicNameValuePair("data", jsonStr));
        String result = null;
        String url = null;
        url = netty_url + "/exchange/queryList.do";
        result = get(url, nvps);
        List<ExchangeConf> json = JsonUtils.jsonToBeanList(result, ExchangeConf.class);
        return json;

    }

}
