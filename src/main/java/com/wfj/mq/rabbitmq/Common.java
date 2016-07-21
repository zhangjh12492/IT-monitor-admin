package com.wfj.mq.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Common {
	
	private static final Logger logger = LoggerFactory.getLogger(Common.class);
	
	/**
	 * A function to allow me to be really lazy in building the maps of String, String.
	 * 
	 * For example: map("key1", "value1", "key2", 2);
	 * Returns: new HashMap<String, String>(){ put("key1", "value1"); put("key2", "2"); };
	 * 
	 * @param alternatingKeyValues An even-numbered array of alternating key, value pairs.
	 * @return
	 */
	public static Map<String, String> map(Object... alternatingKeyValues){
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		if (alternatingKeyValues.length > 0 
			// this must be an even number of arguments
			&& (alternatingKeyValues.length % 2) == 0){
			
			for (int i = 0; i < alternatingKeyValues.length; i += 2){
				
				map.put(
					alternatingKeyValues[i].toString(), 
					alternatingKeyValues[i + 1].toString());
			}
		}
		return map;
	}
	
	/**
	 * Virtual Hosts use "/" in their names.  So we need to 
	 * encode the "/" to it's URL encoded representation "%2F".
	 * @param vhost Virtual Host
	 * @return Encoded String
	 */
	public static String encodeSlashes(String vhost){
		
		String encodedValue = vhost;
		
		try {
			
			encodedValue = URLEncoder.encode(vhost, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			
			logger.error("Failed to encode value: {}", vhost);
		}
		
		return encodedValue;
	}
}
