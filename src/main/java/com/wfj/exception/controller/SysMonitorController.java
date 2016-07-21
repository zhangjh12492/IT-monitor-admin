package com.wfj.exception.controller;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.common.HbasePage;
import com.wfj.exception.dal.cond.ApplicationMonitorCond;
import com.wfj.exception.dal.cond.MesAllProcessReqCond;
import com.wfj.exception.dal.service.ExceptionInfoService;
import com.wfj.exception.dal.service.SysMonitorService;
import com.wfj.exception.dal.vo.ApplicationShowDataVo;
import com.wfj.exception.dal.vo.ClientExceptionReq;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/sysMonitor")
public class SysMonitorController {


    private static final org.slf4j.Logger log= org.slf4j.LoggerFactory.getLogger(SysMonitorController.class);

    @Resource(name="sysMonitorService")
    private SysMonitorService sysMonitorService;
    @Resource(name="exceptionInfoService")
    private ExceptionInfoService exceptionInfoService;
    /**
     * 进入系统信息监控界面
     * @Title: intoSysMonitorIndex
     * @author ZJHao
     * @return
     * @return String
     * @throws
     * @date 2015-9-9 下午8:43:23
     */
    @RequestMapping(params="intoSysMonitorIndex")
    public String intoSysMonitorIndex(Model model,@RequestParam String code){
    	model.addAttribute("code", code);
    	return "/view/jsp/sysMonitor/sysMonitorIndex";
    }
    
    /**
     * 初始化系统监控信息
     * @Title: sysMonitorIndex
     * @author ZJHao
     * @return
     * @throws Exception
     * @return MsgReturnDto
     * @throws
     * @date 2015-9-14 上午1:01:15
     */
    @RequestMapping(params="sysMonitorIndex")
    @ResponseBody
    public String sysMonitorIndex(@RequestParam String sysCode) {
        Map<String, List<ApplicationShowDataVo>> map= null;
        try {
            List<ApplicationShowDataVo> listMonitorAvag=sysMonitorService.initAppShowDataAvg(sysCode);
            List<ApplicationShowDataVo> listMonitorData=sysMonitorService.sysMoniterDataList(sysCode);
            map = new HashMap<String, List<ApplicationShowDataVo>>();
            map.put("listMonitorAvag",listMonitorAvag);
            map.put("listMonitorData", listMonitorData);
        } catch (Exception e) {
            log.error("获取监控见面的数据异常,{}",e.getMessage());
        }
        return JSONObject.toJSONString(map);
    }
    
    /**
     * 查询异常信息用于在系统监控页面展示
     * @Title: selectMessageInfoForSysMonitorShow
     * @author ZJHao
     * @param mesAllProcessReqCond
     * @return
     * @throws Exception
     * @return String
     * @throws
     * @date 2015-9-15 上午9:51:35
     */
    @RequestMapping(params="selectMessageInfoForSysMonitorShow",method = RequestMethod.POST ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String selectMessageInfoForSysMonitorShow(MesAllProcessReqCond mesAllProcessReqCond,@ModelAttribute HbasePage hbasePage) {
        try {
            /*系统监控的异常信息默认展示5条*/
            hbasePage.setPageSize(5);
            List<ClientExceptionReq> list=exceptionInfoService.selectMessageInfoForSysMonitorShow(mesAllProcessReqCond,hbasePage);
            return JSONObject.toJSONString(list);
        } catch (Exception e) {
            log.error("查询异常信息用于在系统监控页面展示异常,e:{}",e.getMessage());
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 进入jvm监控界面
     * @Title: intoSysJvmIndex
     * @author ZJHao
     * @return
     * @throws Exception
     * @return String
     * @throws
     * @date 2015-9-15 下午2:02:54
     */
    @RequestMapping(params="intoSysJvmIndex")
    public String intoSysJvmIndex(ApplicationMonitorCond applicationMonitorCond,Model model) throws Exception{
    	model.addAttribute("appMonitorCond", applicationMonitorCond);
    	return "/view/jsp/sysMonitor/sysOneNodeJvm";
    }
    
    /**
     * 获取系统下单个子节点的系统信息
     * @Title: getSysJvmByNameCode
     * @author ZJHao
     * @return
     * @throws Exception
     * @return String
     * @throws
     * @date 2015-9-15 下午3:04:56
     */
    @RequestMapping(params="getSysOneNodeMonitorInfoByNameCode")
    @ResponseBody
    public String getSysOneNodeMonitorInfoByNameCode(ApplicationMonitorCond applicationMonitorCond ) {
        try {
            ApplicationShowDataVo applicationShowDataVo=sysMonitorService.getSysOneNodeMonitorInfoByNameCode(applicationMonitorCond);
            return JSONObject.toJSONString(applicationShowDataVo);
        } catch (Exception e) {
            log.error("获取系统下单个子节点的系统信息异常,e:{}",e.getMessage());
            e.printStackTrace();
        }
        return "";
    }
    /**
     *  查询系统下单个节点的内存使用情况
     * @Title: getSysOneNodeUsageMemory
     * @author ZJHao
     * @param appMonitorCond
     * @return
     * @throws Exception
     * @return String
     * @throws
     * @date 2015-9-15 下午7:42:38
     */
    @RequestMapping(params="getSysOneNodeUsageMemory")
    @ResponseBody
    public String getSysOneNodeUsageMemory(ApplicationMonitorCond appMonitorCond) {
        try {
            List<ApplicationShowDataVo> listAppShowDataVo=sysMonitorService.getSysOneNodeUsageMemory(appMonitorCond);
            return JSONObject.toJSONString(listAppShowDataVo);
        } catch (Exception e) {
            log.error("查询系统下单个节点的内存使用情况异常,e:{}",e.getMessage());
            e.printStackTrace();
        }
        return "";
    }
    
}
