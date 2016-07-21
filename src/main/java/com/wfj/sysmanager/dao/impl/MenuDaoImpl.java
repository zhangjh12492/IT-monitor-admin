package com.wfj.sysmanager.dao.impl;

import com.wfj.sysmanager.dao.MenuDaoI;
import org.springframework.stereotype.Repository;

@Repository("menuDao")
public class MenuDaoImpl<T> extends BaseDaoImpl<T> implements MenuDaoI<T> {

}
