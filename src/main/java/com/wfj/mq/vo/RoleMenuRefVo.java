package com.wfj.mq.vo;

public class RoleMenuRefVo {
	private Integer sid;        //序号
	private Integer roleId;     //角色id
	private Integer menuId;     //菜单id
	private Integer status;     //状态      0不显示  1显示
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
