package com.wfj.sysmanager.service;

import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.MsgReturnDto;
import com.wfj.sysmanager.entity.PcdsSymenu2;
import com.wfj.sysmanager.httpModel.EasyuiTreeNode;
import com.wfj.sysmanager.httpModel.Menu;
import com.wfj.util.Page;

import java.sql.SQLException;
import java.util.List;

/**
 * 菜单Service
 * 
 * @author 孙宇
 * 
 */
public interface MenuServiceI extends BaseServiceI {

    /**
     * 获得菜单列表
     * @param page
     * @return
     * @throws SQLException
     */
    public DataTableJson getMenuList(Page page) throws SQLException;

    public PcdsSymenu2 getMenuById(String id) throws SQLException;

    public String delMenuById(String id) throws SQLException;

    public String saveOrUpdateMenu(PcdsSymenu2 menu) throws SQLException;

	/**
	 * 获得菜单树
	 * 
	 * @param id
	 * @return
	 */
	public List<EasyuiTreeNode> tree(String id);

	public List<EasyuiTreeNode> tree(String id, Integer userId);

	public List<Menu> treegrid(String id);

	public List<Menu> treegrid(String id, Integer userId);

	public Menu add(Menu menu);

	public void del(Menu menu);

	public Menu edit(Menu menu);

}
