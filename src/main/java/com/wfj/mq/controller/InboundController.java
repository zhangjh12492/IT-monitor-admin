package com.wfj.mq.controller;

import com.wfj.exception.dal.service.UserGroupService;
import com.wfj.exception.util.DataTableDto;
import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.MsgReturnDto;
import com.wfj.mq.dto.InboundConfDto;
import com.wfj.mq.entity.DicItems;
import com.wfj.mq.entity.InboundConf;
import com.wfj.mq.entity.QueueConf;
import com.wfj.util.JsonUtils;
import com.wfj.util.Page;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaYong on 2015/8/17.
 */
@Controller
@RequestMapping("/mq_inbound")
public class InboundController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(InboundController.class);

    @Value("#{configProperties[mq_netty_url]}")
    private String netty_url;

    @Autowired
    private UserGroupService userGroupService;

    @RequestMapping(params = "main")
    public String groupUser() {
        return "/view/mq/inboundReg";
    }

    @RequestMapping(params = "group")
    public String group() {
        return "view/group";
    }


    @RequestMapping(params = "addUser")
    @ResponseBody
    public MsgReturnDto addUser(@RequestParam Integer[] ids, @RequestParam Integer groupId,
                                @RequestParam Integer userType, HttpServletRequest request) {
        String msg = null;
        try {
            msg = userGroupService.addUser(groupId, ids, userType);
        } catch (SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return new MsgReturnDto("", msg);
    }

    @RequestMapping(params = "deleteById")
    @ResponseBody
    public MsgReturnDto deleteById(@RequestParam Integer sid) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("sid", sid.toString()));
        String result = null;
        String url = netty_url + "/inbound/deleteById.do";
        result = get(url, nvps);
        MsgReturnDto json = JsonUtils.jsonToBean(result, MsgReturnDto.class);
        return json;
    }


    @RequestMapping(params = "saveOrUpdate")
    @ResponseBody
    public MsgReturnDto saveOrUpdate(@ModelAttribute InboundConf inboundConf, HttpServletRequest request) {
        inboundConf.setGroupSid(1);
        String jsonStr = JsonUtils.beanToJson(inboundConf);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("data", jsonStr));
        String result = null;
        String url = null;
        if (inboundConf.getSid() == null) {
            url = netty_url + "/inbound/add.do";
        } else {
            url = netty_url + "/inbound/update.do";
        }
        result = get(url, nvps);
        MsgReturnDto json = JsonUtils.jsonToBean(result, MsgReturnDto.class);
        return json;
    }


    @RequestMapping(params = "getSystemList")
    @ResponseBody
    public DataTableJson getSystemList(HttpServletRequest request) {
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        DicItems dicItems = new DicItems();
        dicItems.setDicName("业务系统编号");
        page.setCond(dicItems);
        DataTableJson json = null;
        String jsonStr = JsonUtils.beanToJson(page);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("data", jsonStr));
        String result = null;
        String url = netty_url + "/sysParam/itemList.do";
        result = get(url, nvps);
        json = JsonUtils.jsonToBean(result, DataTableJson.class);
        String aaData = JsonUtils.beanListToJson(json.getAaData());
        List<DicItems> list = JsonUtils.jsonToBeanList(aaData, DicItems.class);
        json.setAaData(list);
        json.setsEcho(dataTableDto.getsEcho());
        return json;
    }

    @RequestMapping(params = "getNotExistUser")
    @ResponseBody
    public DataTableJson getNotExistUser(@RequestParam Integer userGroupId, HttpServletRequest request) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(userGroupId);
        try {
            json = userGroupService.getNotExistUser(page);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;

    }

    @RequestMapping(params = "selectInboundBySystemNo")
    @ResponseBody
    public DataTableJson selectInboundBySystemNo(@RequestParam String systemNo, HttpServletRequest request) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(systemNo);
        String jsonStr = JsonUtils.beanToJson(page);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("data", jsonStr));
        String result = null;
        String url = netty_url + "/inbound/queryList.do";
        result = get(url, nvps);
        json = JsonUtils.jsonToBean(result, DataTableJson.class);
        String aaData = JsonUtils.beanListToJson(json.getAaData());
        List<InboundConfDto> list = JsonUtils.jsonToBeanList(aaData, InboundConfDto.class);
        json.setAaData(list);
        json.setsEcho(dataTableDto.getsEcho());
        return json;
    }


    @RequestMapping(params = "getQueueList")
    @ResponseBody
    public List<QueueConf> getQueueList(@ModelAttribute QueueConf queueConf, HttpServletRequest request) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        String jsonStr = JsonUtils.beanToJson(queueConf);
        nvps.add(new BasicNameValuePair("data", jsonStr));
        String result = null;
        String url = null;
        url = netty_url + "/queue/getQueueList.do";
        result = get(url, nvps);
        List<QueueConf> json = JsonUtils.jsonToBeanList(result, QueueConf.class);
        return json;
    }

    @RequestMapping(params = "getInboundList")
    @ResponseBody
    public List<InboundConf> getInboundList(@ModelAttribute InboundConf inboundConf, HttpServletRequest request) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        String jsonStr = JsonUtils.beanToJson(inboundConf);
        nvps.add(new BasicNameValuePair("data", jsonStr));
        String result = null;
        String url = null;
        url = netty_url + "/inbound/getInboundList.do";
        result = get(url, nvps);
        List<InboundConf> json = JsonUtils.jsonToBeanList(result, InboundConf.class);
        return json;
    }

}
