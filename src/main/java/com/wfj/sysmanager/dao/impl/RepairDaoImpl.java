package com.wfj.sysmanager.dao.impl;

import com.wfj.sysmanager.dao.RepairDaoI;
import org.springframework.stereotype.Repository;

@Repository("repairDao")
public class RepairDaoImpl<T> extends BaseDaoImpl<T> implements RepairDaoI<T> {

}
