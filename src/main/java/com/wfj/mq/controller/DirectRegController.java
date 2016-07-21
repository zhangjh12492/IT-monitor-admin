package com.wfj.mq.controller;

import com.wfj.exception.dal.service.UserGroupService;
import com.wfj.exception.util.DataTableDto;
import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.MsgReturnDto;
import com.wfj.mq.dto.InboundConfDto;
import com.wfj.mq.dto.InboundQueueRefDto;
import com.wfj.mq.entity.DicItems;
import com.wfj.mq.entity.InboundConf;
import com.wfj.mq.entity.InboundQueueRef;
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
@RequestMapping("/mq_directReg")
public class DirectRegController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(DirectRegController.class);

    @Value("#{configProperties[mq_netty_url]}")
    private String netty_url;

    @Autowired
    private UserGroupService userGroupService;

    @RequestMapping(params = "main")
    public String groupUser() {
        return "/view/mq/directReg";
    }


    @RequestMapping(params = "delUserGroup")
    @ResponseBody
    public MsgReturnDto delUserGroup(@RequestParam Integer id, HttpServletRequest request) {
        String msg = null;
        try {
            msg = userGroupService.delUserGroup(id);
        } catch (SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return new MsgReturnDto("", msg);
    }

    @RequestMapping(params = "saveOrUpdate")
    @ResponseBody
    public MsgReturnDto saveOrUpdate(@ModelAttribute InboundQueueRef inboundQueueRef, HttpServletRequest request) {
        inboundQueueRef.setStatus(1);
        String jsonStr = JsonUtils.beanToJson(inboundQueueRef);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("data", jsonStr));
        String result = null;
        String url = null;
        if (inboundQueueRef.getSid() == null) {
            url = netty_url + "/inboundQueue/add.do";
        } else {
//            url = netty_url + "/inbound/update.do";
            return new MsgReturnDto("", "未配置修改方法");
        }
        result = get(url, nvps);
        MsgReturnDto json = JsonUtils.jsonToBean(result, MsgReturnDto.class);
        return json;
    }

    @RequestMapping(params = "selectDirectRegBySystemNo")
    @ResponseBody
    public DataTableJson selectDirectRegBySystemNo(@RequestParam String systemNo, HttpServletRequest request) {
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
        String url = netty_url + "/inboundQueue/queryInfoBySystemNo.do";
        result = get(url, nvps);
        json = JsonUtils.jsonToBean(result, DataTableJson.class);
        String aaData = JsonUtils.beanListToJson(json.getAaData());
        List<InboundQueueRefDto> list = JsonUtils.jsonToBeanList(aaData, InboundQueueRefDto.class);
        json.setAaData(list);
        json.setsEcho(dataTableDto.getsEcho());
        return json;
    }

    @RequestMapping(params = "deleteDirectReg")
    @ResponseBody
    public MsgReturnDto deleteDirectReg(@RequestParam Integer sid, HttpServletRequest request){
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("sid", sid.toString()));
        String result = null;
        String url = null;
        url = netty_url + "/inboundQueue/deleteById.do";
        result = get(url, nvps);
        MsgReturnDto json = JsonUtils.jsonToBean(result, MsgReturnDto.class);
        return json;

    }

}
