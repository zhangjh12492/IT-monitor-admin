package com.wfj.mq.rabbitmq.mgmt.model;

/**
 * Represents a RabbitMQ User/Client.  
 * 
 * Tags = authority in RabbitMQ Management Plugin
 * Options: "admin", "monitoring", "management";
 * This can be multiple (string separated by commas).
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class User {

	protected String name;
	protected String tags;
	protected String password;
	protected String password_hash;
	
	public User(){}
	
	public User(String name, String tags) {
		this.name = name;
		this.tags = tags;
	}
	
	public User(String name, String tags, String password) {
		this.name = name;
		this.tags = tags;
		this.password = password;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordHash() {
		return password_hash;
	}

	public String getName() {
		return name;
	}
	
	public String getTags() {
		return tags;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", tags=" + tags + ", password="
				+ password + ", password_hash=" + password_hash + "]";
	}
}