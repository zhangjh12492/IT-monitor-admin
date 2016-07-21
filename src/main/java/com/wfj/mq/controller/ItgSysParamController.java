package com.wfj.mq.controller;

import com.alibaba.fastjson.JSONArray;
import com.wfj.exception.util.DataTableDto;
import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.MsgReturnDto;
import com.wfj.mq.vo.DicItems;
import com.wfj.mq.vo.DicItemsVo;
import com.wfj.mq.vo.DicNamesVo;
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

@Controller
@RequestMapping("/mq_sysParam")
public class ItgSysParamController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(ItgSysParamController.class);

    @Value("#{configProperties[mq_netty_url]}")
    private String netty_url;

    @RequestMapping(params = "main")
    public ModelAndView showIndex(HttpServletRequest request, ModelAndView model) {
        String basePath = request.getContextPath();
        model.addObject("ctx", basePath);
        model.setViewName("forward:view/mq/itgSysParam.jsp");
        return model;
    }

    @RequestMapping(params = "queryList")
    @ResponseBody
    public DataTableJson queryList(@ModelAttribute DicNamesVo vo) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        page.setCond(vo);
        String jsonStr = JsonUtils.beanToJson(vo);

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("data", jsonStr));
        String result = null;
        String url = netty_url + "/sysParam/queryList.do";
        result = get(url, nvps);
        List<DicNamesVo> list = JsonUtils.jsonToBeanList(result, DicNamesVo.class);
        json.setAaData(list);
        json.setiTotalRecords(list.size());
        json.setiTotalDisplayRecords(list.size());
        return json;
    }

    @RequestMapping(params = "itemList")
    @ResponseBody
    public DataTableJson queryItemList(@ModelAttribute DicItemsVo vo, HttpServletRequest request) {
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(vo);
        DataTableJson json = new DataTableJson();
        String jsonStr = JsonUtils.beanToJson(page);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("data", jsonStr));
        String result = null;
        String url = netty_url + "/sysParam/itemList.do";
        result = get(url, nvps);
//        List<DicItems> list = JsonUtils.jsonToBeanList(result, DicItems.class);
//        json.setAaData(list);
//        json.setiTotalRecords(list.size());
//        json.setiTotalDisplayRecords(list.size());
        json = JsonUtils.jsonToBean(result, DataTableJson.class);
        String aaData = JsonUtils.beanListToJson(json.getAaData());
        List<DicItems> list = JsonUtils.jsonToBeanList(aaData, DicItems.class);
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
        String url = netty_url + "/sysParam/deleteById.do";
        result = get(url, nvps);
        MsgReturnDto json = JsonUtils.jsonToBean(result, MsgReturnDto.class);
        return json;
    }

    @RequestMapping(params = "add")
    @ResponseBody
    public MsgReturnDto add(@ModelAttribute DicItemsVo vo, HttpServletRequest request) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        String jsonStr = JsonUtils.beanToJson(vo);
        nvps.add(new BasicNameValuePair("data", jsonStr));
        String result = null;
        String url = netty_url + "/sysParam/add.do";
        result = get(url, nvps);
        MsgReturnDto json = JsonUtils.jsonToBean(result, MsgReturnDto.class);
        return json;
    }


    @RequestMapping(params = "getDicItem")
    @ResponseBody
    public List<DicItems> getDicItem(HttpServletRequest request) {
        List<DicItems> list = null;
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        String result = null;
        String url = netty_url + "/sysParam/queryAll.do";
        result = get(url, nvps);
        list = JSONArray.parseArray(result, DicItems.class);
        return list;

    }

}
