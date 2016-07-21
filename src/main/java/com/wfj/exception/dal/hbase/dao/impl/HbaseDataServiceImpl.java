package com.wfj.exception.dal.hbase.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.common.HbasePage;
import com.wfj.exception.common.RequestResult;
import com.wfj.exception.common.SysConstants;
import com.wfj.exception.dal.hbase.dao.HbaseDataService;
import com.wfj.exception.dal.vo.ClientExceptionReq;
import com.wfj.exception.util.HttpUtil;
import com.wfj.exception.util.PropertiesLoad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("hbaseDataService")
public class HbaseDataServiceImpl implements HbaseDataService{

	private static final Logger log=LoggerFactory.getLogger(HbaseDataServiceImpl.class);
    @Value("#{configProperties['netty.hbase_netty_url']}")
    private String nettyUrl;

    /**
     * 根据异常信息id查询单挑异常信息
     * @Title: selectById
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author ZJHao
     * @param id
     * @return
     * @throws Exception
     * @throws
     * @date 2015-9-15 上午9:42:26
     */
    @Override
	public ClientExceptionReq selectById(String id ) throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		map.put("errId", id);
		log.info("开始请求hbase单条异常数据,入参:{}",JSONObject.toJSONString(map));
		String result=HttpUtil.http(PropertiesLoad.getProperties("hbase.getData.findMessByErrId"), map);
		log.info("获取hbase中单条异常数据结束,出参:{}",result);
		String content=(String) JSONObject.parseObject(result,RequestResult.class).getContent();
		ClientExceptionReq clientReq=JSONObject.parseObject(content,ClientExceptionReq.class);
		return clientReq;
	}

    /**
     * 根据业务id查询异常信息列表
     * @Title: getExceptionsByBusiId
     * @author ZJHao
     * @return
     * @throws Exception
     * @throws
     * @date 2015-9-15 上午9:42:04
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<ClientExceptionReq> getExceptionsByBusiId(HbasePage hbasePage) throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		map.put("data", JSONObject.toJSONString(hbasePage));
		log.info("开始请求hbase业务中的数据,入参:{}",JSONObject.toJSONString(map));
		String result=HttpUtil.http(PropertiesLoad.getProperties("hbase.getData.findMessesByErrReq"), map);
		log.info("获取hbase中业务下的数据结束,出参:{}",result);
		String content=(String) JSONObject.parseObject(result,RequestResult.class).getContent();
		List<ClientExceptionReq> listReq=  JSONObject.parseObject(content,List.class);
		return listReq;
	}
	
	/**
	 * 修改异常信息的状态
	 * @Title: updateMesProcessStatus
	 * @author ZJHao
	 * @param sysCode
	 * @param beforeProcessStatus
	 * @param afterProcessStatus
	 * @param errLevel
	 * @return
	 * @throws Exception
	 * @throws
	 * @date 2015-9-15 上午9:42:45
	 */
	@Override
	public String updateMesProcessStatus(String sysCode, String beforeProcessStatus,String afterProcessStatus,String errLevel) throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		Map<String,String> param=new HashMap<String, String>();
		param.put("sysCode", sysCode);
		param.put("beforeProcessStatus", beforeProcessStatus);
		param.put("afterProcessStatus", afterProcessStatus);
		param.put("errLevel", errLevel);
		log.info("开始请求hbase,根据系统编码修改信息的操作状态...param:{}",param.toString());
		String result=HttpUtil.http(PropertiesLoad.getProperties("hbase.getData.updateMesProcessStatus"), param);
		log.info("结束请求hbase,根据系统编码修改信息的操作状态...");
		return result;
	}

	/**
	 * 查询异常信息
	 * @Title: getExceptionsByErrCode
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @throws
	 * @date 2015-9-15 上午9:43:04
	 */
	@Override
	public List<ClientExceptionReq> getExceptionsByErrCode(HbasePage hbasePage) throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("data", JSONObject.toJSONString(hbasePage));
		map.put(SysConstants.HBASE_NETTY_MAP_METHOD_NAME, "getExceptionsByErrCode");
		log.info("开始请求hbase业务中的数据,入参:{}",JSONObject.toJSONString(map));
		String result=HttpUtil.http(PropertiesLoad.getProperties("hbase.getData.findMessesByErrIds"), map);
		log.info("获取hbase中业务下的数据结束,出参:{}",result);
		String content=(String) JSONObject.parseObject(result,RequestResult.class).getContent();
		List<ClientExceptionReq> listReq=  JSONObject.parseObject(content,List.class);
		return listReq;
		
	}

	/**
	 * 根据日期、code类型、code、日期周期获取异常信息列表
	 * @Title: findExceptionListOneHour
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @throws
	 * @date 2015-8-29 下午5:29:54
	 */
	@Override
	public List<ClientExceptionReq> findExceptionListOneHour(HbasePage hbasePage) throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		map.put("data", JSONObject.toJSONString(hbasePage));
		log.info("开始请求hbase业务中的数据,入参:{}",JSONObject.toJSONString(map));
		String result=HttpUtil.http(PropertiesLoad.getProperties("hbase.getData.showMessListFromChart"),map);
		log.info("获取hbase中业务下的数据结束,出参:{}",result);
		String content=(String) JSONObject.parseObject(result,RequestResult.class).getContent();
		List listReq=  JSONObject.parseObject(content,List.class);
		return listReq;
	}
	
	
	/**
	 * 查询当前登陆的用户的所有未处理的异常信息
	 * @Title: showMesListByUser
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @return List<ClientExceptionReq>
	 * @throws
	 * @date 2015年9月23日 下午5:16:05
	 */
	@Override
	public List showMessListByUserNoProcess(HbasePage hbasePage) throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("data", JSONObject.toJSONString(hbasePage));
		log.info("开始请求hbase中当前登陆的用户的异常列表,入参:{}", JSONObject.toJSONString(hbasePage));
		String result= HttpUtil.http(PropertiesLoad.getProperties("hbase.getData.showMessListByUserNoProcess"), map);
		log.debug("获取hbase中当前登陆的用户的异常列表成功,\nresult:{}",result);
		String content=(String) JSONObject.parseObject(result,RequestResult.class).getContent();
		List listReq=  JSONObject.parseObject(content,List.class);
		return listReq;
	}
	
	/**
	 * 1.修改单条信息
	 * 2.插入异常信息处理轨迹
	 * @Title: updateOneMes
	 * @author ZJHao
	 * @param req
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 * @date 2015-8-30 下午5:48:47
	 */
	@Override
	public String updateOneMes(ClientExceptionReq req) throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		map.put("data", JSONObject.toJSONString(req));
		log.info("修改hbase中数据,入参:{}",JSONObject.toJSONString(map));
		String result=HttpUtil.http(PropertiesLoad.getProperties("hbase.getData.updateMesOneStatus"),map);
		log.info("修改hbase中数据结束,出参:{}",result);
		return result;
	}
	
	/**
	 * 修改多条信息
	 * @Title: updateOneMes
	 * @author ZJHao
	 * @param req
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 * @date 2015-8-30 下午5:48:47
	 */
	@Override
	public String updateMoreMesStatus(ClientExceptionReq req ) throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		map.put("data", JSONObject.toJSONString(req));
		map.put(SysConstants.HBASE_NETTY_MAP_METHOD_NAME, "updateMoreMesStatus");
		log.info("修改hbase中数据,入参:{}",JSONObject.toJSONString(map));
		String result=HttpUtil.http(PropertiesLoad.getProperties("hbase.getData.updateMoreMesStatus"),map);
		log.info("修改hbase中数据结束,出参:{}",result);
		return result;
	}

	/**
	 * 查询异常信息用于在系统监控页面展示
	 * @Title: selectMessageInfoForSysMonitorShow
	 * methodName: selectMessageInfoForSysMonitorShow
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @throws
	 * @date 2015-9-15 上午9:44:55
	 */
	@Override
	public List<ClientExceptionReq> selectMessageInfoForSysMonitorShow(HbasePage hbasePage) throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		map.put("data", JSONObject.toJSONString(hbasePage));
		map.put(SysConstants.HBASE_NETTY_MAP_METHOD_NAME, "selectMessageInfoForSysMonitorShow");
		log.info("开始请求hbase业务中的数据,入参:{}",JSONObject.toJSONString(map));
		String result=HttpUtil.http(PropertiesLoad.getProperties("hbase.getData.selectMessageInfoForSysMonitorShow"),map);
		log.info("获取hbase中业务下的数据结束,出参:{}",result);
		String content=(String) JSONObject.parseObject(result,RequestResult.class).getContent();
		List<ClientExceptionReq> listReq=  JSONObject.parseObject(content,List.class);
		return listReq;
	}
}
