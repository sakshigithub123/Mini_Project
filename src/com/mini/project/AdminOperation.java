package com.mini.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class AdminOperation
{
	Scanner sc = new Scanner(System.in);
	public void displayMenuForAdmin()
	{
		System.out.println("                     Welcome to Quiz Application               ");
        System.out.println("-------------------------------------------------------------");
//        System.out.println("Press 1.  to see all students score");
        System.out.println("Press 1.  to fetch student score using Id");
        System.out.println("Press 2.  to see quiz questions");
		System.out.println("Select one option")	;
		 int choice=sc.nextInt();
		 switch(choice)
		 {
		 case 1:
			 getStudentScore();
			 break;
		 case 2:
			 displayQuoAndAns();
			 break;
		 }
	}
	public void displayQuoAndAns()
	{
		System.out.println("                          Quiz Questions               ");
        System.out.println("-------------------------------------------------------------");
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
			String query="Select * from question";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			System.out.println("Questions with Answers");
			while(rs.next())
			{
				System.out.println("Question No :" +rs.getInt(1));
				System.out.println("Question :" +rs.getString(2));
				System.out.println("Answer 1 :" +rs.getString(3));
	        }
			ps.close();
			rs.close();
			con.close();
            }
    	catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void getStudentScore()
	{
		try 
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
		String query="Select * from student_data where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		System.out.println("Enter Students Id");
		int id =sc.nextInt();
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) 
		{
			System.out.println("ID : "+rs.getInt(1)); 
			System.out.println("first name:" +rs.getString(2));
			System.out.println("Lastname :"+rs.getString(3)); 
			System.out.println("Score :"+rs.getString(9)); 
			}
		else
		   {
			System.out.println("Invalid Students Id..... ");
			}

			rs.close();
			con.close();
		}
		catch (Exception e) {
			
			e.printStackTrace();}
}
	public static void main(String [] args)
	{
		AdminOperation obj1 = new AdminOperation();
		obj1.displayMenuForAdmin();
	}
}