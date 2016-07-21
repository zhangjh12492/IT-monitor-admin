package com.wfj.exception.dal.entity;

/**
 * Created with IntelliJ IDEA.
 * User: MaYong
 * Date: 2015-08-19
 * Time: 09:59:40
 * To change this template use File | Settings | File Templates.
 */

public class SysInfoUserGroupRef {
    private Integer sysInfoId;  //
    private Integer userGroupId;  //
    private Integer id;  //

    public Integer getSysInfoId() {
        return sysInfoId;
    }

    public void setSysInfoId(Integer sysInfoId) {
        this.sysInfoId = sysInfoId;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}