package com.wfj.sysmanager.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Syuser entity. @author MyEclipse Persistence Tools
 */

public class Syuser implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String password;
	private Date createdatetime;
	private Date modifydatetime;
    private String userName;
    private String email;
    private String tel;
	private Set syuserSyroles = new HashSet(0);

	// Constructors

	/** default constructor */
	public Syuser() {
	}

	/** minimal constructor */
	public Syuser(Integer id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	/** full constructor */
	public Syuser(Integer id, String name, String password, Date createdatetime, Date modifydatetime, Set syuserSyroles) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.createdatetime = createdatetime;
		this.modifydatetime = modifydatetime;
		this.syuserSyroles = syuserSyroles;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedatetime() {
		return this.createdatetime;
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	public Date getModifydatetime() {
		return this.modifydatetime;
	}

	public void setModifydatetime(Date modifydatetime) {
		this.modifydatetime = modifydatetime;
	}

	public Set getSyuserSyroles() {
		return this.syuserSyroles;
	}

	public void setSyuserSyroles(Set syuserSyroles) {
		this.syuserSyroles = syuserSyroles;
	}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

	@Override
	public String toString() {
		return "Syuser [id=" + id + ", name=" + name + ", password=" + password + ", createdatetime=" + createdatetime + ", modifydatetime=" + modifydatetime + ", userName=" + userName + ", email="
				+ email + ", tel=" + tel + ", syuserSyroles=" + syuserSyroles + "]";
	}
    
    
}