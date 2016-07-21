package com.wfj.sysmanager.dto;

/**
 * Created by MaYong on 2015/9/23.
 */
public class RoleResDto {
    private String id;
    private String roleId;
    private String resId;
    private String roleName;
    private String resName;
    private String src;
    private String parentResName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getParentResName() {
        return parentResName;
    }

    public void setParentResName(String parentResName) {
        this.parentResName = parentResName;
    }
}
