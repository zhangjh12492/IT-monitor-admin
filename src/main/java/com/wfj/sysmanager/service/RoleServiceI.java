package com.wfj.sysmanager.service;

import com.wfj.exception.util.DataTableJson;
import com.wfj.sysmanager.entity.PcdsSyrole;
import com.wfj.sysmanager.httpModel.EasyuiTreeNode;
import com.wfj.sysmanager.httpModel.Role;
import com.wfj.util.Page;

import java.sql.SQLException;
import java.util.List;

/**
 * 角色Service
 *
 * @author 孙宇
 */
public interface RoleServiceI extends BaseServiceI {

    public DataTableJson getRoleList(Page page) throws SQLException;
    public PcdsSyrole getRoleById(String id) throws SQLException;
    public String delRoleById(String id) throws SQLException;
    public String saveOrUpdateRole(PcdsSyrole menu) throws SQLException;

    public List<EasyuiTreeNode> tree(String id);

    public List<Role> treegrid(String id);

    public Role add(Role role);

    public void del(Role role);

    public Role edit(Role role);

}
