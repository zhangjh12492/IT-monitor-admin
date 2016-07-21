package com.wfj.exception.dal.service;

import com.wfj.exception.util.DataTableJson;
import com.wfj.sysmanager.model.Syuser;
import com.wfj.util.Page;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.sql.SQLException;
import java.util.List;

public interface UserInfoService {

	void insertUserInfo(Syuser user) throws Exception;
	
	List<Syuser> findAllUser(String sysId,String busiId) throws Exception;

    public String delUserInfo(Integer id) throws SQLException;

    public DataTableJson getUserinfoList(Page page) throws SQLException;

    public String saveOrUpdateUser(Syuser userInfo) throws SQLException;
    /**
     * 根据系统编码查询此系统下的所有用户
     * @Title: findUserBySysCode
     * @author ZJHao
     * @param sysCode
     * @return
     * @throws Exception
     * @return List<UserInfo>
     * @throws
     * @date 2015年9月23日 下午2:12:34
     */
    public List<Syuser> findUserBySysCode(String sysCode) throws Exception;

    /**
     * 根据条件查询user
     * @param syuser
     * @return
     */
    public Syuser getUserByCondition(@ModelAttribute Syuser syuser)  throws Exception;
}
