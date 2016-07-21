package com.wfj.mq.entity;
/**
 * 角色权限映射关系
 * @author xupy
 *
 */
public class RolePermitRef {

	private Integer sid;
	private Integer roleId;
	private Integer perId;
	private String  refDesc;
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
	public Integer getPerId() {
		return perId;
	}
	public void setPerId(Integer perId) {
		this.perId = perId;
	}
	public String getRefDesc() {
		return refDesc;
	}
	public void setRefDesc(String refDesc) {
		this.refDesc = refDesc;
	}
	
	
}
