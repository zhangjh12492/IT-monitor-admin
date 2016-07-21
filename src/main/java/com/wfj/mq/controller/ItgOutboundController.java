package com.wfj.mq.controller;

import com.wfj.exception.util.DataTableDto;
import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.MsgReturnDto;
import com.wfj.mq.entity.OutboundConf;
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
@RequestMapping("/mq_outbound")
public class ItgOutboundController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(ItgOutboundController.class);

    @Value("#{configProperties[mq_netty_url]}")
    private String netty_url;

    @RequestMapping(params = "main")
    public ModelAndView showIndex(HttpServletRequest request, ModelAndView model) {
        String basePath = request.getContextPath();
        model.addObject("ctx", basePath);
        model.setViewName("forward:view/mq/outbound.jsp");
        return model;
    }

    @RequestMapping(params = "queryList")
    @ResponseBody
    public DataTableJson queryList(@ModelAttribute OutboundVo vo, HttpServletRequest request) {
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
        String url = netty_url + "/outbound/queryList.do";
        result = get(url, nvps);
        json = JsonUtils.jsonToBean(result, DataTableJson.class);
        String aaData = JsonUtils.beanListToJson(json.getAaData());
        List<OutboundConf> list = JsonUtils.jsonToBeanList(aaData, OutboundConf.class);
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
    public MsgReturnDto saveOrUpdateOutboundConf(@ModelAttribute OutboundConf outboundConf, HttpServletRequest request) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        outboundConf.setStatus(1);
        String jsonStr = JsonUtils.beanToJson(outboundConf);
        nvps.add(new BasicNameValuePair("data", jsonStr));
        String result = null;
        String url = null;
        if (outboundConf.getSid() == null) {
            url = netty_url + "/outbound/add.do";
        } else {
            url = netty_url + "/outbound/update.do";
        }
        result = get(url, nvps);
        MsgReturnDto json = JsonUtils.jsonToBean(result, MsgReturnDto.class);
        return json;
    }

    @RequestMapping(params = "getOutboundConfList")
    @ResponseBody
    public List<OutboundConf> getOutboundConfList(@ModelAttribute OutboundConf outboundConf, HttpServletRequest request){
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        outboundConf.setStatus(1);
        String jsonStr = JsonUtils.beanToJson(outboundConf);
        nvps.add(new BasicNameValuePair("data", jsonStr));
        String result = null;
        String url = null;
            url = netty_url + "/outbound/getOutboundConfList.do";
        result = get(url, nvps);
        List<OutboundConf> json = JsonUtils.jsonToBeanList(result, OutboundConf.class);
        return json;

    }


}
