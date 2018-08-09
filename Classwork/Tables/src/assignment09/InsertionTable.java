package assignment09;

import java.io.FileWriter;
import java.io.IOException;



public class InsertionTable {
	public static void main(String args[])
	{
		InsPoint[] table;
		int probes = 0; 
		int insertions = 0; 
		double load = 0;
		int id = 8689; 
		try {
			FileWriter fw = new FileWriter("src/Experiment02.csv");
			
			table = new InsPoint[id];
			//0 % load
			for(int i = 0; i < id; i++)
			{
				Double value = new Double(Math.random() * id);
				int hash = value.intValue(); 
				InsPoint ins =  new InsPoint(value, hash); 
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
	private static class InsPoint
	{
		int hash;
		Double data; 
		public InsPoint(Double data, int hash)
		{
			this.data = data; 
			this.hash = hash; 
		}
	}

}
