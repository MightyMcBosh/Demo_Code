package assignment09;

import java.util.HashMap;

public class Quest2D {
 
	
	private static void main(String[] args)
	{
		HashMap hash;
		//Creates a hash map with keys of type String that map to Integer values; 
		hash = new HashMap<String, Integer>(); 
		hash.put("fifteen", 15); 
		int value; 
		//in this case, we check to see if it contains a value, then return it if it does. Sorta inefficient, but 
		//this will keep it from throwing exceptions
		if(hash.containsKey("fourteen"))
		{
			//Returns an object, have to cast it to the expected value
			value = (Integer)hash.get("fourteen"); 
		}
			
	}

}
