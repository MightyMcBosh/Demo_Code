package assignment04;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

public class Assn04WrittenReportStuff {



	public static void main(String[] args)
	{



		Comparator<String> comparator = (x,y) -> x.compareTo(y);

		//testLilQuicksort(comparator); 
		testBigQuicksort(comparator);
		



	}


	public static void testLilQuicksort(Comparator<String> t)
	{
		try{
			FileWriter	quick = new FileWriter("src/quickSort130.tsv");
			for(int i = 1; i <= 30; i++)
			{
				Experiments.resetCount();
				String[] testStrings = Experiments.generateMixedStrings(i, (int)(Math.random()*(10000)));
				Experiments.quicksort(testStrings,t);
				long numCounts = Experiments.comparisonCount; 
				quick.write(numCounts + "\r\n");
				
				
			}
			quick.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void testBigQuicksort(Comparator<String> t)
	{
		try{
			FileWriter	fw = new FileWriter("src/bigQuicksort.tsv");
			for(int i = 1000; i <= 100000; i = i + 1000)
			{
				Experiments.resetCount();
				String[] testStrings = Experiments.generateMixedStrings(i, (int)(Math.random()*(10000)));
				Experiments.quicksort(testStrings,t);
				long numCounts = Experiments.comparisonCount; 
				fw.write(numCounts + "\r\n");
				if(i%10000 == 0)
					System.out.println(i); 
				
			}
			fw.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

}
