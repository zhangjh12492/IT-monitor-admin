package com.wfj.sysmanager.service;

import com.wfj.exception.util.DataTableJson;
import com.wfj.util.Page;

import java.sql.SQLException;

/**
 * Created by MaYong on 2015/8/17.
 */
public interface RoleResService {

    public String addRes(String roleId, String[] ids) throws SQLException;

    public String delRoleRes(String id) throws SQLException;

    public DataTableJson selectResByRoleid(Page page) throws SQLException;

    public DataTableJson getNotExistRes(Page page) throws SQLException;
}
