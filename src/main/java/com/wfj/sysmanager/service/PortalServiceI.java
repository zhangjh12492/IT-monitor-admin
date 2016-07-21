package com.wfj.sysmanager.service;

import com.wfj.sysmanager.httpModel.EasyuiDataGrid;
import com.wfj.sysmanager.httpModel.EasyuiDataGridJson;
import com.wfj.sysmanager.httpModel.Portal;

/**
 * portal Service
 * 
 * @author 孙宇
 * 
 */
public interface PortalServiceI extends BaseServiceI {

	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Portal portal);

	public void del(String ids);

	public void edit(Portal portal);

	public void add(Portal portal);

}
