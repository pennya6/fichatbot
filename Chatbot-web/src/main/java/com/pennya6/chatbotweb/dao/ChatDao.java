package com.pennya6.chatbotweb.dao;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

public class ChatDao {
	private static final String OPEN_API_URL="http://aiopen.etri.re.kr:8000/Dialog";
	private static final String ACCESS_KEY="6a90a2cc-070b-408d-a2b8-92a2c3b5563d";

	public Map open() {
		
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, String> argument = new HashMap<String, String>();
		
		argument.put("name", "Genie_Pizza");
		argument.put("access_method", "internal_data");
		argument.put("method", "open_dialog");

		params.put("access_key",ACCESS_KEY);
		params.put("argument", argument);
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Map> entity = new HttpEntity<Map>(params, headers);
		RestTemplate rt = new RestTemplate();
		Map resp = rt.postForObject(OPEN_API_URL, entity, Map.class);
		
		return resp;
	}

	public Map message() {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, String> argument = new HashMap<String, String>();
		
		argument.put("name", "Genie_Pizza");
		argument.put("access_method", "internal_data");
		argument.put("method", "open_dialog");
		argument.put("text", (String)map.get(text));
		argument.put("method", "open_dialog");
		
		params.put("ACCESS_KEY",ACCESS_KEY);
		params.put("argument", argument);
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Map> entity = new HttpEntity<Map>(params, headers);
		RestTemplate rt = new RestTemplate();
		Map resp = rt.postForObject(OPEN_API_URL, entity, Map.class);
		
		return resp;
	}
}


