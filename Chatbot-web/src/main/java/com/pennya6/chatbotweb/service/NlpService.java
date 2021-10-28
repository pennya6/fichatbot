package com.pennya6.chatbotweb.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pennya6.chatbotweb.dao.ChatDao;
import com.pennya6.chatbotweb.dao.NlpDao;

@Service
public class NlpService {

	//private ChatDao chatDao = new ChatDao();
	private NlpDao nlpDao =new NlpDao();

	@SuppressWarnings("unchecked")
	public Map<String, Object> open() {
		
		//String json = nlpDao.nlp();
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String,Object>>list=new ArrayList<Map<String,Object>>();
	
		Map<String, Object> res = new HashMap<String, Object>();
		
			
			res.put("left", "chatbot");
			res.put("text","안녕하세요 챗봇입니다. 무엇을 도와드릴까요?");
			res.put("createdAt", new Date());
	
		return res;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> message(Map<String, Object> data, HttpServletRequest servletReq) {	
		
		Map question=(Map)data.get("question");
		
		String json = nlpDao.nlp((String)question.get("text"));
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String,Object>>list=new ArrayList<Map<String,Object>>();
	
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			Map<String, Object> map = mapper.readValue(json, Map.class);		
			Map<String, Object> return_object = (Map<String, Object>) map.get("return_object");
			Map <String,Object> sentences=(Map <String,Object>)return_object.get("sentence");		
			Map <String,Object>sentence=(Map <String,Object>)sentences.get(0);
			List <Map <String,Object>>morp=(List<Map<String, Object>>) sentence.get("morp");
			
			String intent=(String)getSimilarity(morp);
			String answer=getAnswer(intent);
			
			res.put("left", "chatbot");
			res.put("text","안녕하세요 챗봇입니다. 무엇을 도와드릴까요?");
			res.put("createdAt", new Date());
		
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
	//유사도 검사
	private String getSimilarity(List<Map<String,Object>> morp){
		
		String trainingText="치즈 피자 라지 사이즈 주세요";//DB에 저장할 질문들
		//선택-1. 객체사전
		Map<String,Object>entity=new HashMap<String, Object>();
		entity.put("pepeorni","페페로니");
		entity.put("pizza","피자");
		
		//유사도검사
		int count=0;
		for(Map<String,Object>word : morp) {
			String lemma=(String)word.get("lemma");
			String type=(String)word.get("type");
			
			Iterator<String>keys=entity.keySet().iterator();
			while(keys.hasNext()) {
				String key=keys.next();
				String value=(String)entity.get(key);
				
				if(lemma.equals(value)) {
					count++;
					break;
				}
			}
		}
		
		double similarity=(double)count/morp.size(); //유사도
		
		String intent="";
		if(similarity>0.2) {
			//발화라고 본다
			//DB에서 select intent where 키워드=""
			intent="피자주문";
		}
		
		// 2. 문장학습
		
		
		return intent;
	}
	private String getAnswer(String intent) {
		//intent로  DB에서 답변 조회해오기
		//intent "피자주문",질문 "페페로니 피자 주세요","치즈 피자 라지 사이즈 주세요"
		//select * from where intent=intent;
		
		//임시코드
		String answer="";
		if(intent.equals("피자자문")) {
			answer="피자가 주문되었습니다."; //임시
		}else {
			answer="잘못드렸습니다. 다시 말씀해주세요";
		}
		
		return answer;
	}
}