package com.wfj.exception.dal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.dal.cond.EarlyWarnSysInfoCond;
import com.wfj.exception.dal.entity.EarlyWarnSysInfo;
import com.wfj.exception.dal.entity.MesEarlyWarnInfo;
import com.wfj.exception.dal.service.MesEarlyWarnService;
import com.wfj.exception.util.DataTableJson;
import com.wfj.persist.SimpleGenericDAO;
import com.wfj.util.Page;

@Service("mesEarlyWarnService")
public class MesEarlyWarnServiceImpl implements MesEarlyWarnService{

	private static final Logger log=LoggerFactory.getLogger(MesEarlyWarnServiceImpl.class);

	private SimpleGenericDAO<MesEarlyWarnInfo, Integer> mesEarlyWarnInfoDao;
	private SimpleGenericDAO<EarlyWarnSysInfo, Integer> ewSysInfoDao;
	
	@Override
	public void insertMesEarlyWarn(MesEarlyWarnInfo param) throws Exception {
		log.info("开始插入预警配置基础信息...{}",JSONObject.toJSONString(param));
		mesEarlyWarnInfoDao.insert(param);
		log.info("结束插入预警配置基础信息,params:{}",param.toString());
	}

	@Override
	public void updateMesEarlyWarn(MesEarlyWarnInfo param) throws Exception {
		log.info("开始修改预警配置基础信息...{}",JSONObject.toJSONString(param));
		mesEarlyWarnInfoDao.updateById(param);
		log.info("结束修改预警配置基础信息:param:{}",param.toString());
	}

	@Override
	public DataTableJson findEarlyWarnInfoByPage(Page page) throws Exception {
		log.info("开始分页查询预警配置信息,page:{}",JSONObject.toJSONString(page));
		DataTableJson json = new DataTableJson();
		List<MesEarlyWarnInfo> list = mesEarlyWarnInfoDao.pageQueryByCond(page);
		Integer count = mesEarlyWarnInfoDao.pageQueryCountByCond(page);
		json.setiTotalRecords(count);
		json.setAaData(list);
		log.info("分页查询预警配置信息结束,count:{},list.size:{},list:{}",count,list.size(),list.toString());
		return json;
	}
	
	@Override
	public void deleteMesEarlyWarn(Integer id) throws Exception {
		MesEarlyWarnInfo sysInfo = new MesEarlyWarnInfo();
		sysInfo.setId(id);
		mesEarlyWarnInfoDao.deleteById(sysInfo);
		log.info("删除预警配置信息完成");
		
	}
	
	@Override
	public void deleteEwSysRefByAllId(EarlyWarnSysInfoCond ewSys) throws Exception{
		log.info("删除预警配置关联系统信息,ewSys:{}", ewSys.toString());
		ewSysInfoDao.deleteByStatementCond("deleteByAllId", ewSys);
		log.info("删除预警配置关联系统信息成功.");
	}

	@Override
	public void insertMoreEwSys(Integer ewId, Integer[] sysIds) throws Exception {
		log.info("开始插入系统预警关联信息,ewId:{},sysIds:{}", ewId, sysIds.toString());
		if(sysIds!=null&&sysIds.length>0){
			for(int i=0;i<sysIds.length;i++){
				EarlyWarnSysInfo ewSysInfo=new EarlyWarnSysInfo();
				ewSysInfo.setEwId(ewId);
				ewSysInfo.setSysId(sysIds[i]);
				ewSysInfoDao.insert(ewSysInfo);
			}
		}
		log.info("开始插入系统预警关联信息结束... ");
	}
	
	/**
	 * 根据系统id获取系统的预警配置信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<MesEarlyWarnInfo> findMesEarlyInfoBySysId(MesEarlyWarnInfo mesEarlyWarnInfo) throws Exception{
		List<MesEarlyWarnInfo> list=mesEarlyWarnInfoDao.findListByStatementCond("findMesEarlyInfoBySysId", mesEarlyWarnInfo);
		return list;
	}
	/**
	 * 根据系统code查询系统的预警配置信息
	 * @Title: findMesEarlyInfoBySysCode
	 * @author ZJHao
	 * @param sysCode
	 * @return
	 * @throws Exception
	 * @return List<MesEarlyWarnInfo>
	 * @throws
	 * @date 2015年9月22日 下午4:54:28
	 */
	@Override
	public List<MesEarlyWarnInfo> findMesEarlyInfoBySysCode(String sysCode) throws Exception{
		Map<String,String> params=new HashMap<String,String>();
		params.put("sysCode", sysCode);
		List<MesEarlyWarnInfo> mesList=mesEarlyWarnInfoDao.findListByStatementCond("selectBySysCode", params);
		return mesList;
	}

	/**
	 * 根据系统code查询系统的单个预警配置信息
	 * @Title: findMesEarlyInfoBySysCode
	 * @author ZJHao
	 * @param sysCode
	 * @return
	 * @throws Exception
	 * @return List<MesEarlyWarnInfo>
	 * @throws
	 * @date 2015年9月22日 下午4:54:28
	 */
	@Override
	public MesEarlyWarnInfo findOneMesEarlyInfoBySysCode(String sysCode) throws Exception{
		List<MesEarlyWarnInfo> mesEarlyWarns=this.findMesEarlyInfoBySysCode(sysCode);
		if(mesEarlyWarns!=null&&mesEarlyWarns.size()>0){
			return mesEarlyWarns.get(0);
		}else{
			return new MesEarlyWarnInfo(true);
		}
	}
	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.mesEarlyWarnInfoDao = new SimpleGenericDAO<MesEarlyWarnInfo, Integer>(sqlSessionTemplate, MesEarlyWarnInfo.class);
		this.ewSysInfoDao=new SimpleGenericDAO<EarlyWarnSysInfo, Integer>(sqlSessionTemplate,EarlyWarnSysInfo.class);
	}

	

	


	

}
