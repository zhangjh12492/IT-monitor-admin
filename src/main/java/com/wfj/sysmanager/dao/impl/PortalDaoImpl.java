package com.wfj.sysmanager.dao.impl;

import com.wfj.sysmanager.dao.PortalDaoI;
import org.springframework.stereotype.Repository;

@Repository("portalDao")
public class PortalDaoImpl<T> extends BaseDaoImpl<T> implements PortalDaoI<T> {

}
