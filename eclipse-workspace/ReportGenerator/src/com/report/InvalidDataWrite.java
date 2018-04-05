package com.report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class InvalidDataWrite {
	private ProcessFileData pfd;
	public InvalidDataWrite(ProcessFileData pfd) {
		// TODO Auto-generated constructor stub
		this.pfd = pfd;
	}
	public List<String> invalidData()
	{
		Map<String,List<String>> map = pfd.validateFileData();
		List<String> lstInvalidData = map.get("invalid list");
		return lstInvalidData;
	}
	public void writeInvalidData() {
		try(FileOutputStream fos = new FileOutputStream("InvalidInput.txt"))
		{
			List<String> lst = this.invalidData();
			for (String string : lst) {
				for(int i =0; i<string.length();i++)
				{
					fos.write(string.charAt(i));
				}
				fos.write('\n');
			}
		}
		catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	//testing
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadFile rf = new ReadFile("Input.txt");
		ProcessFileData pfd = new ProcessFileData(rf);
		InvalidDataWrite idw = new InvalidDataWrite(pfd);
		idw.writeInvalidData();
	}

}
