package com.wfj.sysmanager.service;

import com.wfj.exception.util.DataTableJson;
import com.wfj.sysmanager.entity.PcdsSyuser2;
import com.wfj.sysmanager.httpModel.EasyuiDataGrid;
import com.wfj.sysmanager.httpModel.EasyuiDataGridJson;
import com.wfj.sysmanager.httpModel.User;
import com.wfj.util.Page;

import java.sql.SQLException;
import java.util.List;

/**
 * 用户Service
 * 
 * @author 孙宇
 * 
 */
public interface UserServiceI extends BaseServiceI {

    public DataTableJson getUserList(Page page) throws SQLException;
    public PcdsSyuser2 getUserById(Integer id) throws SQLException;
    public String delUserById(Integer id) throws SQLException;
    public String saveOrUpdateUser(PcdsSyuser2 menu) throws SQLException;


    /**
	 * 用户注册
	 * 
	 * @param user
	 *            用户信息
	 * @return User
	 */
	public User reg(User user);

	/**
	 * 用户登录
	 * 
	 * @param user
	 *            用户信息
	 * @return User
	 */
	public User login(User user);

	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, User user);

	public List<User> combobox(String q);

	public User add(User user);

	public User edit(User user);

	public void del(String ids);

	public void editUsersRole(String userIds, String roleId);

	public User getUserInfo(User user);

	public User editUserInfo(User user);

}
