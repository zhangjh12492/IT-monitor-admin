package com.wfj.exception.dal.service;

import com.wfj.exception.dal.entity.PcdsSymenu;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by MaYong on 2015/8/21.
 */
public interface MenuService {

    public List<PcdsSymenu> getMenuList(Integer userId) throws SQLException;
}
