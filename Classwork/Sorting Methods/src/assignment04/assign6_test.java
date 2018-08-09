package assignment04;

import java.util.Comparator;

public class assign6_test {
 static public void main(String[] args)
 {
	Double test[]; 
	test = new Double[] {1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0}; 
	long values[] = new long [100]; 
	
	for(int i = 0; i < values.length;  i++ )
	{ 
		for(double k : test)
			k = (Math.random() * 10); 
			
			
	Comparator<Double> comparator = (x,y) -> x.compareTo(y); //new TestStringComparator<String>(); 
	long val1 = Experiments.comparisonCount; 
	Experiments.insertionSort(test, 0,9, comparator); 
	///Experiments.quicksort(test2,comparator);
	
	long val2 = Experiments.comparisonCount; 
	
	long comparisons = val2 - val1; 
	
	System.out.println(comparisons); 
	}
 }
}
