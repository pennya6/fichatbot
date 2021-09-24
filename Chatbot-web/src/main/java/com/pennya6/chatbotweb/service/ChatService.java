package com.pennya6.chatbotweb.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.pennya6.chatbotweb.dao.ChatDao;
import com.pennya6.chatbotweb.util.CacheUtils;

@Service
public class ChatService {
	
	@Autowired CacheUtils cache;
	
	public Map open() {
		
		ChatDao chatDao=new ChatDao();
		Map resp=chatDao.open();
		
		System.out.println(resp);
		
		Map return_object=MapUtils.getMap(resp, "return_object");
		String uuid=MapUtils.getString(return_object, "uuid");
		//System.out.println(uuid);
		cache.put("uuid",uuid);
		return makeTemplate(resp);
	}

	@SuppressWarnings("unchecked")
	private Map<String,Object> makeTemplate(Map<String,Object> resp) {
	
		Map<String,Object> map=new HashMap();
		Map<String,Object> chatbotInfo=new HashMap();
		
		Map<String,Object> return_object=(Map<String,Object>)MapUtils.getMap(resp, "return_object");
		Map<String,Object> result=(Map<String,Object>)MapUtils.getMap(return_object,"result");
		chatbotInfo.put("id","user");
		
		map.put("id", "chatbot");
		System.out.println(return_object);
		System.out.println(result);
		map.put("text", MapUtils.getString(result, "system_text"));
		map.put("createdAt", new Date());
		map.put("user",chatbotInfo);
		
		System.out.println(map);
		
		return map;

	}

	public Map message(Map<String, Object> data) {
		ChatDao chatDao=new ChatDao();
		
		String uuid=(String)cache.get("uuid");
		data.put("uuid", uuid);
		
		//Map resp=chatDao.message();
		return chatDao.message(data);
	}

}
