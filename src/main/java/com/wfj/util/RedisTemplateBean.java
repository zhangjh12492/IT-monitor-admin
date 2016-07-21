package com.wfj.util;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

//@Component
public class RedisTemplateBean {

	private RedisTemplateBean(){}
	
	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;
	
//	@PostConstruct
	public RedisTemplate<Serializable, Serializable> getRedisTemplate(){
		
		return redisTemplate;
	}
}
