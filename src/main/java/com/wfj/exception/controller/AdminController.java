package com.wfj.exception.controller;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.common.CodeTypeEnum;
import com.wfj.exception.dal.cond.MesAllProcessReqCond;
import com.wfj.exception.dal.entity.MesAllProcessReq;
import com.wfj.exception.dal.entity.ZkNodeDataInfo;
import com.wfj.exception.dal.service.MesAllProcessService;
import com.wfj.exception.dal.service.SysInfoService;
import com.wfj.exception.dal.service.ZkNodeDataService;
import com.wfj.exception.util.HttpUtil;
import com.wfj.websocket.Constants;
import com.wfj.websocket.SystemWebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger log=LoggerFactory.getLogger(AdminController.class);
	
    @Resource(name="sysInfoService")
    private SysInfoService sysInfoService;
    @Resource(name="mesAllProcessService")
    private MesAllProcessService mesAllProcessService;
    @Resource
    private ZkNodeDataService zkNodeDataService;

    @Bean
    public SystemWebSocketHandler systemWebSocketHandler() {
        return new SystemWebSocketHandler();
    }


    @RequestMapping(value = "/auditing")
    @ResponseBody
    public String auditing(HttpServletRequest request) {
        //无关代码都省略了
        String userName = (String) request.getSession().getAttribute(Constants.WEBSOCKET_USERNAME);
        systemWebSocketHandler().sendMessageToUser(userName, new TextMessage("hello ," + userName));
        return "hello zhang";
    }


    @RequestMapping("/messageGet")
    @ResponseBody
    public void messageGet(HttpServletRequest request) {
        Map<String, String> map = HttpUtil.getParameterMap(request.getParameterMap());
        System.out.println(map.get("data"));
        systemWebSocketHandler().sendMessageToUser("192.168.6.117", new TextMessage(map.get("data")));

    }
    

    
    
    @RequestMapping(params = "intoFrame")
    public String intoFrame() throws Exception {
        return "/view/jsp/iframe/indexFrame";
    }

    @RequestMapping("/leftMenu")
    public String leftMenu() {
        return "/view/jsp/iframe/left";
    }

    @RequestMapping("/topMenu")
    public String topMenu() {
        return "/view/jsp/iframe/top";
    }

    @RequestMapping("/rightContain")
    public String rightContain() {
        return "/view/jsp/iframe/right";
    }


    @RequestMapping("/intoIndex")
    public String intoIndex(Model model) throws Exception {
        return "/view/jsp/sysMesIndex";
    }
    /**
     * 根据系统code查询业务异常的展示数量
     *
     * @param model
     * @param messType error表示查询异常的数据  warn表示查询警告的数据
     * @return
     * @throws Exception
     */
    @RequestMapping(params={"findBusiMesBySysCode","toJsp"})
    public String findBusiMesBySysCode(Model model, @RequestParam String sysCode, @RequestParam String messType,@RequestParam String processStatus) throws Exception {
        model.addAttribute("messType", messType);
        model.addAttribute("sysCode", sysCode);
        model.addAttribute("processStatus", processStatus);
        return "/view/jsp/busiIndex";
    }

    /**
     * 根据首页单击异常或者警告的数量进入业务的异常监控界面
     * @Title: intoBusiMesBySysId
     * @author ZJHao
     * @return
     * @throws Exception
     * @return String
     * @throws
     * @date 2015年9月22日 下午7:41:53
     */
    public String intoBusiMesBySysId(Model model,@RequestParam Integer id, @RequestParam String messType) throws Exception{
        model.addAttribute("sysId", id);
        model.addAttribute("messType", messType);
        return "/view/jsp/busiIndex";
    }

    /**
     * 进入异常信息显示详细列表
     * @Title: toMessageListView
     * @author ZJHao
     * @param model
     * @param busiId
     * @param messType
     * @return
     * @throws Exception
     * @return String
     * @throws
     * @date 2015-8-29 下午4:54:49
     */
    @RequestMapping(params="toMessageListView")
    public String toMessageListView(Model model,@RequestParam int busiId, @RequestParam String messType) throws Exception{
        model.addAttribute("busiId", busiId);
        model.addAttribute("messType", messType);
        return "/view/jsp/messageList";
    }


    /**
     * 根据系统code查询系统的前十条处理信息，做成折线图
     * @Title: selectTopTenSysPro
     * @author ZJHao
     * @param model
     * @param sysCode
     * @return
     * @throws Exception
     * @return String
     * @throws
     * @date 2015-8-25 下午2:25:34
     */
    @RequestMapping(params={"selectTopTenSysPro","toView"})
    public String selectTopTenSysPro(Model model,@RequestParam String sysCode) throws Exception{
        log.info("sysCode:{}",sysCode);
        model.addAttribute("code", sysCode);
        model.addAttribute("codeType", "busi");
        return "/view/jsp/sysMesStatisticalChart";
    }

    /**
     * 根据系统code和业务code查询系统的前十条处理信息，做成折线图
     * @Title: selectTopTenSysPro
     * @author ZJHao
     * @param model
     * @return
     * @throws Exception
     * @return String
     * @throws
     * @date 2015-8-25 下午2:25:34
     */
    @RequestMapping(params={"selectTopBusiPro","toView"})
    public String selectTopBusiPro(Model model,@ModelAttribute MesAllProcessReqCond mesAllReqCond) throws Exception{
        mesAllReqCond.setCodeType(CodeTypeEnum.ERR.getCode());
        model.addAttribute("mesAllReqCond",mesAllReqCond);
        return "/view/jsp/busiMesStatisticalChart";
    }

    @RequestMapping("/initSysData")
    @ResponseBody
    public String initSysData(@RequestParam String sysCode,HttpServletRequest request) throws Exception {
        try {
            List<MesAllProcessReq> sysProcessList = mesAllProcessService.selectProcessMesGroupBySysCode(request);
            log.info("根据系统分组查询到的最近一条的处理结果:{}",JSONObject.toJSONString(sysProcessList));
            return JSONObject.toJSONString(sysProcessList);
        } catch (Exception e) {
            log.error("根据系统分组查询到最近一条的处理结果异常:{}",e.getMessage());
        }
        return "";
    }


    /**
     * 根据系统id查询业务异常的展示数量
     *
     * @param messType error表示查询异常的数据  warn表示查询警告的数据
     * @return
     * @throws Exception
     */
    @RequestMapping(params={"findBusiMesBySysCode","toData"})
    @ResponseBody
    public String findBusiMesBySysCode( @RequestParam String sysCode, @RequestParam String messType ,@RequestParam String processStatus) throws Exception {
        try {
            List<MesAllProcessReq> busiProcessList = mesAllProcessService.selectBusiProcessMesBySysCode(sysCode, processStatus);
            log.info("查询到系统:{},异常类型:{},数据为:{}", sysCode, messType, JSONObject.toJSONString(busiProcessList));
            return JSONObject.toJSONString(busiProcessList);
        } catch (Exception e) {
            log.error("根据系统id查询义务异常的展示数量报错:{}", e.getMessage());
        }
        return "";
    }

    /**
     * 读取数据库中当前所有系统的存活节点
     * @Title: getSysChildActiveCount
     * @author ZJHao
     * @return
     * @return Map<String,Integer>
     * @throws
     * @date 2015年9月24日 上午8:53:01
     */
    @RequestMapping(params="getSysChildActiveCount")
    @ResponseBody
    public List<ZkNodeDataInfo> getSysChildActiveCount(HttpServletRequest request){
        List<ZkNodeDataInfo> params= null;
        try {
            params = zkNodeDataService.selectSysActiveNodeCount(request);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取存活节点数据异常");
        }
        return  params;
    }
    
    @RequestMapping(params = "insertBusiByReduce")
	@ResponseBody
	public String insertBusiByReduce() throws Exception {
		System.out.println("insertbusi");
		return "";
	}
    
}