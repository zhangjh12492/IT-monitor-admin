package com.wfj.sysmanager.dao.impl;

import com.wfj.sysmanager.dao.ResourcesDaoI;
import org.springframework.stereotype.Repository;

@Repository("resourcesDao")
public class ResourcesDaoImpl<T> extends BaseDaoImpl<T> implements ResourcesDaoI<T> {

}
