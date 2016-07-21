package com.wfj.mq.rabbitmq.mgmt.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an AMQP Queue.
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class Queue {

	protected Integer memory;
	protected String idle_since;
	protected String policy;
	protected String exclusive_consumer_tag;
	protected Integer messages_ready;
	protected Integer messages_unacknowledged;
	protected Integer messages;
	protected Integer consumers;
	protected Integer active_consumers;
	protected BackingQueueStatus backing_queue_status;
	protected String name;
	protected String state;
	protected String vhost;
	protected boolean durable = false;
	protected boolean auto_delete = false;
	protected Map<String, String> arguments = new HashMap<String, String>();
	protected String node;
	protected MessageDetails messages_details;
	protected MessageDetails messages_ready_details;
	protected MessageDetails messages_unacknowledged_details;
	
	public Queue(){}
	public Queue(String name, String vhost) {
		this.name = name;
		this.vhost = vhost;
	}
	
	public Queue(String name, String vhost, boolean durable, boolean auto_delete) {
		
		this.name = name;
		this.vhost = vhost;
		this.durable = durable;
		this.auto_delete = auto_delete;
	}
	
	public Queue(String name, String vhost, boolean durable,
			boolean auto_delete, Map<String, String> arguments) {
		this.name = name;
		this.vhost = vhost;
		this.durable = durable;
		this.auto_delete = auto_delete;
		this.arguments = arguments;
	}

	public Integer getMemory() {
		return memory;
	}
	
	public String getIdleSince() {
		return idle_since;
	}
	
	public String getPolicy() {
		return policy;
	}
	
	public void setPolicy(String policy) {
		this.policy = policy;
	}
	
	public String getExclusiveConsumerTag() {
		return exclusive_consumer_tag;
	}
	
	public Integer getMessagesReady() {
        if(this.messages_ready == null){
            this.messages_ready = 0;
        }
		return messages_ready;
	}
	
	public Integer getMessagesUnacknowledged() {
        if(this.messages_unacknowledged == null){
            this.messages_unacknowledged = 0;
        }
		return messages_unacknowledged;
	}
	
	public Integer getMessages() {
		return messages;
	}
	
	public Integer getConsumers() {
		return consumers;
	}
	
	public Integer getActiveConsumers() {
		return active_consumers;
	}
	
	public BackingQueueStatus getBackingQueueStatus() {
		return backing_queue_status;
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
	
	public boolean isDurable() {
		return durable;
	}
	
	public void setDurable(boolean durable) {
		this.durable = durable;
	}
	
	public boolean isAutoDelete() {
		return auto_delete;
	}
	
	public void setAutoDelete(boolean autoDelete) {
		this.auto_delete = autoDelete;
	}
	
	public Map<String, String> getArguments() {
		return arguments;
	}
	
	public void setArguments(Map<String, String> arguments) {
		this.arguments = arguments;
	}
	
	public String getNode() {
		return node;
	}

	public MessageDetails getMessagesDetails() {
		return messages_details;
	}

	public MessageDetails getMessagesReadyDetails() {
		return messages_ready_details;
	}

	public MessageDetails getMessagesUnacknowledgedDetails() {
		return messages_unacknowledged_details;
	}

    public String getIdle_since() {
        return idle_since;
    }

    public String getExclusive_consumer_tag() {
        return exclusive_consumer_tag;
    }

    public Integer getMessages_ready() {
        return messages_ready;
    }

    public Integer getMessages_unacknowledged() {
        return messages_unacknowledged;
    }

    public Integer getActive_consumers() {
        return active_consumers;
    }

    public BackingQueueStatus getBacking_queue_status() {
        return backing_queue_status;
    }

    public boolean isAuto_delete() {
        return auto_delete;
    }

    public MessageDetails getMessages_details() {
        return messages_details;
    }

    public MessageDetails getMessages_ready_details() {
        return messages_ready_details;
    }

    public MessageDetails getMessages_unacknowledged_details() {
        return messages_unacknowledged_details;
    }

    public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public void setIdle_since(String idle_since) {
        this.idle_since = idle_since;
    }

    public void setExclusive_consumer_tag(String exclusive_consumer_tag) {
        this.exclusive_consumer_tag = exclusive_consumer_tag;
    }

    public void setMessages_ready(Integer messages_ready) {
        this.messages_ready = messages_ready;
    }

    public void setMessages_unacknowledged(Integer messages_unacknowledged) {
        this.messages_unacknowledged = messages_unacknowledged;
    }

    public void setMessages(Integer messages) {
        this.messages = messages;
    }

    public void setConsumers(Integer consumers) {
        this.consumers = consumers;
    }

    public void setActive_consumers(Integer active_consumers) {
        this.active_consumers = active_consumers;
    }

    public void setBacking_queue_status(BackingQueueStatus backing_queue_status) {
        this.backing_queue_status = backing_queue_status;
    }

    public void setAuto_delete(boolean auto_delete) {
        this.auto_delete = auto_delete;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public void setMessages_details(MessageDetails messages_details) {
        this.messages_details = messages_details;
    }

    public void setMessages_ready_details(MessageDetails messages_ready_details) {
        this.messages_ready_details = messages_ready_details;
    }

    public void setMessages_unacknowledged_details(MessageDetails messages_unacknowledged_details) {
        this.messages_unacknowledged_details = messages_unacknowledged_details;
    }

    @Override
	public String toString() {
		return "Queue [memory=" + memory + ", idle_since=" + idle_since
				+",state="+state
				+ ", policy=" + policy + ", exclusive_consumer_tag="
				+ exclusive_consumer_tag + ", messages_ready=" + messages_ready
				+ ", messages_unacknowledged=" + messages_unacknowledged
				+ ", messages=" + messages + ", consumers=" + consumers
				+ ", active_consumers=" + active_consumers
				+ ", backing_queue_status=" + backing_queue_status + ", name="
				+ name + ", vhost=" + vhost + ", durable=" + durable
				+ ", auto_delete=" + auto_delete + ", arguments=" + arguments
				+ ", node=" + node + ", messages_details=" + messages_details
				+ ", messages_ready_details=" + messages_ready_details
				+ ", messages_unacknowledged_details="
				+ messages_unacknowledged_details + "]";
	}
	
}