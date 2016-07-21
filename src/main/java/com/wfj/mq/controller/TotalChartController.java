package com.wfj.mq.controller;

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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/mq_totalChart")
public class TotalChartController extends BaseController {


    private final Logger logger = LoggerFactory.getLogger(TotalChartController.class);

    @Value("#{configProperties[mq_netty_url]}")
    private String netty_url;

    @RequestMapping(params = "main")
    public ModelAndView home(HttpServletRequest request, ModelAndView model) {
        String basePath = request.getContextPath();
        model.addObject("ctx", basePath);
        model.setViewName("forward:view/mq/totalChart.jsp");
        return model;
    }

    /**
     * @param groupSid
     * @return
     */
    @RequestMapping(params = "getOverview")
    @ResponseBody
    public JSONObject getOverview(Integer groupSid) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("groupSid", groupSid.toString()));
        String result = null;
        String url = netty_url + "/queueCharts/getMessage.do";
        result = get(url, nvps);
        JSONObject object = JSONObject.fromObject(result);
        return object;
    }

}
