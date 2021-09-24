package com.pennya6.chatbotweb.util;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@Repository
public class CacheUtils implements InitializingBean {

	private Cache<String, Object> cache = null;
	
	public void put(String key, Object value) {
		cache.put(key, value);
	}

	public Object get(String key) {
		return this.cache.getIfPresent(key);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		cache = CacheBuilder.newBuilder()
				.expireAfterWrite(15, TimeUnit.MINUTES)
				.maximumSize(1000)
				.build();
	}
}