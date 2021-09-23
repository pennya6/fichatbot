package com.pennya6.chatbotweb;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//의존성 주입
@RestController
public class ChatController {
	
	private static final Logger logger=LoggerFactory.getLogger(ChatController.class);
	
	//요청이 들어오면 받아라
	@CrossOrigin("*")
	//method가 post이기 때문에 postmapping
	@PostMapping(value="/chat") 
	//requestmapping도 사용하기는 함
	//단 getmapping하면 더 길이가 줌
	public Map message(Locale locale,Model model) {
		logger.info("메세지 불러오기");
	
		//자료구조 map
		Map map=new HashMap();
		Map submap=new HashMap();
		
		map.put("id", "chatbot");
		map.put("text", "안녕 나는 챗봇이야");
		map.put("createdAt", new Date());
		
		// user가 생긴게 다른 아이들과는 다르게 생김
		//user : {
		//	id:"user"
		//}
		submap.put("id","user"); //id:"user"형식
		//다시 넣기
		map.put("user",submap);
		
		System.out.println(map);
		
		//자바에서만 인식하는 map구조
		//content-type에 맞지 않는 타입으로 리턴되어서 404에러
		//json타입으로 return -> pom에서 추가
		return map;
	}

}
