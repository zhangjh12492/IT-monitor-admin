package com.wfj.sysmanager.service.impl;

import com.wfj.sysmanager.dao.BaseDaoI;
import com.wfj.sysmanager.model.*;
import com.wfj.sysmanager.service.AuthServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * 权限Service
 *
 * @author 孙宇
 */
@Service("authService")
public class AuthServiceImpl extends BaseServiceImpl implements AuthServiceI {

    private BaseDaoI<Syresources> resourcesDao;
    private BaseDaoI<Syuser> userDao;

    public BaseDaoI<Syuser> getUserDao() {
        return userDao;
    }

    @Autowired
    public void setUserDao(BaseDaoI<Syuser> userDao) {
        this.userDao = userDao;
    }

    public BaseDaoI<Syresources> getResourcesDao() {
        return resourcesDao;
    }

    @Autowired
    public void setResourcesDao(BaseDaoI<Syresources> resourcesDao) {
        this.resourcesDao = resourcesDao;
    }

    //    @Cacheable(value = "syproAuthCache", key = "'offResourcesList'")
    @Transactional(readOnly = true)
    public List<Syresources> offResourcesList() {
        return resourcesDao.find("from Syresources t where t.onoff != '0'");
    }

    //	@Cacheable(value = "syproAuthCache", key = "'getSyresourcesByRequestPath'+#requestPath")
    @Transactional(readOnly = true)
    public Syresources getSyresourcesByRequestPath(String requestPath) {
        return resourcesDao.get("from Syresources t where t.src=?", requestPath);
    }

    //	@Cacheable(value = "syproAuthCache", key = "'checkAuth'+#userId+#requestPath")
    @Transactional(readOnly = true)
    public boolean checkAuth(Integer userId, String requestPath) {
        Syuser syuser = userDao.get(Syuser.class, userId);
        Set<SyuserSyrole> syuserSyroles = syuser.getSyuserSyroles();// 用户和角色关系
        for (SyuserSyrole syuserSyrole : syuserSyroles) {
            Syrole syrole = syuserSyrole.getSyrole();
            Set<SyroleSyresources> syroleSyreources = syrole.getSyroleSyresourceses();// 角色和资源关系
            for (SyroleSyresources syroleSyreource : syroleSyreources) {
                Syresources syresources = syroleSyreource.getSyresources();
                if (recursiveCheck(syresources, requestPath)) {
                    //System.out.println(syresources.getSrc());
                    //System.out.println(requestPath);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean recursiveCheck(Syresources syresources, String requestPath) {
        if (syresources.getSrc() != null && requestPath.equals(syresources.getSrc().trim())) {
            //System.out.println(syresources.getSrc());
            //System.out.println(requestPath);
            return true;
        } else {
            Set<Syresources> syresourceses = syresources.getSyresourceses();
            for (Syresources _syresources : syresourceses) {
                if (recursiveCheck(_syresources, requestPath)) {
                    return true;
                }
            }
        }
        return false;
    }

}
