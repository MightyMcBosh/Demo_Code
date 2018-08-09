package assignment09;
//test class for assignment 9 
public class StringWrapper {
	String data; 
	public StringWrapper(String data)
	{
		this.data = data;
	}
	
	public boolean equals(StringWrapper s)
	{
		if(data.compareTo(s.data)!= 0 )
			return true; 
		else
			return false; 
	}
}
