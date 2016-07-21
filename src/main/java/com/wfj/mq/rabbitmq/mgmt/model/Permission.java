package com.wfj.mq.rabbitmq.mgmt.model;

/**
 * RabbitMQ Permissions schema.  Please remember that 
 * EVERY property, EXCEPT "user" and "vhost", is a 
 * Regular Expression!
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class Permission {
	
	protected String vhost;
	protected String user;
	protected String configure;
	protected String write;
	protected String read;
	
	public Permission(){}
	
	public Permission(String vhost, String user, String configure,
			String write, String read) {
		this.vhost = vhost;
		this.user = user;
		this.configure = configure;
		this.write = write;
		this.read = read;
	}
	
	public String getVhost() {
		return vhost;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getConfigure() {
		return configure;
	}
	
	public String getWrite() {
		return write;
	}
	
	public String getRead() {
		return read;
	}
	
	public void setVhost(String vhost) {
		this.vhost = vhost;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setConfigure(String configure) {
		this.configure = configure;
	}

	public void setWrite(String write) {
		this.write = write;
	}

	public void setRead(String read) {
		this.read = read;
	}

	@Override
	public String toString() {
		return "Permission [vhost=" + vhost + ", user=" + user + ", configure="
				+ configure + ", write=" + write + ", read=" + read + "]";
	}
}
