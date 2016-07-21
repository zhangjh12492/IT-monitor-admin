package com.wfj.mq.rabbitmq.mgmt;

import com.wfj.mq.rabbitmq.mgmt.model.Permission;
import com.wfj.mq.rabbitmq.mgmt.model.User;

import java.util.Collection;


public class UserOperations extends BaseFluent {

	public UserOperations(HttpContext httpContext, RabbitMgmtService mgmtService) {
		super(httpContext, mgmtService);
	}
	
	public Collection<User> all(){
		
		return HTTP.GET("/users", USER_COLLECTION);
	}
	
	public User whoAmI(){
		
		return HTTP.GET("/whoami", USER);
	}
	
	public Collection<Permission> permissionsFor(String user){
		
		return HTTP.GET(String.format("/users/%s/permissions", user), PERMISSION_COLLECTION);
	}
	
	public User get(String username){
		
		return HTTP.GET(String.format("/users/%s", username), USER);
	}
	
	public UserOperations create(User user){
		
		HTTP.PUT(String.format("/users/%s", user.getName()), user);
		
		return this;
	}
	
	public UserOperations delete(String name){
		
		HTTP.DELETE(String.format("/users/%s", name));
		
		return this;
	}
}
