package com.wfj.exception.dal.service.impl;

import com.wfj.exception.dal.entity.*;
import com.wfj.exception.dal.service.SysGroupService;
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
@Service("sysGroupService")
public class SysGroupServiceImpl implements SysGroupService {


    private SimpleGenericDAO<UserGroup, Integer> userGroupDao;
    private SimpleGenericDAO<SysInfoUserGroupRef, Integer> sysInfoUserGroupRefDao;
    private SimpleGenericDAO<UserInfo, Integer> userInfoDao;
    private SimpleGenericDAO<SysInfo, Integer> sysInfoDao;

    @Transactional
    public String addGroup(Integer sysInfoId, Integer[] ids) throws SQLException {
        for (Integer id : ids) {
            SysInfoUserGroupRef ref = new SysInfoUserGroupRef();
            ref.setSysInfoId(sysInfoId);
            ref.setUserGroupId(id);
            sysInfoUserGroupRefDao.insert(ref);
        }
        return null;
    }

    public String delUserGroup(Integer id) throws SQLException {
        sysInfoUserGroupRefDao.deleteById(id);
        return "";
    }

    public DataTableJson getSystemList(Page page) throws SQLException {
        DataTableJson json = new DataTableJson();
//        List<UserGroup> list = userGroupDao.findListByCond(page.getCond());
        List<SysInfo> list = sysInfoDao.pageQueryByCond(page);
        Integer count = sysInfoDao.pageQueryCountByCond(page);
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


    public DataTableJson getNotExistGroup(Page page) throws SQLException {
        DataTableJson json = new DataTableJson();
//        List<UserGroup> list = userGroupDao.findListByCond(page.getCond());
        List<UserGroupUserInfoVo> list = sysInfoUserGroupRefDao.pageQueryByStatementCond("getNotExistGroup", page);
        Integer count = sysInfoUserGroupRefDao.pageQueryCountByStatementCond("getNotExistGroupCount", page);
        json.setiTotalRecords(count);
        json.setAaData(list);
        return json;
    }

    public DataTableJson selectGroupBySysid(Page page) throws SQLException {
        DataTableJson json = new DataTableJson();
//        List<UserGroup> list = userGroupDao.findListByCond(page.getCond());
        List<UserGroupUserInfoVo> list = sysInfoUserGroupRefDao.pageQueryByStatementCond("selectGroupBySysid", page);
        Integer count = sysInfoUserGroupRefDao.pageQueryCountByStatementCond("selectGroupCountBySysid", page);
        json.setiTotalRecords(count);
        json.setAaData(list);
        return json;
    }

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.userGroupDao = new SimpleGenericDAO<UserGroup, Integer>(sqlSessionTemplate, UserGroup.class);
        this.sysInfoUserGroupRefDao = new SimpleGenericDAO<SysInfoUserGroupRef, Integer>(sqlSessionTemplate, SysInfoUserGroupRef.class);
        this.userInfoDao = new SimpleGenericDAO<UserInfo, Integer>(sqlSessionTemplate, UserInfo.class);
        this.sysInfoDao = new SimpleGenericDAO<SysInfo, Integer>(sqlSessionTemplate, SysInfo.class);
    }

}
