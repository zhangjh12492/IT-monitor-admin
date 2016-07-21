package com.wfj.exception.dal.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.common.ErrProcessStatusEnum;
import com.wfj.exception.dal.cond.MesAllProcessReqCond;
import com.wfj.exception.dal.entity.MesAllProcessReq;
import com.wfj.exception.dal.entity.SysInfo;
import com.wfj.exception.dal.service.SysInfoService;
import com.wfj.exception.util.DataTableJson;
import com.wfj.persist.SimpleGenericDAO;
import com.wfj.sysmanager.httpModel.User;
import com.wfj.util.Page;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("sysInfoService")
public class SysInfoServiceImpl implements SysInfoService {

	private static final Logger log = LoggerFactory.getLogger(SysInfoServiceImpl.class);

	private SimpleGenericDAO<SysInfo, Integer> sysInfoDao;
	private SimpleGenericDAO<MesAllProcessReq, Integer> mesAllProcessReqDao;

	@Override
	public String insertSysInfo(SysInfo sysInfo) throws Exception {
		sysInfoDao.insert(sysInfo);
		log.debug("插入系统数据完成,id:{}", sysInfo.getId());
		return String.valueOf(sysInfo.getId());
	}

	@Override
	public List<SysInfo> selectAllSys(String sysCode) throws Exception {
		SysInfo sysInfo=new SysInfo();
		sysInfo.setSysCode(sysCode);
		log.debug("查询所有的sysInfo信息,sysCode:{}", sysCode);
		List<SysInfo> sysList=sysInfoDao.findListByStatementCond("selectAll", sysInfo);
		log.debug("查询所有的sysInfo信息结束,result:{}",sysList.toString());
		return sysList;
	}

	@Override
	public SysInfo updateSysInfo(SysInfo sysInfo) throws Exception {
		sysInfoDao.updateById(sysInfo);
		log.info("修改系统数据完成.");
		return sysInfo;
	}

	@Override
	public void deleteSysInfo(int id) throws Exception {
		SysInfo sysInfo = new SysInfo();
		sysInfo.setId(id);
		sysInfoDao.deleteById(sysInfo);
		log.info("删除系统信息完成");
	}

	@Override
	public DataTableJson findSysInfoByPage(Page page) throws Exception {
		log.info("开始分页查询系统信息,page:{}",JSONObject.toJSONString(page));
		DataTableJson json = new DataTableJson();
		List<SysInfo> list = sysInfoDao.pageQueryByCond(page);
		Integer count = sysInfoDao.pageQueryCountByCond(page);
		json.setiTotalRecords(count);
		json.setAaData(list);
		log.info("分页查询系统信息结束,count:{},list.size:{},list:{}",count,list.size(),list.toString());
		return json;
	}
	

	/**
	 * 根据系统id查询系统信息
	 * @Title: selectBySysId
	 * @author ZJHao
	 * @param sysId
	 * @return
	 * @throws Exception
	 * @throws
	 * @date 2015-8-29 下午11:58:37
	 */
	@Override
	public SysInfo selectBySysId(Integer sysId) throws Exception {
		SysInfo sysInfo=sysInfoDao.findById(sysId);
		return sysInfo;
	}
	
	 /**
	  * 根据用户获取到用户所属的系统异常处理结果
	  * @param user
	  * @return
	  * @throws Exception
	  */
	 @Override
	 public List<MesAllProcessReq> selectUserProcessMesCountByUser(User user) throws Exception{
		 List<SysInfo> sysList=this.selectSysByUser(user);
		 List<MesAllProcessReq> mesAllReqList=new ArrayList<MesAllProcessReq>();
		 log.info("sysList:{}",sysList.toString());
		 if(sysList!=null&&sysList.size()>0){
			 for(int i=0;i<sysList.size();i++){
				 MesAllProcessReqCond mesAllReqCond=new MesAllProcessReqCond();
				 mesAllReqCond.setCode(sysList.get(i).getSysCode());
				 mesAllReqCond.setProcessStatus(ErrProcessStatusEnum.UNDISPOSED.getCode());
				 MesAllProcessReq mesAllReq=(MesAllProcessReq) mesAllProcessReqDao.findByStatementCond("selectLatelyBySysCode",mesAllReqCond );
				 if(mesAllReq!=null)
				 mesAllReqList.add(mesAllReq);
			 }
		 }
		 log.info("mesAllReqList:{}",mesAllReqList.toString());
		 return mesAllReqList;
	 }
	
	 
	 /**
	  * 根据当前登陆的用户查询用户所属系统
	  * @Title: selectSysByUser
	  * @author ZJHao
	  * @param user
	  * @return
	  * @throws Exception
	  * @return List<Syuser>
	  * @throws
	  * @date 2015年9月23日 下午4:30:38
	  */
	 @Override
	 public List<SysInfo> selectSysByUser(User user) throws Exception{
		 List<SysInfo> sysList=sysInfoDao.findListByStatementCond("selectSysByUser", user);
		 return sysList;
	 }
	/**
	 * 根据系统code查询系统信息
	 * @param sysCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public SysInfo selectSysInfoBySysCode(String sysCode) throws Exception {
		List<SysInfo> sysInfoList=this.selectAllSys(sysCode);
		if (sysInfoList!=null&&sysInfoList.size()>0) {
			return sysInfoList.get(0);
		}else{
			return null;
		}
	}

	
	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sysInfoDao = new SimpleGenericDAO<SysInfo, Integer>(sqlSessionTemplate, SysInfo.class);
		this.mesAllProcessReqDao = new SimpleGenericDAO<MesAllProcessReq, Integer>(sqlSessionTemplate, MesAllProcessReq.class);
	}

	

	

	
}
