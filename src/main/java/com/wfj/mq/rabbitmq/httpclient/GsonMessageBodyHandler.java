package com.wfj.mq.rabbitmq.httpclient;

import com.google.gson.Gson;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Encapsulates GSON support for custom serializing/deserializing objects.
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public final class GsonMessageBodyHandler implements MessageBodyWriter<Object>, MessageBodyReader<Object> {

    private static final String CHARSET = "UTF-8";

    private Gson gson;

    private Gson getGson() {
        if (gson == null) { gson = new Gson(); }

        return gson;
    }

    @Override
	public boolean isReadable(
			Class<?> clazz, Type type, Annotation[] annotations,
			MediaType mediaType) {
		
		return true;
	}
    
    @Override
    public boolean isWriteable(
    		Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    	
    		return true;
    }

    @Override
    public long getSize(
    		Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    	
        return -1;
    }
    
    @Override
    public void writeTo(
    		Object object, Class<?> type, Type genericType, Annotation[] annotations,
        MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
        OutputStream entityStream) throws IOException, WebApplicationException {
    	
        OutputStreamWriter outputStreamWriter = null;
        
        try {
        	
        		outputStreamWriter = new OutputStreamWriter(entityStream, CHARSET);
        	
            Type jsonType = getAppropriateType(type, genericType);

            getGson().toJson(object, jsonType, outputStreamWriter);
            
        } finally {
        	
        		if (outputStreamWriter != null)
        			outputStreamWriter.close();
        }
    }

	@Override
	public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
			InputStream inputStream) throws IOException, WebApplicationException {
		
		Object result = null;
		InputStreamReader inputStreamReader = null;
		
		try {
		
			inputStreamReader = new InputStreamReader(inputStream, CHARSET);
			
			Type jsonType = getAppropriateType(type, genericType);
		
			result = getGson().fromJson(inputStreamReader, jsonType);
		
		} finally {
		
			if (inputStreamReader != null)
				inputStreamReader.close();
		}
		
		return result;
	}
	
	Type getAppropriateType(Class<?> type, Type genericType){
		
		return (type.equals(genericType))? type : genericType;
	}
	
}