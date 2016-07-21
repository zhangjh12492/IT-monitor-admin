package com.wfj.sysmanager.service.impl;

import com.wfj.exception.util.DataTableJson;
import com.wfj.persist.SimpleGenericDAO;
import com.wfj.sysmanager.dao.BaseDaoI;
import com.wfj.sysmanager.entity.PcdsSymenu2;
import com.wfj.sysmanager.entity.PcdsSyrole;
import com.wfj.sysmanager.httpModel.EasyuiTreeNode;
import com.wfj.sysmanager.httpModel.Role;
import com.wfj.sysmanager.model.*;
import com.wfj.sysmanager.service.RoleServiceI;
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
 * 角色Service实现类
 *
 * @author 孙宇
 *
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl implements RoleServiceI {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    private BaseDaoI<Syrole> roleDao;
    private BaseDaoI<SyroleSyresources> syroleSyresourcesDao;
    private BaseDaoI<SyroleSymenus> syroleSymenusDao;
    private BaseDaoI<SyuserSyrole> syuserSyroleDao;
    private BaseDaoI<Syresources> resourcesDao;
    private BaseDaoI<Symenu> menuDao;

    private SimpleGenericDAO<PcdsSyrole, String> pcdsSyroleDao;

    public BaseDaoI<Symenu> getMenuDao() {
        return menuDao;
    }


    @SuppressWarnings("unchecked")
    @Override
    public DataTableJson getRoleList(Page page) throws SQLException {
        DataTableJson json = new DataTableJson();
        List<PcdsSyrole> list = pcdsSyroleDao.pageQueryByCond(page);
        Integer count = pcdsSyroleDao.pageQueryCountByCond(page).intValue();
        json.setAaData(list);
        json.setiTotalRecords(count);
        json.setiTotalDisplayRecords(count);
        return json;

    }

    @Override
    public PcdsSyrole getRoleById(String id) throws SQLException {
        return pcdsSyroleDao.findById(id);
    }

    @Override
    public String delRoleById(String id) throws SQLException {
        pcdsSyroleDao.deleteById(id);
        return "";
    }

    @Override
    public String saveOrUpdateRole(PcdsSyrole menu) throws SQLException {
        if (menu.getId() == null || menu.getId().equals("")) {
            menu.setId(UuidUtil.getUuid());
            pcdsSyroleDao.insert(menu);
        } else {
            pcdsSyroleDao.updateById(menu);
        }
        return "";
    }











    @Autowired
    public void setMenuDao(BaseDaoI<Symenu> menuDao) {
        this.menuDao = menuDao;
    }

    public BaseDaoI<SyroleSymenus> getSyroleSymenusDao() {
        return syroleSymenusDao;
    }

    @Autowired
    public void setSyroleSymenusDao(BaseDaoI<SyroleSymenus> syroleSymenusDao) {
        this.syroleSymenusDao = syroleSymenusDao;
    }

    public BaseDaoI<Syresources> getResourcesDao() {
        return resourcesDao;
    }

    @Autowired
    public void setResourcesDao(BaseDaoI<Syresources> resourcesDao) {
        this.resourcesDao = resourcesDao;
    }

    public BaseDaoI<SyuserSyrole> getSyuserSyroleDao() {
        return syuserSyroleDao;
    }

    @Autowired
    public void setSyuserSyroleDao(BaseDaoI<SyuserSyrole> syuserSyroleDao) {
        this.syuserSyroleDao = syuserSyroleDao;
    }

    public BaseDaoI<SyroleSyresources> getSyroleSyresourcesDao() {
        return syroleSyresourcesDao;
    }

    @Autowired
    public void setSyroleSyresourcesDao(BaseDaoI<SyroleSyresources> syroleSyresourcesDao) {
        this.syroleSyresourcesDao = syroleSyresourcesDao;
    }

    public BaseDaoI<Syrole> getRoleDao() {
        return roleDao;
    }

    @Autowired
    public void setRoleDao(BaseDaoI<Syrole> roleDao) {
        this.roleDao = roleDao;
    }

    @Cacheable(value = "syproRoleCache", key = "'tree'+#id")
    @Transactional(readOnly = true)
    public List<EasyuiTreeNode> tree(String id) {
        String hql = "from Syrole t where t.syrole is null order by t.seq";
        if (id != null && !id.trim().equals("")) {
            hql = "from Syrole t where t.syrole.id ='" + id + "' order by t.seq";
        }
        List<Syrole> syroleList = roleDao.find(hql);
        List<EasyuiTreeNode> tree = new ArrayList<EasyuiTreeNode>();
        for (Syrole syrole : syroleList) {
            tree.add(tree(syrole, false));
        }
        return tree;
    }

    private EasyuiTreeNode tree(Syrole syrole, boolean recursive) {
        EasyuiTreeNode node = new EasyuiTreeNode();
        node.setId(syrole.getId());
        node.setText(syrole.getText());
        Map<String, Object> attributes = new HashMap<String, Object>();
        node.setAttributes(attributes);
        if (syrole.getSyroles() != null && syrole.getSyroles().size() > 0) {
            node.setState("closed");
            if (recursive) {// 递归查询子节点
                List<Syrole> syroleList = new ArrayList<Syrole>(syrole.getSyroles());
//                Collections.sort(syroleList, new RoleComparator());// 排序
                List<EasyuiTreeNode> children = new ArrayList<EasyuiTreeNode>();
                for (Syrole r : syroleList) {
                    EasyuiTreeNode t = tree(r, true);
                    children.add(t);
                }
                node.setChildren(children);
            }
        }
        return node;
    }

//	@Cacheable(value = "syproRoleCache", key = "'treegrid'+#id")
    @Transactional(readOnly = true)
    public List<Role> treegrid(String id) {
        List<Role> treegrid = new ArrayList<Role>();
        String hql = "from Syrole t where t.syrole is null order by t.seq";
        if (id != null && !id.trim().equals("")) {
            hql = "from Syrole t where t.syrole.id = '" + id + "' order by t.seq";
        }
        List<Syrole> syroleList = roleDao.find(hql);
        for (Syrole syrole : syroleList) {
            Role r = new Role();
            BeanUtils.copyProperties(syrole, r);
            if (syrole.getSyrole() != null) {
                r.setParentId(syrole.getSyrole().getId());
                r.setParentText(syrole.getSyrole().getText());
            }
            if (syrole.getSyroles() != null && syrole.getSyroles().size() > 0) {
                r.setState("closed");
            }
            if (syrole.getSyroleSyresourceses() != null && syrole.getSyroleSyresourceses().size() > 0) {
                String resourcesTextList = "";
                String resourcesIdList = "";
                boolean b = false;
                Set<SyroleSyresources> syroleSyresourcesSet = syrole.getSyroleSyresourceses();
                for (SyroleSyresources syroleSyresources : syroleSyresourcesSet) {
                    Syresources syresources = syroleSyresources.getSyresources();
                    //5月7日修改只把叶子节点加入
                    //开始
                    String hhqq = "from Syresources t where t.syresources.id='"+syresources.getId()+"'";
                    List<Syresources> rlist=resourcesDao.find(hhqq);
                    if(rlist.size()>0){
                        continue;
                    }
                    //结束
                    if (!b) {
                        b = true;
                    } else {
                        resourcesTextList += ",";
                        resourcesIdList += ",";
                    }
                    resourcesTextList += syresources.getText();
                    resourcesIdList += syresources.getId();
                }
                r.setResourcesId(resourcesIdList);
                r.setResourcesText(resourcesTextList);
            }
            if (syrole.getSyroleSymenus() != null && syrole.getSyroleSymenus().size() > 0) {
                String menusTextList = "";
                String menusIdList = "";
                boolean b = false;
                Set<SyroleSymenus> syroleSyrolesSymenusSet = syrole.getSyroleSymenus();
                for (SyroleSymenus syroleSymenus : syroleSyrolesSymenusSet) {
                    Symenu symenu = syroleSymenus.getSymenu();
                    //5月7日修改只把叶子节点加入
                    //开始
                    String hhqq="from Symenu t where t.symenu.id='"+symenu.getId()+"'";
                    List<Symenu> rlist=menuDao.find(hhqq);
                    if(rlist.size()>0){
                        continue;
                    }
                    //结束
                    if (!b) {
                        b = true;
                    } else {
                        menusTextList += ",";
                        menusIdList += ",";
                    }
                    menusTextList += symenu.getText();
                    menusIdList += symenu.getId();
                }
                r.setMenusId(menusIdList);
                r.setMenusText(menusTextList);
            }
            treegrid.add(r);
        }
        return treegrid;
    }

    @CacheEvict(value = "syproRoleCache", allEntries = true)
    public Role add(Role role) {
        Syrole syrole = new Syrole();
        BeanUtils.copyProperties(role, syrole);
        if (role.getParentId() != null && !role.getParentId().trim().equals("")) {
            syrole.setSyrole(roleDao.load(Syrole.class, role.getParentId()));
        }
        roleDao.save(syrole);
        return role;
    }

    @CacheEvict(value = "syproRoleCache", allEntries = true)
    public void del(Role role) {
        Syrole syrole = roleDao.get(Syrole.class, role.getId());
        if (role != null) {
            recursiveDelete(syrole);
        }
    }

    private void recursiveDelete(Syrole syrole) {
        if (syrole.getSyroles() != null && syrole.getSyroles().size() > 0) {
            Set<Syrole> syroleSet = syrole.getSyroles();
            for (Syrole t : syroleSet) {
                recursiveDelete(t);
            }
        }

        List<SyroleSyresources> syroleSyresourcesList = syroleSyresourcesDao.find("from SyroleSyresources t where t.syrole=?", syrole);
        if (syroleSyresourcesList != null && syroleSyresourcesList.size() > 0) {
            for (SyroleSyresources syroleSyresources : syroleSyresourcesList) {
                syroleSyresourcesDao.delete(syroleSyresources);
            }
        }

        List<SyroleSymenus> syroleSymenusList = syroleSymenusDao.find("from SyroleSymenus t where t.syrole=?", syrole);
        if (syroleSymenusList != null && syroleSymenusList.size() > 0) {
            for (SyroleSymenus syroleSymenus : syroleSymenusList) {
                syroleSymenusDao.delete(syroleSymenus);
            }
        }

        List<SyuserSyrole> syuserSyroleList = syuserSyroleDao.find("from SyuserSyrole t where t.syrole=?", syrole);
        if (syuserSyroleList != null && syuserSyroleList.size() > 0) {
            for (SyuserSyrole syuserSyrole : syuserSyroleList) {
                syuserSyroleDao.delete(syuserSyrole);
            }
        }

        roleDao.delete(syrole);
    }

    @CacheEvict(value = "syproRoleCache", allEntries = true)
    public Role edit(Role role) {
        Syrole syrole = roleDao.get(Syrole.class, role.getId());
        if (syrole != null) {
            BeanUtils.copyProperties(role, syrole);
            if (!syrole.getId().equals("0")) {// 跟节点不可以修改上级节点
                syrole.setSyrole(roleDao.get(Syrole.class, role.getParentId()));
            }

            List<SyroleSyresources> syroleSyresourcesList = syroleSyresourcesDao.find("from SyroleSyresources t where t.syrole=?", syrole);
            for (SyroleSyresources syroleSyresources : syroleSyresourcesList) {
                syroleSyresourcesDao.delete(syroleSyresources);
            }

            if (role.getResourcesId() != null && !role.getResourcesId().equals("")) {// 保存角色和资源的关系
                String[] resourceIds = role.getResourcesId().split(",");
                //2013-05-07日陈勇修改，当保存的菜单为叶子级时,判断其上级是否存在，不存在也加入进来
                List<String> rList=Arrays.asList(resourceIds);
                for (String resourceId : resourceIds) {
                    SyroleSyresources syroleSyresources = new SyroleSyresources();// 关系
                    Syresources syresources = resourcesDao.get(Syresources.class, resourceId);// 资源
                    syroleSyresources.setId(UUID.randomUUID().toString());
                    syroleSyresources.setSyrole(syrole);
                    syroleSyresources.setSyresources(syresources);
                    syroleSyresourcesDao.save(syroleSyresources);
                    if(null!=syresources.getSyresources()){
                        if(!rList.contains(syresources.getSyresources().getId())){
                           //list不存在其父ID
                            SyroleSyresources ss=new SyroleSyresources();
                            ss.setId(UUID.randomUUID().toString());
                            ss.setSyrole(syrole);
                            ss.setSyresources(syresources.getSyresources());
                            syroleSyresourcesDao.save(ss);

                        }
                    }
                }
            }

            List<SyroleSymenus> syroleSymenusList = syroleSymenusDao.find("from SyroleSymenus t where t.syrole=?", syrole);
            for (SyroleSymenus syroleSymenus : syroleSymenusList) {
                syroleSymenusDao.delete(syroleSymenus);
            }

            if (role.getMenusId() != null && !role.getMenusId().equals("")) {// 保存角色和菜单的关系
                String[] menusId = role.getMenusId().split(",");
                //2013-04-24日陈勇修改，当保存的菜单为叶子级时，判断其上级是否存在，不存在也加入进来，解决不能单独分配菜单的问题
                List<String> mList=Arrays.asList(menusId);//将菜单ID数组转为List
                for (String menuId : menusId) {
                    SyroleSymenus syroleSymenus = new SyroleSymenus();// 关系
                    Symenu symenu = menuDao.get(Symenu.class, menuId);// 菜单
                    syroleSymenus.setId(UUID.randomUUID().toString());
                    syroleSymenus.setSyrole(syrole);
                    syroleSymenus.setSymenu(symenu);
                    syroleSymenusDao.save(syroleSymenus);
                    if(null!=symenu.getSymenu()){
                        if(!mList.contains(symenu.getSymenu().getId())){
                            //list不存在其父ID
                            SyroleSymenus sys = new SyroleSymenus();// 关系
                            Symenu smenu=menuDao.get(Symenu.class, symenu.getSymenu().getId());
                            sys.setId(UUID.randomUUID().toString());
                            sys.setSyrole(syrole);
                            sys.setSymenu(symenu.getSymenu());
                            syroleSymenusDao.save(sys);
                            if(null!=smenu.getSymenu()){
                                 if(!mList.contains(smenu.getSymenu().getId())){
                                	 SyroleSymenus ss=new SyroleSymenus();
                                	 ss.setId(UUID.randomUUID().toString());
                                	 ss.setSyrole(syrole);
                                	 ss.setSymenu(smenu.getSymenu());
                                	 syroleSymenusDao.save(ss);
                                 }
                            }
                        }
                    }
                }
            }

        }
        return role;
    }
    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.pcdsSyroleDao = new SimpleGenericDAO<PcdsSyrole, String>(sqlSessionTemplate, PcdsSyrole.class);
    }


}
