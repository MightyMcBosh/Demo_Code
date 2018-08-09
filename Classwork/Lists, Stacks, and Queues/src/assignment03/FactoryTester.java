package assignment03;

public class FactoryTester {
	
	public static void main(String[] args)
	{

	MyFactory<String> factory = new MyFactory<String>(); ; 
	UtahList<String> list = factory.makeArrayList(); 
	String string1 = "adsegf"; 
	String string2 = "1234f"; 
	
	String string3 = "1234123"; 
	
	String string4 = "fawecvaqw"; 
	
	String string5 = "faqwergdv"; 
	
	String string6 = "gashbga"; 
	
	String string7 = "argvaev"; 
	
	
	String string8 = "agerwdv"; 
	String string9 = "agervavrdvf"; 
	list.insert(0,string1); 
	list.insert(1,string2); 
	list.insert(2,string3); 
	list.insert(3,string4); 
	list.insert(4,string5); 
	list.insert(3,string6); 
	list.insert(4,string7); 
	list.insert(6,string9); 
	
	
	}
	
	
}
