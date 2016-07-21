package com.wfj.mq.rabbitmq.mgmt.model;

import java.util.Map;

/**
 * An AMQP Exchange.
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class Exchange {
	
	protected String name;
	protected String vhost = "/";
	protected String type = "direct";
	protected boolean durable = false;
	protected boolean auto_delete = false;
	protected boolean internal = false;;
	protected Map<String, String> arguments;
	
	public Exchange(){}
	
	public Exchange(String name, String vhost, String type) {
		
		this.name = name;
		this.vhost = vhost;
		this.type = type;
	}
	
	public Exchange(
			String name, 
			String vhost, 
			String type, 
			boolean durable,
			boolean auto_delete) {
		
		this.name = name;
		this.vhost = vhost;
		this.type = type;
		this.durable = durable;
		this.auto_delete = auto_delete;
	}
	
	public Exchange(String name, String vhost, String type, boolean durable,
			boolean auto_delete, boolean internal, Map<String, String> arguments) {
		
		this.name = name;
		this.vhost = vhost;
		this.type = type;
		this.durable = durable;
		this.auto_delete = auto_delete;
		this.internal = internal;
		this.arguments = arguments;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getVhost() {
		return vhost;
	}
	
	public void setVhost(String vhost) {
		this.vhost = vhost;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isDurable() {
		return durable;
	}
	
	public void setDurable(boolean durable) {
		this.durable = durable;
	}
	
	public boolean isAutoDelete() {
		return auto_delete;
	}
	
	public void setAutoDelete(boolean shouldAutoDelete) {
		this.auto_delete = shouldAutoDelete;
	}
	
	public boolean isInternal() {
		return internal;
	}
	
	public void setInternal(boolean internal) {
		this.internal = internal;
	}
	
	public Map<String, String> getArguments() {
		return arguments;
	}
	
	public void setArguments(Map<String, String> arguments) {
		this.arguments = arguments;
	}
	
	@Override
	public String toString() {
		return "Exchange [name=" + name + ", vhost=" + vhost + ", type=" + type
				+ ", durable=" + durable + ", auto_delete=" + auto_delete
				+ ", internal=" + internal + ", arguments=" + arguments + "]";
	}
	
}