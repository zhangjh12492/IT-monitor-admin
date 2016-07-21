package com.wfj.exception.dal.service;

import com.wfj.exception.dal.entity.UserGroup;
import com.wfj.exception.util.DataTableJson;
import com.wfj.util.Page;

import java.sql.SQLException;

/**
 * Created by MaYong on 2015/8/17.
 */
public interface BusiGroupService {

    public String addGroup(Integer groupId, Integer[] ids) throws SQLException;

    public String delUserGroup(Integer id) throws SQLException;

    public DataTableJson getBusiGroupList(Page page) throws SQLException;

    public String saveOrUpdateGroup(UserGroup userGroup) throws SQLException;

    public DataTableJson selectUsersByGroupid(Page page) throws SQLException;

    public DataTableJson getNotExistUser(Page page) throws SQLException;
}
