package com.wfj.mq.rabbitmq.mgmt;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.wfj.mq.rabbitmq.httpclient.HttpClientProvider;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Java binding to the RabbitMQ Management Service.
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class RabbitMgmtService {
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitMgmtService.class);
	
	HttpClientProvider httpClientProvider;
	String protocol = "http";
	String hostname;
	int port;
	HttpContext httpContext;
	
	public RabbitMgmtService(){}
	
	public RabbitMgmtService(String hostname, int port, HttpClientProvider httpClientProvider){
		
		this.hostname = hostname;
		this.port = port;
		this.httpClientProvider = httpClientProvider;
	}
	
	public RabbitMgmtService(String hostname, int port, boolean useSsl, HttpClientProvider httpClientProvider){
		
		this.hostname = hostname;
		this.port = port;
		this.httpClientProvider = httpClientProvider;
		this.setUseSsl(useSsl);
	}
	
	/**
	 * This needs to be called when you are done setting config properties.
	 */
	public RabbitMgmtService initialize(){
		
		Client client = httpClientProvider.getClient();
		
		httpContext = new HttpContext(client, protocol, hostname, port);
		
		logger.info("RabbitMgmtService initialized.");
		
		return this;
	}
	
	/**
	 * Set the client provider.
	 * @param httpClientProvider Interface that allows you to generically configure
	 * the underlying Jersey Client provider.
	 */
	public void setHttpClientProvider(HttpClientProvider httpClientProvider) {
		this.httpClientProvider = httpClientProvider;
	}
	
	/**
	 * Set whether SSL should be used.  It's up to you to configure the connection
	 * to appropriately use SSL.
	 * @param useSsl true if we are using SSL
	 */
	public void setUseSsl(boolean useSsl) {
		
		this.protocol = (useSsl)? "https" : "http";
	}

	/**
	 * Set the name of the RabbitMQ broker with the MGMT console to connect to.
	 * @param hostname DNS name of the host.
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	/**
	 * Set the port of the RabbitMQ Management Console.
	 * @param port Port of the RabbitMQ Management Console.
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	/**
	 * Get the HttpContext we're using to make calls and
	 * deserialize payloads.
	 * @return HttpContext
	 */
	public HttpContext getHttpContext(){
		
		return this.httpContext;
	}
	
	/**
	 * Get the overview for this node.
	 * @return Overview of the node with stats about usage.
	 */
	public JSONObject overview(){
		
		return this.httpContext.GET("/overview", new GenericType<JSONObject>(){});
	}
	
	/**
	 * Get operations related to exchanges.
	 * @return Exchange Operations
	 */
	public ExchangeOperations exchanges(){
		
		return new ExchangeOperations(httpContext, this);
	}
	
	/**
	 * Get operations related to queues.
	 * @return Queue Operations
	 */
	public QueueOperations queues(){
		
		return new QueueOperations(httpContext, this);
	}
	
	/**
	 * Get operations related to vhosts.
	 * @return VHost Operations
	 */
	public VirtualHostOperations vhosts(){
		
		return new VirtualHostOperations(httpContext, this);
	}
	
	
	/**
	 * Get operations related to users.
	 * @return User Operations
	 */
	public UserOperations users(){
		
		return new UserOperations(httpContext, this);
	}
	
	/**
	 * Get operations related to permissions.
	 * @return Permission Operations
	 */
	public PermissionOperations permissions(){
		
		return new PermissionOperations(httpContext, this);
	}
	
	/**
	 * Get operations related to nodes.
	 * @return Node Operations
	 */
	public NodeOperations nodes(){
		
		return new NodeOperations(httpContext, this);
	}
	
	/**
	 * Get operations related to bindings.
	 * @return Binding Operations
	 */
	public BindingOperations bindings(){
		
		return new BindingOperations(httpContext, this);
	}
}