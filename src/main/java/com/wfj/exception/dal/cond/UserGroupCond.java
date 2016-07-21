package com.wfj.exception.dal.cond;

/**
 * Created by MaYong on 2015/8/17.
 */
public class UserGroupCond extends BaseCond {
    private String groupDesc;  //
    private Integer id;  //
    private String groupName;  //
    private Integer sendType;


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
