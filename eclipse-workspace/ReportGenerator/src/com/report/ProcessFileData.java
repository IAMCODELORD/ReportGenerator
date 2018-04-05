package com.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessFileData {
	ReadFile rf;
	public ProcessFileData(ReadFile rf) {
		// TODO Auto-generated constructor stub
		this.rf = rf;
	}
	public List<String> extractFileData() {
		List<String> lstContent = rf.fileRead();
		return lstContent;
	}
	public Boolean validateLine(String str) {
		String regexpattern = "[a-zA-Z]+ [a-zA-z]+:Term[12]:(English:[0-9][0-9]?:?)?(Maths:[0-9][0-9]?:?)?(Science:[0-9][0-9]?:?)?";
		Pattern regex = Pattern.compile(regexpattern);
		Matcher lineMatcher = regex.matcher(str);
		return lineMatcher.matches();
	}
	public Map<String,List<String>> validateFileData()
	{
		Map<String, List<String>> map = new HashMap<>();
		List<String> content = extractFileData();
		List<String> contentpcsd = new ArrayList<>();
		List<String> invalidContent = new ArrayList<>();		
		for (String string : content) {
			String str = string.trim();
			if(validateLine(str))
			{
				//System.out.println("valid "+str);
				contentpcsd.add(str);
			}
			else {
				//System.out.println("invalid "+str);
				invalidContent.add(str);
			}
		}
		map.put("valid list", contentpcsd);
		map.put("invalid list", invalidContent);
		return map;
	}
	//testing
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadFile rf = new ReadFile("Input.txt");
		ProcessFileData pfd = new ProcessFileData(rf);
		Map<String, List<String>> map = pfd.validateFileData();
		for (List<String> string : map.values()) {
			System.out.println(string);
		}*/
	}

