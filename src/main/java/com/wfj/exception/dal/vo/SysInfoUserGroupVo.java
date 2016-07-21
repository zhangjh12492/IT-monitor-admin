package com.wfj.exception.dal.vo;

/**
 * Created by MaYong on 2015/8/19.
 */
public class SysInfoUserGroupVo {

    private Integer id;  //
    private String groupDesc;  //
    private Integer sendType;  //
    private String groupName;  //
    private Integer userGroupId;
    private String sysCode;
    private String sysDesc;
    private Integer sysInfoId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getSysDesc() {
        return sysDesc;
    }

    public void setSysDesc(String sysDesc) {
        this.sysDesc = sysDesc;
    }

    public Integer getSysInfoId() {
        return sysInfoId;
    }

    public void setSysInfoId(Integer sysInfoId) {
        this.sysInfoId = sysInfoId;
    }
}
