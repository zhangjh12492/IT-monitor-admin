package com.wfj.mq.rabbitmq.mgmt;

import com.wfj.mq.rabbitmq.mgmt.model.Binding;
import com.wfj.mq.rabbitmq.mgmt.model.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

import static com.wfj.mq.rabbitmq.Common.encodeSlashes;


public class QueueOperations extends BaseFluent {
	
	private static final Logger logger = LoggerFactory.getLogger(QueueOperations.class);
	
	public QueueOperations(HttpContext httpContext, RabbitMgmtService mgmtService) {
		super(httpContext, mgmtService);
	}
	
	/**
	 * Get all queues, regardless of what virtual host it is on.
	 * @return Collection of Queues
	 */
	public Collection<Queue> all(){
		
		return HTTP.GET("/queues", QUEUE_COLLECTION);
	}
	
	public Collection<Queue> allOnDefault(String vhost){
		
		logger.debug("Getting queues at from the default vhost.");
		
		return allOnVHost("/");
	}
	
	public Collection<Queue> allOnVHost(String vhost){
		
		logger.debug("Getting queues on vhost: {}.", vhost);
		
		return HTTP.GET(
			String.format("/queues/%s", encodeSlashes(vhost)), QUEUE_COLLECTION);
	}
	
	public Queue get(String queueName){
		
		return get("/", queueName);
	}
	
	public Queue get(String vhost, String queueName){
		
		return HTTP.GET(
			String.format("/queues/%s/%s", encodeSlashes(vhost), queueName), QUEUE);
	}
	
	public QueueOperations create(Queue queue){
		
		String url = String.format("/queues/%s/%s", encodeSlashes(queue.getVhost()), queue.getName());
		
		HTTP.PUT(url, queue);
		
		return this;
	}
	
	public QueueOperations delete(String queueName){
		
		return delete("/", queueName);
	}
	
	public QueueOperations delete(String vhost, String queueName){
		
		String url = String.format("/queues/%s/%s", encodeSlashes(vhost), queueName);
		
		HTTP.DELETE(url);
		
		return this;
	}
	
	public QueueOperations purge(String queueName){
		
		return this.purge("/", queueName);
	}
	
	public QueueOperations purge(String vhost, String queueName){
		
		String url = String.format("/queues/%s/%s/contents", encodeSlashes(vhost), queueName);
		
		HTTP.DELETE(url);
		
		return this;
	}
	
	public Collection<Binding> bindings(String queueName){
		
		return bindings("/", queueName);
	}
	
	public Collection<Binding> bindings(String vhost, String queueName){
		
		return HTTP.GET(
			String.format("/queues/%s/%s/bindings", encodeSlashes(vhost), queueName), 
			BINDING_COLLECTION);
	}
}
