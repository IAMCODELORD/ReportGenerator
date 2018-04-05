package com.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WriteReport {
	private StudentDetailsProcessing sdp;
	public WriteReport(StudentDetailsProcessing s) {
		// TODO Auto-generated constructor stub
		sdp = s;
	}
	public List<String[]> processFileDetails()
	{
		List<String[]> lstDetails = sdp.extractStudentDetails();
		List<String[]> lstNew = new ArrayList<>();
		List<String[]> lstFinal = new ArrayList<>();
		for (int i = 0; i < lstDetails.size(); i++) {
			String[] arr = lstDetails.get(i);
			String[] arrNew = new String[3];
			int total = 0;
			if(arr.length == 8)
			{
				total = Integer.parseInt(arr[3]) + Integer.parseInt(arr[5]) + Integer.parseInt(arr[7]);
			}
			if(arr.length == 6)
			{
				total = Integer.parseInt(arr[3]) + Integer.parseInt(arr[5]);
			}
			if(arr.length == 4)
			{
				total = Integer.parseInt(arr[3]);
			}
			arrNew[0] = arr[0];
			arrNew[1] = arr[1];
			//test
			//System.out.println(total);
			String s = ""+total;
			//test
			//System.out.println(s);
			arrNew[2] = s;
			lstNew.add(arrNew);
		}
		List<String> lst = new ArrayList<>();
		for (int i = 0; i < lstNew.size(); i++) {
			String[] arr = lstNew.get(i);
			String name = arr[0];
			//System.out.println(name+"a");
		}
		for (int i = 0; i < lstNew.size(); i++) {
			String[] arr = lstNew.get(i);
			//test
			//System.out.println(arr.toString());
			String[] arrFinal = new String[2];
			String name = arr[0];
			int marks = 0;
			//System.out.println(name);
			if(lst.indexOf(name) == -1)
			{
				for (int i1 = 0; i1 < lstNew.size(); i1++) {
					String[] arrtemp = lstNew.get(i1);
					//System.out.println(lst);
					//System.out.println(lst.indexOf(name)+name);
					if(lst.indexOf(name) == -1)
					{
						if(arrtemp[0].equals(name))
						{
							//System.out.println(arrtemp[0]+name);
							marks += Integer.parseInt(arrtemp[2]);
							//test
							//System.out.println(arrtemp[2]);
							//System.out.println(marks);
						}
					}
					else
					{
						continue;
					}
				}
				marks = (marks*100)/300;
				arrFinal[0] = name;
				String s2 = ""+marks;
				arrFinal[1] = s2;
				lstFinal.add(arrFinal);
			}	
			else
			{
				continue;
			}
			lst.add(name);
	
		}
		//System.out.println(lst);
		return lstFinal;
	}
	public void writeReport() {
		List<String[]> lst = processFileDetails();
		try(
				FileWriter fw = new FileWriter("Report.txt");
		   )
		{	
			for (int i = 0; i < lst.size(); i++) {
				String[] arr = lst.get(i);
					fw.write("Name"+":"+arr[0]+" "+"Percentage"+":"+arr[1]+"\n")		;
			}
		}
		catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadFile rf = new ReadFile("Input.txt");
		ProcessFileData pfd = new ProcessFileData(rf);
		StudentDetailsProcessing sdp = new StudentDetailsProcessing(pfd);
		WriteReport wr = new WriteReport(sdp);
		//System.out.println(wr.processFileDetails());
		List<String[]> lst = wr.processFileDetails();
		for (int i = 0; i < lst.size(); i++) {
			String[] arr = lst.get(i);
			for (int j = 0; j < arr.length; j++) {
				System.out.println(arr[j]);
			}
		}
	}
}
