package practice;



	// stores person objects
public class Person {
	
	private String name;
	private int age; 
	private double score; 
	
	
	public Person(String name, int age, double score)
	{
		this.name = name; 
		this.age = age; 
		this.score = score;
	}
	
	//accessor methods
	
	public String getName()
	{
		return name; 
	}
	
	public int getAge()
	{
		return age; 
	}
	
	public double getScore()
	{
		return score; 
	}
	
	
	public String toString()
	{
		String total = name + " " + age + " " + score; 
		//System.out.println(total);
		return total;  
	}

}
