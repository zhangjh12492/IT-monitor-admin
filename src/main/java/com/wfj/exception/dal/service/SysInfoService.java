package com.wfj.exception.dal.service;

import com.wfj.exception.dal.entity.MesAllProcessReq;
import com.wfj.exception.dal.entity.SysInfo;
import com.wfj.exception.util.DataTableJson;
import com.wfj.sysmanager.httpModel.User;
import com.wfj.util.Page;

import java.util.List;

public interface SysInfoService {

	 String insertSysInfo(SysInfo sysInfo) throws Exception;
	
	 List<SysInfo>  selectAllSys(String sysCode) throws Exception;

	/**
	 * 根据系统code查询系统信息
	 * @param sysCode
	 * @return
	 * @throws Exception
	 */
	 SysInfo selectSysInfoBySysCode(String sysCode) throws Exception;
	 


	 SysInfo updateSysInfo(SysInfo sysInfo) throws Exception;
	 
	 SysInfo selectBySysId(Integer sysId) throws Exception;
	 
	 void deleteSysInfo(int id) throws Exception;
	 
	 public DataTableJson findSysInfoByPage(Page page) throws Exception;
	 
	 /**
	  * 根据用户获取到用户所属的系统的异常数据处理结果
	  * @param user
	  * @return
	  * @throws Exception
	  */
	 public List<MesAllProcessReq> selectUserProcessMesCountByUser(User user) throws Exception;
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
	 public List<SysInfo> selectSysByUser(User user) throws Exception;
}
