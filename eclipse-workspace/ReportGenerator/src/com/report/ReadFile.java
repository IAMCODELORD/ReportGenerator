package com.report;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile 
	{
	private String fileName;
	public ReadFile(String name) 
	{
		// TODO Auto-generated constructor stub
		fileName = name;
	}
	public List<String> fileRead() 
	{
		List<String> lstInputFileLines = new ArrayList<>(); 
		try(
				FileInputStream fis = new FileInputStream("Input.txt");
		)
		{
			int read = fis.read();
			String str = "";
			while(read!=-1)
			{
				if((char)read == '\n')
				{
					lstInputFileLines.add(str);
					str = "";
				}
				str += (char)read;
				read = fis.read();
			}
		}
		catch (IOException  e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return lstInputFileLines;
	}
	//testing
	/*
	public static void main(String[] args) {
		ReadFile rf = new ReadFile("Input.txt");
		List<String> out = rf.fileRead();
		for(String i:out)
		{
			System.out.println(i); 
		}
	}*/
}
