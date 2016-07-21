package com.wfj.sysmanager.service.impl;

import com.wfj.exception.util.DataTableJson;
import com.wfj.persist.SimpleGenericDAO;
import com.wfj.sysmanager.dto.RoleUserDto;
import com.wfj.sysmanager.entity.PcdsSyroleSymenus;
import com.wfj.sysmanager.entity.PcdsSyuser2;
import com.wfj.sysmanager.entity.PcdsSyuserSyrole;
import com.wfj.sysmanager.service.RoleMenuService;
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
@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {


    private SimpleGenericDAO<PcdsSyroleSymenus, String> pcdsSyroleSymenusDao;

    @Transactional
    public String addMenu(String roleId, String[] ids) throws SQLException {
        for (String id : ids) {
            PcdsSyroleSymenus ref = new PcdsSyroleSymenus();
            ref.setRoleId(roleId);
            ref.setMenuId(id);
            ref.setId(UuidUtil.getUuid());
            pcdsSyroleSymenusDao.insert(ref);
        }
        return null;
    }

    public String delRoleMenu(String id) throws SQLException {
        pcdsSyroleSymenusDao.deleteById(id);
        return "";
    }


    public String saveOrUpdateRole(PcdsSyroleSymenus roleMenu) throws SQLException {
        if (roleMenu.getId() == null || roleMenu.getId().equals("")) {
            //增加
            pcdsSyroleSymenusDao.insert(roleMenu);
        } else {
            pcdsSyroleSymenusDao.updateById(roleMenu);
        }
        return "";
    }


    public DataTableJson getNotExistMenu(Page page) throws SQLException {
        DataTableJson json = new DataTableJson();
//        List<UserGroup> list = pcdsSyroleSymenusDao.findListByCond(page.getCond());
        List<PcdsSyuser2> list = pcdsSyroleSymenusDao.pageQueryByStatementCond("getNotExistMenu", page);
        Integer count = pcdsSyroleSymenusDao.pageQueryCountByStatementCond("getNotExistMenuCount", page);
        json.setiTotalRecords(count);
        json.setAaData(list);
        return json;
    }

    public DataTableJson selectMenusByRoleid(Page page) throws SQLException {
        DataTableJson json = new DataTableJson();
//        List<UserGroup> list = pcdsSyroleSymenusDao.findListByCond(page.getCond());
        List<RoleUserDto> list = pcdsSyroleSymenusDao.pageQueryByStatementCond("selectMenusByRoleid", page);
        Integer count = pcdsSyroleSymenusDao.pageQueryCountByStatementCond("selectMenusCountByRoleid", page);
        json.setiTotalRecords(count);
        json.setAaData(list);
        return json;
    }

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.pcdsSyroleSymenusDao = new SimpleGenericDAO<PcdsSyroleSymenus, String>(sqlSessionTemplate, PcdsSyroleSymenus.class);
    }

}
