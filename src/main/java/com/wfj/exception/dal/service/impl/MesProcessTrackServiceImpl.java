package com.wfj.exception.dal.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.dal.entity.MesProcessTrack;
import com.wfj.exception.dal.service.MesProcessTrackService;
import com.wfj.exception.util.DataTableJson;
import com.wfj.persist.SimpleGenericDAO;
import com.wfj.util.Page;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mesProcessTrackService")
public class MesProcessTrackServiceImpl implements MesProcessTrackService {

	private static final Logger log = LoggerFactory.getLogger(MesProcessTrackServiceImpl.class);
	private SimpleGenericDAO<MesProcessTrack, Integer> mesProcessTrackDao;

	/**
	 * 分页查询信息处理轨迹表
	 * @Title: findMesProcessTrackByPage
	 * @author ZJHao
	 * @param page
	 * @return
	 * @throws Exception
	 * @throws
	 * @date 2015-8-30 下午10:37:41
	 */
	@Override
	public DataTableJson findMesProcessTrackByPage(Page page) throws Exception {
		log.info("开始分页查询异常信息处理轨迹,page:{}", JSONObject.toJSONString(page));
		DataTableJson json = new DataTableJson();
		List<MesProcessTrack> list = mesProcessTrackDao.pageQueryByStatementCond("selectByCondGroupErrId", page);
		Integer count = mesProcessTrackDao.pageQueryCountByStatementCond("selectCountByCondGroupErrId", page);
		json.setiTotalRecords(count);
		json.setAaData(list);
		log.info("分页查询异常信息处理轨迹结束,count:{},list.size:{},list:{}", count, list.size(), list.toString());
		return json;
	}

	/**
	  * 添加信息处理轨迹
	  * @Title: addMesProcessTrack
	  * @author ZJHao
	  * @param mesTrack
	  * @throws Exception
	  * @return void
	  * @throws
	  * @date 2015-8-31 上午10:22:49
	  */
	@Override
	public void addMesProcessTrack(MesProcessTrack mesTrack) throws Exception {
		mesProcessTrackDao.insert(mesTrack);
	}

	@Override
	public void addMoreMesProcessTrack(List<MesProcessTrack> mesProcessTracks) throws Exception{
		if (mesProcessTracks!=null && mesProcessTracks.size()>0){
			for(int i=0;i<mesProcessTracks.size();i++){
				mesProcessTrackDao.insert(mesProcessTracks.get(i));
			}
		}
	}

	@Override
	public DataTableJson getMesProcessTrackListByErrId(Page page) throws Exception {
		log.info("开始分页查询系统信息,page:{}", JSONObject.toJSONString(page));
		DataTableJson json = new DataTableJson();
		List<MesProcessTrack> list = mesProcessTrackDao.pageQueryByStatementCond("selectByErrId", page);
		Integer count = mesProcessTrackDao.pageQueryCountByStatementCond("selectCountByErrId", page);
		json.setiTotalRecords(count);
		json.setAaData(list);
		log.info("分页查询系统信息结束,count:{},list.size:{},list:{}", count, list.size(), list.toString());
		return json;
	}


	@Autowired
	public void setMesProcessTrackDao(SqlSessionTemplate sqlSessionTemplate) {
		this.mesProcessTrackDao = new SimpleGenericDAO<MesProcessTrack, Integer>(sqlSessionTemplate, MesProcessTrack.class);
	}

}
