package com.wfj.mq.rabbitmq.mgmt;

import com.wfj.mq.rabbitmq.mgmt.model.Node;

import java.util.Collection;

public class NodeOperations extends BaseFluent {

	public NodeOperations(HttpContext httpContext, RabbitMgmtService mgmtService) {
		super(httpContext, mgmtService);
	}

	
	public Collection<Node> all(){
		
		return HTTP.GET("/nodes", NODE_COLLECTION);
	}
	
	public Node get(String name){
		
		return HTTP.GET(String.format("/nodes/%s", name), NODE);
	}
	
}
