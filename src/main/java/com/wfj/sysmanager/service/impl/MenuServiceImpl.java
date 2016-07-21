package com.wfj.sysmanager.service.impl;

import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.MsgReturnDto;
import com.wfj.persist.SimpleGenericDAO;
import com.wfj.sysmanager.dao.BaseDaoI;
import com.wfj.sysmanager.entity.PcdsSymenu2;
import com.wfj.sysmanager.httpModel.EasyuiTreeNode;
import com.wfj.sysmanager.httpModel.Menu;
import com.wfj.sysmanager.model.*;
import com.wfj.sysmanager.service.MenuServiceI;
import com.wfj.sysmanager.util.UuidUtil;
import com.wfj.util.Page;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

/**
 * 菜单Service实现类
 *
 * @author 孙宇
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl implements MenuServiceI {

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    private BaseDaoI<Symenu> menuDao;

    private BaseDaoI<Syuser> userDao;

    private BaseDaoI<SyroleSymenus> syroleSymenusDao;

    public BaseDaoI<Syuser> getUserDao() {
        return userDao;
    }

    private SimpleGenericDAO<PcdsSymenu2, String> pcdsSymenuDao;


    @SuppressWarnings("unchecked")
    @Override
    public DataTableJson getMenuList(Page page) throws SQLException {
        DataTableJson json = new DataTableJson();
        List<PcdsSymenu2> list = pcdsSymenuDao.pageQueryByCond(page);
        Integer count = pcdsSymenuDao.pageQueryCountByCond(page).intValue();
        json.setAaData(list);
        json.setiTotalRecords(count);
        json.setiTotalDisplayRecords(count);
        return json;

    }

    @Override
    public PcdsSymenu2 getMenuById(String id) throws SQLException {
        return pcdsSymenuDao.findById(id);
    }

    @Override
    public String delMenuById(String id) throws SQLException {
        pcdsSymenuDao.deleteById(id);
        return "";
    }

    @Override
    public String saveOrUpdateMenu(PcdsSymenu2 menu) throws SQLException {
        if (menu.getId() == null || menu.getId().equals("")) {
            menu.setId(UuidUtil.getUuid());
            pcdsSymenuDao.insert(menu);
        } else {
            pcdsSymenuDao.updateById(menu);
        }
        return "";
    }


    @Autowired
    public void setSyroleSysmenusDao(BaseDaoI<SyroleSymenus> syroleSymenusDao) {
        this.syroleSymenusDao = syroleSymenusDao;
    }

    @Autowired
    public void setUserDao(BaseDaoI<Syuser> userDao) {
        this.userDao = userDao;
    }

    public BaseDaoI<Symenu> getMenuDao() {
        return menuDao;
    }

    @Autowired
    public void setMenuDao(BaseDaoI<Symenu> menuDao) {
        this.menuDao = menuDao;
    }

    @Cacheable(value = "syproMenuCache", key = "'tree'+#id")
    @Transactional(readOnly = true)
    public List<EasyuiTreeNode> tree(String id) {
        String hql = "from Symenu t where t.symenu is null order by t.seq";
        if (id != null && !id.equals("")) {
            hql = "from Symenu t where t.symenu.id ='" + id + "' order by t.seq";
        }
        List<Symenu> symenus = menuDao.find(hql);
        List<EasyuiTreeNode> tree = new ArrayList<EasyuiTreeNode>();
        for (Symenu symenu : symenus) {
            tree.add(tree(symenu, false));
        }
        return tree;
    }

    private EasyuiTreeNode tree(Symenu symenu, boolean recursive) {
        EasyuiTreeNode node = new EasyuiTreeNode();
        node.setId(symenu.getId());
        node.setText(symenu.getText());
        node.setIconCls(symenu.getIconcls());
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("src", symenu.getSrc());
        node.setAttributes(attributes);
        if (symenu.getSymenus() != null && symenu.getSymenus().size() > 0) {
            node.setState("closed");
            if (recursive) {// 递归查询子节点
                List<Symenu> symenuList = new ArrayList<Symenu>(symenu.getSymenus());
//                Collections.sort(symenuList, new MenuComparator());// 排序
                List<EasyuiTreeNode> children = new ArrayList<EasyuiTreeNode>();
                for (Symenu m : symenuList) {
                    EasyuiTreeNode t = tree(m, true);
                    children.add(t);
                }
                node.setChildren(children);
            }
        }
        node.setSrc(symenu.getSrc());
        return node;
    }

    //    @Cacheable(value = "syproMenuCache", key = "'tree'+#id")
    @Transactional(readOnly = true)
    public List<EasyuiTreeNode> tree(String id, Integer userId) {
        String hql = "from Symenu t where t.symenu is null order by t.seq";
        if (id != null && !id.trim().equals("")) {
            hql = "from Symenu t where t.symenu.id ='" + id + "' order by t.seq";
        }
        List<Symenu> symenus = menuDao.find(hql);
        List<String> authMenuIdList = getAuthMenu(userId);//允许访问的菜单IdList
        List<EasyuiTreeNode> tree = new ArrayList<EasyuiTreeNode>();
        if (authMenuIdList != null && authMenuIdList.size() > 0) {
            for (Symenu symenu : symenus) {
                if (authMenuIdList.contains(symenu.getId()) || symenu.getId().equals("0")) {
                    tree.add(tree(symenu, false, authMenuIdList));
                }
            }
        }
        return tree;
    }

    private EasyuiTreeNode tree(Symenu symenu, boolean recursive, List<String> authMenuIdList) {
        EasyuiTreeNode node = new EasyuiTreeNode();
        node.setId(symenu.getId());
        node.setText(symenu.getText());
        node.setIconCls(symenu.getIconcls());
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("src", symenu.getSrc());
        node.setAttributes(attributes);
        if (symenu.getSymenus() != null && symenu.getSymenus().size() > 0) {
            node.setState("closed");
            if (recursive) {// 递归查询子节点
                List<Symenu> symenuList = new ArrayList<Symenu>(symenu.getSymenus());
                //排序会报错
//                Collections.sort(symenuList, new MenuComparator());// 排序
                List<EasyuiTreeNode> children = new ArrayList<EasyuiTreeNode>();
                for (Symenu m : symenuList) {
                    if (authMenuIdList.contains(m.getId())) {
                        EasyuiTreeNode t = tree(m, true, authMenuIdList);
                        children.add(t);
                    }
                }
                node.setChildren(children);
            }
        }
        return node;
    }

    //@Cacheable(value = "syproMenuCache", key = "'treegrid'+#id")
    @Transactional(readOnly = true)
    public List<Menu> treegrid(String id) {
        List<Menu> treegrid = new ArrayList<Menu>();
        String hql = "from Symenu t where t.symenu is null order by t.seq";
        if (id != null && !id.trim().equals("")) {
            hql = "from Symenu t where t.symenu.id = '" + id + "' order by t.seq";
        }
        List<Symenu> symenus = menuDao.find(hql);
        for (Symenu symenu : symenus) {
            Menu m = new Menu();
            BeanUtils.copyProperties(symenu, m);
            if (symenu.getSymenu() != null) {
                m.setParentId(symenu.getSymenu().getId());
                m.setParentText(symenu.getSymenu().getText());
            }
            m.setIconCls(symenu.getIconcls());
            if (symenu.getSymenus() != null && symenu.getSymenus().size() > 0) {
                m.setState("closed");
            }
            treegrid.add(m);
        }
        return treegrid;
    }

    @Override
    public List<Menu> treegrid(String id, Integer userId) {
        List<Menu> treegrid = new ArrayList<Menu>();
        String hql = "from Symenu t where t.symenu is null order by t.seq";
        if (id != null && !id.trim().equals("")) {
            hql = "from Symenu t where t.symenu.id = '" + id + "' order by t.seq";
        }
        List<Symenu> symenus = menuDao.find(hql);
        if (userId != null) {
            List<String> authMenuIdList = getAuthMenu(userId);//允许访问的菜单IdList
            for (Symenu symenu : symenus) {
                if (authMenuIdList.contains(symenu.getId())) {
                    Menu m = new Menu();
                    BeanUtils.copyProperties(symenu, m);
                    if (symenu.getSymenu() != null) {
                        m.setParentId(symenu.getSymenu().getId());
                        m.setParentText(symenu.getSymenu().getText());
                    }
                    m.setIconCls(symenu.getIconcls());
                    if (symenu.getSymenus() != null && symenu.getSymenus().size() > 0) {
                        m.setState("closed");
                    }
                    treegrid.add(m);
                }
            }
        }
        return treegrid;
    }

    @Transactional(readOnly = true)
    private List<String> getAuthMenu(Integer userId) {
        List<String> symenuList = new ArrayList<String>();
        Syuser syuser = userDao.get(Syuser.class, userId);
        Set<SyuserSyrole> syuserSyroles = syuser.getSyuserSyroles();// 用户和角色关系
        for (SyuserSyrole syuserSyrole : syuserSyroles) {
            Syrole syrole = syuserSyrole.getSyrole();
            Set<SyroleSymenus> syroleSymenusSet = syrole.getSyroleSymenus();// 角色和菜单关系
            for (SyroleSymenus syroleSymenus : syroleSymenusSet) {
                Symenu symenu = syroleSymenus.getSymenu();
                symenuList.add(symenu.getId());
            }
        }
        return symenuList;
    }

    @CacheEvict(value = "syproMenuCache", allEntries = true)
    public Menu add(Menu menu) {
        Symenu symenu = new Symenu();
        BeanUtils.copyProperties(menu, symenu);
        symenu.setIconcls(menu.getIconCls());
        if (menu.getParentId() != null && !menu.getParentId().trim().equals("")) {
            symenu.setSymenu(menuDao.load(Symenu.class, menu.getParentId()));
        }
        menuDao.save(symenu);
        return menu;
    }

    @CacheEvict(value = "syproMenuCache", allEntries = true)
    public void del(Menu menu) {
        Symenu symenu = menuDao.get(Symenu.class, menu.getId());
        if (symenu != null) {
            recursiveDelete(symenu);
        }
    }

    private void recursiveDelete(Symenu symenu) {
        if (symenu.getSymenus() != null && symenu.getSymenus().size() > 0) {
            Set<Symenu> symenus = symenu.getSymenus();
            for (Symenu t : symenus) {
                recursiveDelete(t);
            }
        }

        List<SyroleSymenus> syroleSymenusList = syroleSymenusDao.find("from SyroleSymenus t where t.symenu=?", symenu);
        if (syroleSymenusList != null) {// 删除资源前,需要现将角色菜单关系表中的相关记录删除
            for (SyroleSymenus syroleSymenus : syroleSymenusList) {
                syroleSymenusDao.delete(syroleSymenus);
            }
        }

        menuDao.delete(symenu);
    }

    @CacheEvict(value = "syproMenuCache", allEntries = true)
    public Menu edit(Menu menu) {
        Symenu symenu = menuDao.get(Symenu.class, menu.getId());
        if (symenu != null) {
            BeanUtils.copyProperties(menu, symenu);
            symenu.setIconcls(menu.getIconCls());
            if (!symenu.getId().equals("0")) {// 根节点不可以修改上级节点
                symenu.setSymenu(menuDao.get(Symenu.class, menu.getParentId()));
            }
        }
        return menu;
    }

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.pcdsSymenuDao = new SimpleGenericDAO<PcdsSymenu2, String>(sqlSessionTemplate, PcdsSymenu2.class);
    }

}
