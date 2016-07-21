package com.wfj.sysmanager.httpModel;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

/**
 * 角色模型
 * 
 * @author 孙宇
 * 
 */
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class Role implements java.io.Serializable {

	private String id;
	private String text;
	private BigDecimal seq;
	private String descript;

	private String parentId;
	private String parentText;
	private String state;
    private String checked;
	private String resourcesId;
	private String resourcesText;
    private String menusId;
	private String menusText;

    public String getMenusId() {
        return menusId;
    }

    public void setMenusId(String menusId) {
        this.menusId = menusId;
    }

    public String getMenusText() {
        return menusText;
    }

    public void setMenusText(String menusText) {
        this.menusText = menusText;
    }

    public String getResourcesId() {
		return resourcesId;
	}

	public void setResourcesId(String resourcesId) {
		this.resourcesId = resourcesId;
	}

	public String getResourcesText() {
		return resourcesText;
	}

	public void setResourcesText(String resourcesText) {
		this.resourcesText = resourcesText;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public BigDecimal getSeq() {
		return seq;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentText() {
		return parentText;
	}

	public void setParentText(String parentText) {
		this.parentText = parentText;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

}
