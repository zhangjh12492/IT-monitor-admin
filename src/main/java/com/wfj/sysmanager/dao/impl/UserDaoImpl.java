package com.wfj.sysmanager.dao.impl;

import com.wfj.sysmanager.dao.UserDaoI;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl<T> extends BaseDaoImpl<T> implements UserDaoI<T> {

}
