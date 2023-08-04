package com.mini.project;

import java.util.Scanner;

public class MainClass {
public static void main(String[] args) 
		{
			Scanner sc = new Scanner(System.in);
		
			AdminOperation obj = new AdminOperation();
			UserOperation obj1 = new UserOperation();
			System.out.println("Press 1. If you are a Admin");
	        System.out.println("Press 2. If you are a user");
			System.out.println("Select one option")	;
			int a=sc.nextInt();
			 switch(a)
			 {
			 case 1:
				 System.out.println("Admin Opeartion: ");
				 obj.displayMenuForAdmin();
				 break;
			 case 2:
				 System.out.println("User Opeartion: ");
				obj1.displayMenu();
				 break;
			 }
			 sc.close();
		}

	}
