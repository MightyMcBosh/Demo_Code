package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class People {
	
	 
		
	public static void main (String[] args)
	{
		
		List<Person> classPeople = new ArrayList<Person>();
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(new File("src/practice/People.txt"));
			
			String name = null;
			 double score = 0.0; 
			 int age = 0; 
			 double totalScore = 0; 
			 int totalAge = 0;
			 int oldestAge = 0; 
			 String oldestName = "Fred";
			 for(int i = 0; i < 2000; i++)
			 {	
				 
				 if(!fileScanner.hasNextInt() && !fileScanner.hasNextDouble())
				 name = fileScanner.next();
				 
				 else if(fileScanner.hasNextInt())
				 age = fileScanner.nextInt();
				 
				 else if(fileScanner.hasNextDouble())
				 score = fileScanner.nextDouble();
				 
				 
				 if(age > oldestAge)
				 {
					 oldestAge = age; 
					 oldestName = name; 
				 }
				 
				 totalScore= totalScore + score; 
				
				 
				 
				 
				 
				 
				 classPeople.add(new Person(name,age,score)); 
			}
			 
			 System.out.println("AverageScore =  " +  totalScore/2000); 
			 //System.out.println(); 
			 System.out.printf("Oldest person is: " + oldestName + " and they are " +  oldestAge); 
			 fileScanner.close(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("it dont woooork");
			
		} 
		
		
		
		 
	}
	

}
