package com.pennya6.chatbotweb.controller;



import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pennya6.chatbotweb.service.ChatService;
import com.pennya6.chatbotweb.service.NlpService;


@RestController
public class ChatController {
	
	@Autowired ChatService chatService;	
	@Autowired NlpService nlpService;
		
	@CrossOrigin("*")
	@PostMapping(value = "/chat/open")
	public Map<String, Object> open(@RequestBody Map<String, Object> data) {
		
		Map<String, Object> answer = new HashMap<String, Object>();
		String message = "";
		//System.out.println(data);
		answer.put("position", "left");
		answer.put("type", "text");
		answer.put("text", message);
		answer.put("date", new Date());
		
		//return answer;
		return nlpService.open();
	}
	
	@CrossOrigin("*")
	@PostMapping(value = "/chat/message")
	public Map message(@RequestBody Map<String, Object> data, HttpServletRequest req) throws IOException {
		//Utils.stream(chatService.tts(data), res.getOutputStream());
		return chatService.message(data, req);
	}
}