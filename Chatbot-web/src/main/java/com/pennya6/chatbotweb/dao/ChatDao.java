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
	//private static final String OPEN_API_URL="http://aiopen.etri.re.kr:8000/Dialog";
	//사용자 API Key
	private static final String ACCESS_KEY="6a90a2cc-070b-408d-a2b8-92a2c3b5563d";

	public String open() {
		String openApiURL = "http://aiopen.etri.re.kr:8000/Dialog";
        String accessKey = ACCESS_KEY;    // 발급받은 API Key
        String domain = "Pizza-bot";          // 도메인 명
        String access_method = "internal_data";   // 도메인 방식
        String method = "open_dialog";                      // method 호출 방식
        Gson gson = new Gson();
 
        Map<String, Object> request = new HashMap<>();
        Map<String, String> argument = new HashMap<>();
 
        ////////////////////////// OPEN DIALOG //////////////////////////
 
        argument.put("name", domain);
        argument.put("access_method", access_method);
        argument.put("method", method);
 
        request.put("access_key", accessKey);
        request.put("argument", argument);
 
 
        URL url;
        Integer responseCode = null;
        String responBody = null;
        try {
                url = new URL(openApiURL);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
 
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(gson.toJson(request).getBytes("UTF-8"));
                wr.flush();
                wr.close();
 
                responseCode = con.getResponseCode();
                InputStream is = con.getInputStream();
                byte[] buffer = new byte[is.available()];
                int byteRead = is.read(buffer);
                responBody = new String(buffer);
 
                System.out.println("[responseCode] " + responseCode);
                System.out.println("[responBody]");
                System.out.println(responBody);
 
        } catch (MalformedURLException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
    
        return responBody;
		
	}

	
	  public String message(Map map) {
		  
		  System.out.println(map);
		  	String openApiURL = "http://aiopen.etri.re.kr:8000/Dialog";
	        String accessKey = ACCESS_KEY;    // 발급받은 API Key
	        String uuid = (String) map.get("uuid");  // Open Dialog로 부터 생성된 UUID
	        String method = "dialog";           // method 호출 방식
	        String text = (String) map.get("text");          // method 호출 방식
	        Gson gson = new Gson();
	 
	        Map<String, Object> request = new HashMap<>();
	        Map<String, String> argument = new HashMap<>();
	 
	        ////////////////////////// OPEN DIALOG //////////////////////////
	 
	        argument.put("uuid", uuid);
	        argument.put("method", method);
	        argument.put("text", text);
	 
	        request.put("access_key", accessKey);
	        request.put("argument", argument);
	 
	 
	        URL url;
	        Integer responseCode = null;
	        String responBody = null;
	        try {
	                url = new URL(openApiURL);
	                HttpURLConnection con = (HttpURLConnection)url.openConnection();
	                con.setRequestMethod("POST");
	                con.setDoOutput(true);
	 
	                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	                wr.write(gson.toJson(request).getBytes("UTF-8"));
	                wr.flush();
	                wr.close();
	 
	                responseCode = con.getResponseCode();
	                InputStream is = con.getInputStream();
	                byte[] buffer = new byte[is.available()];
	                int byteRead = is.read(buffer);
	                responBody = new String(buffer);
	 
	                System.out.println("[responseCode] " + responseCode);
	                System.out.println("[responBody]");
	                System.out.println(responBody);
	 
	        } catch (MalformedURLException e) {
	                e.printStackTrace();
	        } catch (IOException e) {
	                e.printStackTrace();
	        }
	    
		  
		  return responBody;
	  }
	 
}


