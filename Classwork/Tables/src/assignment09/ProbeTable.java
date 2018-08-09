package assignment09;

import java.io.FileWriter;
import java.io.IOException;

public class ProbeTable {
	
	
	public static void main(String args[])
	{
		HashPoint[] table;
		int probes = 0; 
		int insertions = 0; 
		double load = 0;
		int id = 8689; 
		try {
			FileWriter fw = new FileWriter("src/Experiment01.csv");
			
			table = new HashPoint[id];
			for(int i = 0; i < id; i++)
			{
				Double value = new Double(Math.random() * id);
				int hash = value.intValue(); 
				HashPoint ins =  new HashPoint(value, hash); 
				// insert into table
				int index = hash;
				boolean up = true; 
				probes = 1;
				while(table[index] != null)
				{
					
					if(up)
						index++;
					else
						index--; 
					if(index == table.length)
					{
						up = false;
						index = hash - 1; 
					}
					
					probes++; 
				}
				table[index] = ins;
				load=(double)i/(double)id;  
				System.out.println(load);
				fw.write(load + "," + probes + "\n");
			}
		
			fw.close(); 
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


	
	//helper class to hold data
	private static class HashPoint
	{
		int hash;
		Double data; 
		public HashPoint(Double data, int hash)
		{
			this.data = data; 
			this.hash = hash; 
		}
	}
}
