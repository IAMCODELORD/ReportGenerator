package com.report;

import java.util.Map;

public class Student {
	private String studentName;
	private String term;
	private Map<String, Integer> mapSubjectsMarks;
	public Student(String sname, String term,Map<String, Integer> map) {
		// TODO Auto-generated constructor stub
		this.studentName = sname;
		this.term = term;
		this.mapSubjectsMarks = map;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public Map<String, Integer> getMapSubjectsMarks() {
		return mapSubjectsMarks;
	}
	public void setMapSubjectsMarks(Map<String, Integer> mapSubjectsMarks) {
		this.mapSubjectsMarks = mapSubjectsMarks;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return studentName+" "+term+ " "+ mapSubjectsMarks;  
	}
}
