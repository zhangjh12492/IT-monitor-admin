package com.wfj.mq.controller;

import com.wfj.exception.util.DataTableJson;
import com.wfj.mq.rabbitmq.mgmt.model.Queue;
import com.wfj.util.JsonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping(value = "/mq_detailChart")
public class DetailChartController extends BaseController {


    private final Logger logger = LoggerFactory.getLogger(DetailChartController.class);

    @Value("#{configProperties[mq_netty_url]}")
    private String netty_url;

    @RequestMapping(params = "main")
    public ModelAndView home(HttpServletRequest request, ModelAndView model) {
        String basePath = request.getContextPath();
        model.addObject("ctx", basePath);
        model.setViewName("forward:view/mq/detailChart.jsp");
        return model;
    }

    @RequestMapping(params = "main2")
    public ModelAndView home_2(HttpServletRequest request, ModelAndView model) {
        String basePath = request.getContextPath();
        model.addObject("ctx", basePath);
        model.setViewName("forward:view/mq/detailChart_2.jsp");
        return model;
    }

    /**
     * @param groupSid
     * @return
     */
    @RequestMapping(params = "getQueues")
    @ResponseBody
    public Collection<Queue> getQueues(Integer groupSid) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("groupSid", groupSid.toString()));
        String result = null;
        String url = netty_url + "/queueCharts/getQueues.do";
        result = get(url, nvps);
//        JSONObject object = JSONObject.fromObject(result);
        Collection<Queue> queues = com.alibaba.fastjson.JSONArray.parseArray(result, Queue.class);
        return queues;
    }

    /**
     * @param groupSid
     * @return
     */
    @RequestMapping(params = "getQueuesList")
    @ResponseBody
    public DataTableJson getQueuesList(Integer groupSid) {
        DataTableJson json = new DataTableJson();
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("groupSid", groupSid.toString()));
        String result = null;
        String url = netty_url + "/queueCharts/getQueues.do";
        result = get(url, nvps);
//        JSONObject object = JSONObject.fromObject(result);
        Collection<Queue> queues = com.alibaba.fastjson.JSONArray.parseArray(result, Queue.class);
        List list = new ArrayList(queues);
        json.setAaData(list);
        json.setiTotalRecords(list.size());
        json.setiTotalDisplayRecords(list.size());
        return json;
    }

    /**
     * @param groupSid
     * @return
     */
    @RequestMapping(params = "getQueueChartList")
    @ResponseBody
    public List<Queue> getQueueChartList(Integer groupSid) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("groupSid", groupSid.toString()));
        String result = null;
        String url = netty_url + "/queueCharts/getQueues_list.do";
        result = get(url, nvps);
//        JSONObject object = JSONObject.fromObject(result);
//        List<Queue> queues = com.alibaba.fastjson.JSONArray.parseArray(result, Queue.class);
        List<Queue> queues = JsonUtils.jsonToBeanList(result, Queue.class);
        return queues;
    }

}
