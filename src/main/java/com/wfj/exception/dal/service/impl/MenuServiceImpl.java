package com.wfj.exception.dal.service.impl;

import com.wfj.exception.dal.cond.UserMenuCond;
import com.wfj.exception.dal.entity.PcdsSymenu;
import com.wfj.exception.dal.service.MenuService;
import com.wfj.persist.SimpleGenericDAO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by MaYong on 2015/8/21.
 */
@Service("menuService_my")
public class MenuServiceImpl implements MenuService {

    private SimpleGenericDAO<PcdsSymenu, Integer> pcdsSymenuDao;


    public List<PcdsSymenu> getMenuList(Integer userId) throws SQLException {
        UserMenuCond mainCond = new UserMenuCond();
        mainCond.setPid("0");
        mainCond.setUserId(userId);
        List<PcdsSymenu> mainMenuList = pcdsSymenuDao.findListByStatementCond("selectMenuByUserid",mainCond);
        for (PcdsSymenu menu: mainMenuList){
            UserMenuCond subCond = new UserMenuCond();
            subCond.setUserId(userId);
            subCond.setPid(menu.getId());
            List<PcdsSymenu> subList = pcdsSymenuDao.findListByStatementCond("selectMenuByUserid",subCond);
            menu.setChildren(subList);
        }
        return mainMenuList;
    }

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.pcdsSymenuDao = new SimpleGenericDAO<PcdsSymenu, Integer>(sqlSessionTemplate, PcdsSymenu.class);
    }

}
