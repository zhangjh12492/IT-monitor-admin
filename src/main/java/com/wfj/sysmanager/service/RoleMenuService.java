package com.wfj.sysmanager.service;

import com.wfj.exception.util.DataTableJson;
import com.wfj.util.Page;

import java.sql.SQLException;

/**
 * Created by MaYong on 2015/8/17.
 */
public interface RoleMenuService {

    public String addMenu(String roleId, String[] ids) throws SQLException;

    public String delRoleMenu(String id) throws SQLException;


    public DataTableJson selectMenusByRoleid(Page page) throws SQLException;

    public DataTableJson getNotExistMenu(Page page) throws SQLException;
}
