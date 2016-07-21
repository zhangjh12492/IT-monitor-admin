package com.wfj.sysmanager.dao.impl;

import com.wfj.sysmanager.dao.RoleDaoI;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDaoImpl<T> extends BaseDaoImpl<T> implements RoleDaoI<T> {

}
