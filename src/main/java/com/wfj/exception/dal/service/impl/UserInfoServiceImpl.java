package com.wfj.exception.dal.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfj.exception.dal.entity.UserGroupUserInfoRef;
import com.wfj.exception.dal.service.UserInfoService;
import com.wfj.exception.dal.vo.UserGroupUserInfoVo;
import com.wfj.exception.util.DataTableJson;
import com.wfj.persist.SimpleGenericDAO;
import com.wfj.sysmanager.model.Syuser;
import com.wfj.util.Page;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService{

    private SimpleGenericDAO<UserGroupUserInfoRef, Integer> userGroupUserInfoDao;
    private SimpleGenericDAO<Syuser, Integer> userInfoDao;

	@Override
	public void insertUserInfo(Syuser user) throws Exception {
//		userInfoDao.insertUserInfo(user);
	}

	@Override
	public List<Syuser> findAllUser(String sysId, String busiId) throws Exception {
//		List<UserInfo> userList=userInfoDao.selectAllUser(StringUtils.isBlank(sysId)?null:sysId, StringUtils.isBlank(busiId)?null:busiId);
		return null;
	}



    public String delUserInfo(Integer id) throws SQLException {
        UserGroupUserInfoRef ref = new UserGroupUserInfoRef();
        ref.setUserInfoId(id);
        Integer count = userGroupUserInfoDao.findListCountByCond(ref);
        if (count > 0) {
            return "已经使用，不能删除";
        }
        userInfoDao.deleteById(id);
        return "";
    }

    public DataTableJson getUserinfoList(Page page) throws SQLException {
        DataTableJson json = new DataTableJson();
//        List<UserGroup> list = userGroupDao.findListByCond(page.getCond());
        List<Syuser> list = userInfoDao.pageQueryByCond(page);
        Integer count = userInfoDao.pageQueryCountByCond(page);
        json.setiTotalRecords(count);
        json.setAaData(list);
        return json;
    }

    public String saveOrUpdateUser(Syuser userInfo) throws SQLException {
        if (userInfo.getId() == null) {
            //增加
            userInfoDao.insert(userInfo);
        } else {
            userInfoDao.updateById(userInfo);
        }
        return "";
    }


    public DataTableJson getNotExistUser(Page page) throws SQLException {
        DataTableJson json = new DataTableJson();
//        List<UserGroup> list = userGroupDao.findListByCond(page.getCond());
        List<UserGroupUserInfoVo> list = userInfoDao.pageQueryByStatementCond("getNotExistUser", page);
        Integer count = userInfoDao.pageQueryCountByStatementCond("getNotExistUserCount", page);
        json.setiTotalRecords(count);
        json.setAaData(list);
        return json;
    }

    public DataTableJson selectUsersByGroupid(Page page) throws SQLException {
        DataTableJson json = new DataTableJson();
//        List<UserGroup> list = userGroupDao.findListByCond(page.getCond());
        List<UserGroupUserInfoVo> list = userGroupUserInfoDao.pageQueryByStatementCond("selectUsersByGroupid", page);
        Integer count = userGroupUserInfoDao.pageQueryCountByStatementCond("selectUsersCountByGroupid", page);
        json.setiTotalRecords(count);
        json.setAaData(list);
        return json;
    }
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
    public List<Syuser> findUserBySysCode(String sysCode) throws Exception{
    	Map<String, String> params=new HashMap<String, String>();
    	params.put("code", sysCode);
    	List<Syuser> listUser=userInfoDao.findListByStatementCond("getUserBySysCodeSendMes", params);	//获取此系统下的所有的消息发送配置信息
    	return listUser;
    }

    /**
     * 根据条件查询user
     * @param syuser
     * @return
     */
    @Override
    public Syuser getUserByCondition(@ModelAttribute Syuser syuser) throws Exception{
        Syuser syuser1=userInfoDao.findById(syuser.getId());
        return syuser1;
    }

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.userGroupUserInfoDao = new SimpleGenericDAO<UserGroupUserInfoRef, Integer>(sqlSessionTemplate, UserGroupUserInfoRef.class);
        this.userInfoDao = new SimpleGenericDAO<Syuser, Integer>(sqlSessionTemplate, Syuser.class);
    }



}
