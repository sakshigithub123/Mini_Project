package com.mini.project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class UserOperation //implements Interface1 
{
	Scanner sc = new Scanner(System.in);
	
	//Menu for User Operations
	public void displayMenu() 

	{
		System.out.println("                     Welcome to Quiz Application               ");
        System.out.println("-------------------------------------------------------------");
        System.out.println("Press 1. for Registration");
        System.out.println("Press 2. for Login");
		System.out.println("Select one option")	;
		 int choice=sc.nextInt();
		 switch(choice)
		 {
		 case 1:
			 System.out.println("Registration Form ");
			 getUserDetails(); 
			 System.out.println("Press 3. to start the quiz");
			 int b=sc.nextInt();
			 displayQuestion();
			 break;
		 case 2:
			 System.out.println("Login Form ");
			 login();
			 break;
		 }
		 
	}
	
    //When you want to take quiz again
	public void again() 
	{
		System.out.println("Do you want to again use this application");
		System.out.println("If yes then press 'y' if no then print 'n'");
		char ch=sc.next().charAt(0);
		if(ch == 'y')
		{
			//instead of disply call login wala method
			displayMenu();
			//return;
		}
		else
		{
			System.out.println("Exit");
		}
	}String fname;
	
    //To get the user details and store it in database (Registration)
	public void getUserDetails() 
	{
		try {
	
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
			String query="insert into Student_Data (fName,lName,username,password,city,mailId,mobileNo)"+ "Values (?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			
			System.out.println("Enter first name");
			String fname=sc.next();
			ps.setString(1, fname);
			
			System.out.println("Enter last name");
			String lname=sc.next();
			ps.setString(2, lname);
			
			System.out.println("Enter username ");
			String username=sc.next();
			ps.setString(3, username);
			
			System.out.println("Enter  password");
			String password=sc.next();
			ps.setString(4, password);
			
			System.out.println("Enter  city");
			String city=sc.next();
			ps.setString(5, city);
			
			System.out.println("Enter  mail Id ");
			String mailId=sc.next();
			ps.setString(6, mailId);
			
			System.out.println("Enter  mobile number");
			int mobileNo=sc.nextInt();
			ps.setInt(7, mobileNo);
			
			int i = ps.executeUpdate();
			System.out.println("Data is added "+i);
			ps.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//login details
	public void login()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
			String query="select * from Student_Data where username=? and password=?";
		//	String query="select * from Student_Data (username,password)"+ "Values (?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			
			System.out.println("Enter the usernme" );
			String user =sc.next();
			ps.setString(1, user);
			
			System.out.println("Enter the password" );
			String pass =sc.next();
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
		
			if(rs.next()) {
			System.out.println("ID :" +rs.getInt(1));
			System.out.println("first name :" +rs.getString(2));
			System.out.println("Lastname :"+rs.getString(3));
			System.out.println("City :"+rs.getString(6));
			System.out.println("Mail :"+rs.getString(7));
			System.out.println("Mobile Number :"+rs.getInt(8));
			System.out.println("Score :"+rs.getInt(9));
			}
			else
			{
				System.out.println("Invalid login credentials.....login failed");
			}
			rs.close();
			con.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	//Quiz Questions
    public void displayQuestion()
    {
    	System.out.println("                          Quiz Questions               ");
        System.out.println("-------------------------------------------------------------");
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
			String query="Select * from question";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			int score=0;
			while(rs.next())
			{
				System.out.println("Question No :" +rs.getInt(1));
				System.out.println("Question :" +rs.getString(2));
				System.out.println("Option 1 :" +rs.getString(4));
				System.out.println("Option 2 :" +rs.getString(5));
				System.out.println("Option 3 :" +rs.getString(6));
				System.out.println("Option 4 :" +rs.getString(7));
	
				System.out.println("Enter your Answer :");
				String a=sc.next();
				System.out.println();
				
				if (a !=null && a.equalsIgnoreCase(rs.getString(3)))
				{
					score++;
				}
			}
			System.out.println("Score is "+score);
			System.out.println("Enter the Name" );
		    fname =sc.next();
		    String q="update Student_Data set score=? where fname=fname";
			//String query1="update into Student_Data set score=? where fname=?";
		    //String query1="insert into Student_Data  (fName,score) values (?,?)";
			PreparedStatement ps1 = con.prepareStatement(q);
			
			//ps1.setString(1, fname);
			ps1.setInt(1, score);
			int i = ps1.executeUpdate();
			System.out.println("Score added into database "+i);
			rs.close();
			ps.close();
			con.close();
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    public static void main(String [] args)
	{
    	UserOperation obj = new UserOperation();
    	obj.displayMenu();
	}
}
