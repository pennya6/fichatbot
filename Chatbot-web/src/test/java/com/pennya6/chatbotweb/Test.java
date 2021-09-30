package com.pennya6.chatbotweb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
	public static void main(String[] args) {
		
		//대괄호 list 
		//중괄호 map
		ArrayList <Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object>map=new HashMap<String,Object>();
		Map<String,List<Integer>>submap=new HashMap<String, List<Integer>>();
		List<Integer>semilist=new ArrayList<Integer>();
		
		semilist.add(10);
		semilist.add(11);
		semilist.add(12);
		
		List<Integer>finallist=new ArrayList<Integer>();
		
		finallist.add(11);
		finallist.add(12);
		finallist.add(23);
		
		submap.put("semi",semilist);
		submap.put("final", finallist);
		
		map.put("person","사람");
		map.put("sports", "야구");
		map.put("score", submap);
		
		list.add(map);
		
		Map<String,Object>map2=new HashMap<String,Object>();
		
		map2.put("person","사람2");
		map2.put("sports", "축구");
		list.add(map2);
		
		Map<String,Object>map3=new HashMap<String,Object>();
		map3.put("person","사람3");
		map3.put("sports", "농구");
		list.add(map3);
		
		System.out.println(list.toString());
		
		//2.첫번째 사람의 두번째 세미 스코어 값 출력 즉 11
		Map<String,Object>res=list.get(0);
		Map<String,List<Integer>>scoremap=(Map<String,List<Integer>>)map.get("score");
		List<Integer>semi=scoremap.get("semi");
		int value=semi.get(1);
		System.out.println(value);
	}
}	
