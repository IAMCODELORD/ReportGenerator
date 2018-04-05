package com.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDetailsProcessing {
	private ProcessFileData pfd;
	public StudentDetailsProcessing(ProcessFileData pfd) {
		// TODO Auto-generated constructor stub
		this.pfd = pfd;
	}
	public List<String[]> extractStudentDetails() {
		Map<String, List<String>> map = pfd.validateFileData();
		List<String> lst = map.get("valid list");
		List<String[]> lstArrays = new ArrayList<>();
		for (int j=0;j<lst.size();j++) {
			String[] arr = lst.get(j).split(":");	
			lstArrays.add(arr);
		}
		return lstArrays; 
	}
	//Store details in student class
	public Map<String, Student> storeDetails()
	{
		Map<String, Integer> mapSubMks = new HashMap<>();
		Map<String, Student> mapStudents = new HashMap<>();
		String sname,term;
		List<String[]> lst =this.extractStudentDetails(); 
		for (int j=0;j<lst.size();j++) {
			String[] arr = lst.get(j);
			sname = arr[0];
			term = arr[1];
			for (int i = 2; i < arr.length; i+=2) {
				mapSubMks.put(arr[i], Integer.valueOf(arr[i+1]));
			}
			mapStudents.put(sname+term, new Student(sname, term, mapSubMks));
		}
		return mapStudents;
	}
	// to generate 
	
	//testing
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadFile rf = new ReadFile("Input.txt");
		ProcessFileData pfd = new ProcessFileData(rf);
		StudentDetailsProcessing sdp = new StudentDetailsProcessing(pfd);
		Map<String, Student> map = sdp.storeDetails();
		System.out.println(map);
	} 

}
