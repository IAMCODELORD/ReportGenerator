package com.report;

import java.sql.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.plaf.SliderUI;

public class StudentDetailsDB {
	private StudentDetailsProcessing sdp;
	private static final String dbClassName = "com.mysql.jdbc.Driver";
	private static final String Connection = "jdbc:mysql://127.0.0.1/DBdemoEclipse";	
	public StudentDetailsDB(StudentDetailsProcessing s) {
		// TODO Auto-generated constructor stub
		sdp =s;
	}
	public Connection dbConnection()
	{
		Connection conn = null;
		try {
			//Load the jdbc classes and creates a drivermanager class factory
			Class.forName(dbClassName);
			Properties p = new Properties();
			p.put("user", "nitish");
			p.put("password", "root");
			//try to connect 
			conn = DriverManager.getConnection(Connection, p);
//			System.out.println("Connection established");
		}
		catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}
	public void storeDetailsDB()
	{
		List<String[]> lstDetails = sdp.extractStudentDetails();
		Connection conn = dbConnection();
		//create the statement
		PreparedStatement ps = null;
		try 
		{
			ps = conn.prepareStatement("drop table Student");
			ps.executeUpdate();
			ps = conn.prepareStatement("create table Student(Name varchar(24),Term varchar(20),Engish int,Maths int,Science int)");
			int bool = ps.executeUpdate();
			System.out.println(bool);
			if(bool == 0)
			{
				System.out.println("Table Created");
			}
			ps = conn.prepareStatement("insert into Student values(?,?,?,?,?)");
			for (int i = 0; i < lstDetails.size(); i++) {
				String[] arr = lstDetails.get(i);
					ps.setString(1, arr[0]);
					ps.setString(2, arr[1]);
					if(arr.length == 8)
					{
						ps.setInt(3, Integer.parseInt(arr[3]));
						ps.setInt(4, Integer.parseInt(arr[5]));
						ps.setInt(5, Integer.parseInt(arr[7]));
					}
					if(arr.length == 6)
					{
						ps.setInt(3, Integer.parseInt(arr[3]));
						ps.setInt(4, Integer.parseInt(arr[5]));
						ps.setInt(5, Integer.parseInt("0"));
					}
					if(arr.length == 4)
					{
						ps.setInt(3, Integer.parseInt(arr[3]));
						ps.setInt(4, Integer.parseInt("0"));
						ps.setInt(5, Integer.parseInt("0"));
					}
					if(ps.executeUpdate() == 1)
					{
						System.out.println("Row inserted");
					}
			}
			conn.close(); 
		}
		catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
	//testing
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadFile rf = new ReadFile("Input.txt");
		ProcessFileData pfd = new ProcessFileData(rf);
		StudentDetailsProcessing sdp = new StudentDetailsProcessing(pfd);
		//Map<String, Student> map = sdp.storeDetails();
		StudentDetailsDB sd = new StudentDetailsDB(sdp);
		sd.storeDetailsDB();
	}

}
