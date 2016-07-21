package com.wfj.sysmanager.dao.impl;

import com.wfj.sysmanager.dao.AuthDaoI;
import org.springframework.stereotype.Repository;

@Repository("authDao")
public class AuthDaoImpl<T> extends BaseDaoImpl<T> implements AuthDaoI<T> {

}
