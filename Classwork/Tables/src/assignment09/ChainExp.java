package assignment09;

import java.io.FileWriter;
import java.io.IOException;





public class ChainExp {
	public static void main(String args[])
	{
		HashNode[] table;
		int probes = 0; 
		int insertions = 0; 
		double load = 0;
		int id = 8689; 
		try {
			FileWriter fw = new FileWriter("src/Experiment03.csv");
			int stuff= 0; 
			table = new HashNode[id];
			for(int i = 0; i < id; i++)
			{
				Double value = new Double(Math.random() * id);
				int hash = value.intValue(); 
				HashNode ins =  new HashNode(value, hash); 
				// insert into table
				int index = hash;
				boolean up = true; 
				probes = 0;
				if(table[hash] == null) {
					probes++;
					table[hash] = ins;
					stuff++; 
				}
				else {
					probes++; 
					HashNode head = table[hash];
					HashNode nextNode = head.prev;
					head.prev = ins;
					ins.next = head; 
					if(nextNode != null)
					{
						ins.prev = nextNode; 
						nextNode.next = ins; 
					}
					
					
							
				}
				load = (double)stuff/(double)id; 
				
				System.out.println(load);
				fw.write(load + "," + probes + "\n");
			}
			
			fw.close(); 
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	private static class HashNode
	{
		Double data; 
		int hash; 
		HashNode prev = null; 
		HashNode next = null; 
		public HashNode(Double data, int hash)
		{
			this.data = data; 
			this.hash = hash; 
		}
	}

}
