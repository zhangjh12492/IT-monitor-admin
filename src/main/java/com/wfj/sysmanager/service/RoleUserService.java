package com.wfj.sysmanager.service;

import com.wfj.exception.util.DataTableJson;
import com.wfj.util.Page;

import java.sql.SQLException;

/**
 * Created by MaYong on 2015/8/17.
 */
public interface RoleUserService {

    public String addUser(String roleId, String[] ids) throws SQLException;

    public String delRoleUser(String id) throws SQLException;


    public DataTableJson selectUsersByGroupid(Page page) throws SQLException;

    public DataTableJson getNotExistUser(Page page) throws SQLException;
}
