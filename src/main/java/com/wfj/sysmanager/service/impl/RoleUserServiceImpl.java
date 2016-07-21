package com.wfj.sysmanager.service.impl;

import com.wfj.exception.dal.entity.UserGroup;
import com.wfj.exception.dal.entity.UserGroupUserInfoRef;
import com.wfj.exception.dal.entity.UserInfo;
import com.wfj.exception.dal.vo.UserGroupUserInfoVo;
import com.wfj.exception.util.DataTableJson;
import com.wfj.persist.SimpleGenericDAO;
import com.wfj.sysmanager.dto.RoleUserDto;
import com.wfj.sysmanager.entity.PcdsSyuser2;
import com.wfj.sysmanager.entity.PcdsSyuserSyrole;
import com.wfj.sysmanager.service.RoleUserService;
import com.wfj.sysmanager.util.UuidUtil;
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
@Service("roleUserService")
public class RoleUserServiceImpl implements RoleUserService {


    private SimpleGenericDAO<PcdsSyuserSyrole, String> pcdsSyuserSyroleDao;

    @Transactional
    public String addUser(String roleId, String[] ids) throws SQLException {
        for (String id : ids) {
            PcdsSyuserSyrole ref = new PcdsSyuserSyrole();
            ref.setSyroleId(roleId);
            ref.setSyuserId(id);
            ref.setId(UuidUtil.getUuid());
            pcdsSyuserSyroleDao.insert(ref);
        }
        return null;
    }

    public String delRoleUser(String id) throws SQLException {
        pcdsSyuserSyroleDao.deleteById(id);
        return "";
    }


    public String saveOrUpdateGroup(PcdsSyuserSyrole roleUser) throws SQLException {
        if (roleUser.getId() == null || roleUser.getId().equals("")) {
            //增加
            pcdsSyuserSyroleDao.insert(roleUser);
        } else {
            pcdsSyuserSyroleDao.updateById(roleUser);
        }
        return "";
    }


    public DataTableJson getNotExistUser(Page page) throws SQLException {
        DataTableJson json = new DataTableJson();
//        List<UserGroup> list = pcdsSyuserSyroleDao.findListByCond(page.getCond());
        List<PcdsSyuser2> list = pcdsSyuserSyroleDao.pageQueryByStatementCond("getNotExistUser", page);
        Integer count = pcdsSyuserSyroleDao.pageQueryCountByStatementCond("getNotExistUserCount", page);
        json.setiTotalRecords(count);
        json.setAaData(list);
        return json;
    }

    public DataTableJson selectUsersByGroupid(Page page) throws SQLException {
        DataTableJson json = new DataTableJson();
//        List<UserGroup> list = pcdsSyuserSyroleDao.findListByCond(page.getCond());
        List<RoleUserDto> list = pcdsSyuserSyroleDao.pageQueryByStatementCond("selectUsersByRoleid", page);
        Integer count = pcdsSyuserSyroleDao.pageQueryCountByStatementCond("selectUsersCountByRoleid", page);
        json.setiTotalRecords(count);
        json.setAaData(list);
        return json;
    }

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.pcdsSyuserSyroleDao = new SimpleGenericDAO<PcdsSyuserSyrole, String>(sqlSessionTemplate, PcdsSyuserSyrole.class);
    }

}
