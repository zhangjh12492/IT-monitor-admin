package com.wfj.exception.dal.entity;


/**
 * Created with IntelliJ IDEA.
 * User: MaYong
 * Date: 2015-08-19
 * Time: 09:59:40
 * To change this template use File | Settings | File Templates.
 */

public class UserGroupUserInfoRef {
    private Integer userInfoId;  //
    private Integer userGroupId;  //
    private Integer id;  //
    private Integer userType;

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
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