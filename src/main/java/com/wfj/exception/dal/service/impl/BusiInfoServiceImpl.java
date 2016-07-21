package com.wfj.exception.dal.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.dal.entity.BusiInfo;
import com.wfj.exception.dal.entity.BusiInfoTmp;
import com.wfj.exception.dal.entity.SysInfo;
import com.wfj.exception.dal.service.BusiInfoService;
import com.wfj.exception.dal.service.SysInfoService;
import com.wfj.exception.util.DataTableJson;
import com.wfj.persist.SimpleGenericDAO;
import com.wfj.util.Page;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("busiInfoService")
public class BusiInfoServiceImpl implements BusiInfoService {

	
	private static final Logger log=LoggerFactory.getLogger(BusiInfoServiceImpl.class);
	
	private SimpleGenericDAO<BusiInfo, Integer> busiInfoDao;
	private SimpleGenericDAO<BusiInfoTmp, Integer> busiInfoTmpDao;
	private SimpleGenericDAO<SysInfo, Integer> sysInfoDao;

	@Resource
	private SysInfoService sysInfoService;
	@Override
	public void inserBusi(BusiInfo busiInfo) throws Exception {
		busiInfoDao.insert(busiInfo);
	}

	@Override
	public void deleteBusiInfo(BusiInfo busiInfo) throws Exception {
		busiInfoDao.deleteById(busiInfo);
		log.debug("删除业务信息完成");
	}
	@Override
	public BusiInfo updateBusiInfo(BusiInfo busiInfo) throws Exception {
		busiInfoDao.updateById(busiInfo);
		log.debug("修改业务数据完成.");
		return busiInfo;
	}
	@Override
	public DataTableJson findBusiInfoByPage(Page page) throws Exception {
		log.debug("开始分页查询系统信息,page:{}", JSONObject.toJSONString(page));
		DataTableJson json = new DataTableJson();
		List<BusiInfo> list = busiInfoDao.pageQueryByCond(page);
		Integer count = busiInfoDao.pageQueryCountByCond(page);
		json.setiTotalRecords(count);
		json.setAaData(list);
		log.debug("分页查询业务信息结束,count:{},list.size:{},list:{}", count, list.size(), list.toString());
		return json;
	}

	@Override
	public List<BusiInfo> findAllBusi(String sysId) throws Exception {
		BusiInfo busiInfo=new BusiInfo();
		busiInfo.setSysId(Integer.parseInt(sysId));
		List<BusiInfo> busiList = busiInfoDao.findListByStatementCond("selectAllBusi",busiInfo);
		return busiList;
	}

	/**
	 * 通过系统码和业务码获取业务信息
	 * @Title: getBusiInfoBySysBusiCode
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @return BusiInfo
	 * @throws
	 * @date 2015年9月22日 下午5:51:13
	 */
	@Override
	public BusiInfo getBusiInfoBySysBusiCode(Map<String, String> params) throws Exception{
		BusiInfo busiInfo=(BusiInfo) busiInfoDao.findByStatementCond("getBusiInfoBySysBusiCode", params);
		return busiInfo;
	}
	/**
	 * 将mapreduce统计的业务插入数据库
	 * 步骤为,1.查询数据库中busi_debug_tmp数据
	 * 2.用busi_debug_tmp数据与busi_debug的数据匹配,将合适的数据插入busi_debug中
	 * @Title: insertBusiByReduce
	 * @author ZJHao
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 * @date 2015年9月24日 上午10:59:37
	 */
	public void insertBusiByReduce() throws Exception{
		List<BusiInfo> busiInfoList=new ArrayList<>();
		List<BusiInfoTmp> busiInfoTmpList=busiInfoTmpDao.findListByStatementCond("selectAllBusiInfoTmp",null);
		if(busiInfoTmpList!=null&&busiInfoTmpList.size()>0){
			for(BusiInfoTmp busiInfoTmp:busiInfoTmpList){
				SysInfo sysInfo=sysInfoService.selectSysInfoBySysCode(busiInfoTmp.getSysCode());
				if(sysInfo!=null){
					BusiInfo busiInfo=new BusiInfo();
					busiInfo.setBusiCode(busiInfoTmp.getBusiCode());
					busiInfo.setSysId(sysInfo.getId());
					busiInfo.setBusiDesc(busiInfoTmp.getBusiDesc());
					BusiInfo busiInfo1= (BusiInfo) busiInfoDao.findByStatementCond("selectBusiBySysIdAndBusiCode",busiInfo);
					if(busiInfo1==null||busiInfo1.getId()==null){
						busiInfoList.add(busiInfo);
					}
				}
			}
		}
		if(busiInfoList.size()>0){
			for (BusiInfo busiInfo:busiInfoList){
				busiInfoDao.insert(busiInfo);
			}
			log.debug("插入reduce后的busiInfo成功,条数为:{}",busiInfoList.size());
		}
	}
	@Override
	public void deleteAllBusiInfoTmp() throws Exception{
		busiInfoTmpDao.deleteByStatementCond("deleteAllBusiInfoTmp",null);
	}

	/**
	 * 根据条件查询busiInfo
	 * @param busiInfo
	 * @return
	 * @throws Exception
	 */
	@Override
	public BusiInfo findBusiInfoByCondition(BusiInfo busiInfo) throws Exception{
		BusiInfo busiInfo1= (BusiInfo) busiInfoDao.findByStatementCond("findBusiInfoByCondition",busiInfo);
		return busiInfo1;
	}
	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.busiInfoDao = new SimpleGenericDAO<BusiInfo, Integer>(sqlSessionTemplate, BusiInfo.class);
		this.busiInfoTmpDao = new SimpleGenericDAO<BusiInfoTmp, Integer>(sqlSessionTemplate, BusiInfoTmp.class);
		this.sysInfoDao = new SimpleGenericDAO<SysInfo, Integer>(sqlSessionTemplate, SysInfo.class);
	}


}
