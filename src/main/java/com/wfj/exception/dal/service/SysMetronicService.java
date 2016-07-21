package com.wfj.exception.dal.service;

import java.util.List;

import com.wfj.exception.dal.vo.ApplicationShowDataVo;

public interface SysMetronicService {

	/**
	 * 获取初始化系统监控信息的数据
	 * @Title: initAppShowData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author ZJHao
	 * @return
	 * @return List<ApplicationShowDataVo>
	 * @throws
	 * @date 2015-9-14 上午12:47:42
	 */
	public List<ApplicationShowDataVo> initAppShowData() throws Exception;
}
