package com.wfj.exception.dal.entity;

/**
 * Created with IntelliJ IDEA.
 * User: MaYong
 * Date: 2015-08-17
 * Time: 18:05:22
 * To change this template use File | Settings | File Templates.
 */

public class UserGroup {
    private String groupDesc;  //
    private Integer id;  //
    private Integer sendType;  //
    private String groupName;  //

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}