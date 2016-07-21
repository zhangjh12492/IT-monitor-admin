package com.wfj.mq.rabbitmq.httpclient;

import com.sun.jersey.api.client.Client;

public interface HttpClientProvider {
	
	public Client getClient();
	
}