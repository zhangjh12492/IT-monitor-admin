package com.wfj.sysmanager.service.impl;

import com.wfj.exception.util.DataTableJson;
import com.wfj.persist.SimpleGenericDAO;
import com.wfj.sysmanager.dto.RoleUserDto;
import com.wfj.sysmanager.entity.PcdsSyroleSymenus;
import com.wfj.sysmanager.entity.PcdsSyroleSyresources;
import com.wfj.sysmanager.entity.PcdsSyuser2;
import com.wfj.sysmanager.service.RoleResService;
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
@Service("roleResService")
public class RoleResServiceImpl implements RoleResService {


    private SimpleGenericDAO<PcdsSyroleSyresources, String> pcdsSyroleSyresDao;

    @Transactional
    public String addRes(String roleId, String[] ids) throws SQLException {
        for (String id : ids) {
            PcdsSyroleSyresources ref = new PcdsSyroleSyresources();
            ref.setRoleId(roleId);
            ref.setResourcesId(id);
            ref.setId(UuidUtil.getUuid());
            pcdsSyroleSyresDao.insert(ref);
        }
        return null;
    }

    public String delRoleRes(String id) throws SQLException {
        pcdsSyroleSyresDao.deleteById(id);
        return "";
    }



    public DataTableJson getNotExistRes(Page page) throws SQLException {
        DataTableJson json = new DataTableJson();
//        List<UserGroup> list = pcdsSyroleSyresDao.findListByCond(page.getCond());
        List<PcdsSyuser2> list = pcdsSyroleSyresDao.pageQueryByStatementCond("getNotExistRes", page);
        Integer count = pcdsSyroleSyresDao.pageQueryCountByStatementCond("getNotExistResCount", page);
        json.setiTotalRecords(count);
        json.setAaData(list);
        return json;
    }

    public DataTableJson selectResByRoleid(Page page) throws SQLException {
        DataTableJson json = new DataTableJson();
//        List<UserGroup> list = pcdsSyroleSyresDao.findListByCond(page.getCond());
        List<RoleUserDto> list = pcdsSyroleSyresDao.pageQueryByStatementCond("selectResByRoleid", page);
        Integer count = pcdsSyroleSyresDao.pageQueryCountByStatementCond("selectResCountByRoleid", page);
        json.setiTotalRecords(count);
        json.setAaData(list);
        return json;
    }

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.pcdsSyroleSyresDao = new SimpleGenericDAO<PcdsSyroleSyresources, String>(sqlSessionTemplate, PcdsSyroleSyresources.class);
    }

}
