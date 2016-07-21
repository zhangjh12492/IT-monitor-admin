package com.wfj.exception.dal.service;

import com.wfj.exception.dal.entity.UserGroup;
import com.wfj.exception.util.DataTableJson;
import com.wfj.util.Page;

import java.sql.SQLException;

/**
 * Created by MaYong on 2015/8/17.
 */
public interface SysGroupService {

    public String addGroup(Integer sysInfoId, Integer[] ids) throws SQLException;

    public String delUserGroup(Integer id) throws SQLException;

    public DataTableJson getSystemList(Page page) throws SQLException;

    public String saveOrUpdateGroup(UserGroup userGroup) throws SQLException;

    public DataTableJson selectGroupBySysid(Page page) throws SQLException;

    public DataTableJson getNotExistGroup(Page page) throws SQLException;
}
