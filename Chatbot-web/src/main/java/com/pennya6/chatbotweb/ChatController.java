package com.pennya6.chatbotweb;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
	
	private static final Logger logger=LoggerFactory.getLogger(ChatController.class);
	
	@CrossOrigin("*")
	@GetMapping(value="/chatbot")
	public Map message(Locale locale,Model model) {
		logger.info("메세지 불러오기");
	
		Map map=new HashMap();
		
		
		
		System.out.println(map);
		return map;
	}

}
