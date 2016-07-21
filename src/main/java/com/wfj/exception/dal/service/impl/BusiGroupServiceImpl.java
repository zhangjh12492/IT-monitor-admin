package com.wfj.exception.dal.service.impl;

import com.wfj.exception.dal.entity.UserGroup;
import com.wfj.exception.dal.entity.UserGroupUserInfoRef;
import com.wfj.exception.dal.entity.UserInfo;
import com.wfj.exception.dal.service.BusiGroupService;
import com.wfj.exception.dal.vo.UserGroupUserInfoVo;
import com.wfj.exception.util.DataTableJson;
import com.wfj.persist.SimpleGenericDAO;
import com.wfj.util.Page;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by MaYong on 2015/8/17.
 */
@Service("busiGroupService")
public class BusiGroupServiceImpl implements BusiGroupService {


    private SimpleGenericDAO<UserGroup, Integer> userGroupDao;
    private SimpleGenericDAO<UserGroupUserInfoRef, Integer> userGroupUserInfoDao;
    private SimpleGenericDAO<UserInfo, Integer> userInfoDao;

    @Transactional
    public String addGroup(Integer groupId, Integer[] ids) throws SQLException {
        for (Integer id : ids) {
            UserGroupUserInfoRef ref = new UserGroupUserInfoRef();
            ref.setUserInfoId(id);
            ref.setUserGroupId(groupId);
            userGroupUserInfoDao.insert(ref);
        }
        return null;
    }

    public String delUserGroup(Integer id) throws SQLException {
        userGroupUserInfoDao.deleteById(id);
        return "";
    }

    public DataTableJson getBusiGroupList(Page page) throws SQLException {
        DataTableJson json = new DataTableJson();
//        List<UserGroup> list = userGroupDao.findListByCond(page.getCond());
        List<UserGroup> list = userGroupDao.pageQueryByCond(page);
        Integer count = userGroupDao.pageQueryCountByCond(page);
        json.setiTotalRecords(count);
        json.setAaData(list);
        return json;
    }

    public String saveOrUpdateGroup(UserGroup userGroup) throws SQLException {
        if (userGroup.getId() == null) {
            //增加
            userGroupDao.insert(userGroup);
        } else {
            userGroupDao.updateById(userGroup);
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

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.userGroupDao = new SimpleGenericDAO<UserGroup, Integer>(sqlSessionTemplate, UserGroup.class);
        this.userGroupUserInfoDao = new SimpleGenericDAO<UserGroupUserInfoRef, Integer>(sqlSessionTemplate, UserGroupUserInfoRef.class);
        this.userInfoDao = new SimpleGenericDAO<UserInfo, Integer>(sqlSessionTemplate, UserInfo.class);
    }

}
