package com.wfj.sysmanager.service;

import com.wfj.exception.util.DataTableJson;
import com.wfj.sysmanager.entity.PcdsSyresources;
import com.wfj.sysmanager.entity.PcdsSyuser2;
import com.wfj.sysmanager.httpModel.EasyuiTreeNode;
import com.wfj.sysmanager.httpModel.Resources;
import com.wfj.util.Page;

import java.sql.SQLException;
import java.util.List;

/**
 * 资源Service
 * 
 * @author 孙宇
 * 
 */
public interface ResourcesServiceI extends BaseServiceI {

    public DataTableJson getResList(Page page) throws SQLException;
    public PcdsSyresources getResById(String id) throws SQLException;
    public String delResById(String id) throws SQLException;
    public String saveOrUpdateRes(PcdsSyresources menu) throws SQLException;


    public List<EasyuiTreeNode> tree(String id);

	public List<Resources> treegrid(String id);

	public Resources add(Resources resources);

	public void del(Resources resources);

	public Resources edit(Resources resources);

}
