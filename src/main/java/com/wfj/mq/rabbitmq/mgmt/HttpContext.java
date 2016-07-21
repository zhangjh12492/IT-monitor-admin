package com.wfj.mq.rabbitmq.mgmt;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Represents the configuration and tools needed to make requests
 * against the RabbitMQ Management Console.  This is passed to other
 * Fluent interfaces so they can make HTTP calls without needing
 * to be configured.
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class HttpContext {

	private static final Logger logger = LoggerFactory.getLogger(HttpContext.class);
	
	Client client;
	String protocol = "http";
	String hostname;
	int port;
	Gson gson = new Gson();
	
	public HttpContext(
			Client client, String protocol,
			String hostname, int port) {
		
		this.protocol = protocol;
		this.hostname = hostname;
		this.port = port;
		this.client = client;
	}
	
	/**
	 * Compose the URL from the protocol://hostname:port + relativeApiPath
	 * @param relativeApiPath Management route to call
	 * @return URL to call
	 */
	String buildUrl(String relativeApiPath){
		
		return String.format("%s://%s:%s/api%s", this.protocol, this.hostname, this.port, relativeApiPath);
	}
	
	/**
	 * Compose a URI from the protocol://hostname:port + relativeApiPath.
	 * The only reason why this exists is to work around a URL Encoding issue
	 * in which Spring will encode "/" to "%225F" instead of "%2F".
	 * @param relativeApiPath Management route to call.
	 * @return URI to call
	 */
	URI buildUri(String relativeApiPath) {
		
		URI uri = null;
		
		try {
			
			uri = new URI(buildUrl(relativeApiPath));
			
		} catch (URISyntaxException e) {
			
			logger.error("Problem constructing URI: {}", e);
		}
		return uri;
	}
	
	/**
	 * Execute a GET call against the partial URL and deserialize the results.
	 * @param partialUrl Partial URL to build
	 * @param returnType Expected Return type.
	 * @return Your desired return type.
	 */
	public <T> T GET(String partialUrl, GenericType<T> returnType){
		
		URI uri = buildUri(partialUrl);
		
		return makeGetRequest(uri, returnType);
	}
	
	/**
	 * Execute a PUT call against the partial URL.
	 * @param partialUrl Partial URL to build.
	 * @param payload Object to PUT
	 */
	public void PUT(String partialUrl, Object payload){
		
		URI uri = buildUri(partialUrl);
		
		makePutRequest(uri, payload);
	}
	
	/**
	 * Execute a POST call against the partial URL.
	 * @param partialUrl Partial URL to build.
	 * @param payload Object to POST.
	 */
	public void POST(String partialUrl, Object payload){
		
		URI uri = buildUri(partialUrl);
		
		makePostRequest(uri, payload);
	}
	
	/**
	 * Execute a DELETE call against the partial URL.
	 * @param partialUrl Partial URL to build.
	 */
	public void DELETE(String partialUrl){
		
		URI uri = buildUri(partialUrl);
		
		makeDeleteRequest(uri);
	}
	
	/**
	 * Execute a GET request and return the result.
	 * @param uri Raw URI to call.
	 * @param expectedReturnType Type to marshall the result back to.
	 * @return
	 */
	public <T> T makeGetRequest(URI uri, GenericType<T> expectedReturnType){
		
		logger.info("Making get request to: {}", uri.toString());
		
		WebResource webResource = this.client.resource(uri);
		
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		return response.getEntity(expectedReturnType);
	}
	
	/**
	 * Execute a PUT request.
	 * @param uri Raw URI to call.
	 * @param obj Object to send.
	 */
	public void makePutRequest(URI uri, Object obj){
		
		WebResource webResource = this.client.resource(uri);
		
		ClientResponse response = webResource.type("application/json").put(ClientResponse.class, obj);
		
		logger.info("Result of call: {}", response.getStatus());
	}
	
	/**
	 * Execute a POST request.
	 * @param uri Raw URI to call.
	 * @param obj Object to send.
	 */
	public void makePostRequest(URI uri, Object obj){
		
		WebResource webResource = this.client.resource(uri);
		
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, obj);
		
		logger.info("Result of call: {}", response.getStatus());
	}
	
	/**
	 * Execute a DELETE request.
	 * @param uri Raw URI to call.
	 */
	public void makeDeleteRequest(URI uri){
		
		WebResource webResource = this.client.resource(uri);
		
		ClientResponse response = webResource.type("application/json").delete(ClientResponse.class);
		
		logger.info("Result of call: {}", response.getStatus());
	}
}
