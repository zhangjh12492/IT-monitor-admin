package com.wfj.exception.dal.entity;

/**
 * Created with IntelliJ IDEA.
 * User: MaYong
 * Date: 2015-08-19
 * Time: 09:59:40
 * To change this template use File | Settings | File Templates.
 */

public class BusiInfoUserGroupRef {
    private Integer busiInfoId;  //
    private Integer userGroupId;  //
    private Integer id;  //

    public Integer getBusiInfoId() {
        return busiInfoId;
    }

    public void setBusiInfoId(Integer busiInfoId) {
        this.busiInfoId = busiInfoId;
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