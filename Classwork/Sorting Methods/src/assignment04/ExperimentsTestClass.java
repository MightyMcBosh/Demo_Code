package assignment04;

import java.util.Comparator;

public class ExperimentsTestClass {
	
	//just a quick java program to run experiemtns
	
	
	public static void main(String[] args)

	{
	
		for(int i = 0; i < 100; i++)
		{
		
		String test1[] = new String[1023]; 
		Double test2[] = new Double[100]; 
		Double unsorted[] = new Double[100]; 
		Double test3[] = new Double[1]; 
		Double test4[] = new Double[100];  
		for(int e = 0; e<100; e++)
		{
			test2[e] = Math.random() * 10; 
		//	System.out.println(test2[e]); 
		}
		
		unsorted = test2; 
		test3[0] = Math.random() * 20; 
		
		System.out.println("/n ---------SORTed --------------"); 
		@SuppressWarnings("unchecked")
		Comparator<Double> comparator = (x,y) -> x.compareTo(y); //new TestDoubleComparator<Double>(); 
		Comparator<String> comparator2 = (x,y) -> x.compareTo(y); //new TestStringComparator<String>(); 
		long val1 = Experiments.comparisonCount; 
		Experiments.insertionSort(test2, 0, 99, comparator); 
		///Experiments.quicksort(test2,comparator);
		Experiments.sortCheck(unsorted , test2, comparator, true); 
		long val2 = Experiments.comparisonCount; 
		
		long comparisons = val2 - val1; 
		
		System.out.println(comparisons); 
		
		
//		test1 = Experiments.generateMixedStrings(1023, 25); 
//		String[] unsortedTest1 = test1;
//		Experiments.quicksortWithCutoff(test1,comparator2,5);
//		Experiments.sortCheck(unsortedTest1 , test1, comparator2, false); 
		
		for(int e = 0; e<100; e++)
		{
			test2[e] = Math.random() * 10; 
			//System.out.println(test2[e]); 
		}
		unsorted = test2; 
		
	////////////////////////////////////////////////////////////////////////////
		val1 = Experiments.comparisonCount;
		//Experiments.quicksort(test2, comparator);
		Experiments.quicksortWithCutoff(test2,comparator,5);
		
		val2 = Experiments.comparisonCount; 
		comparisons = val2 - val1; 
		Experiments.resetCount(); 
		System.out.println(comparisons);
		Experiments.sortCheck(unsorted , test2, comparator, false); 
		
		for(int e = 0; e<100; e++)
		{
			test2[e] = Math.random() * 10; 
			//System.out.println(test2[e]); 
		}
		unsorted = test2; 
	////////////////////////////////////////////////////////////////////////////
		
		val1 = Experiments.comparisonCount;
		//Experiments.quicksort(test2, comparator);
		Experiments.quicksort(test2,comparator);
		
		val2 = Experiments.comparisonCount; 
		comparisons = val2 - val1; 
		Experiments.resetCount(); 
		System.out.println(comparisons);
		Experiments.sortCheck(unsorted , test2, comparator, false); 
		
		for(int e = 0; e<100; e++)
		{
			test2[e] = Math.random() * 10; 
			//System.out.println(test2[e]); 
		}
		unsorted = test2; 
		
		
		
		val1 = Experiments.comparisonCount;
		//Experiments.quicksort(test2, comparator);
		Experiments.twoWayMergesort(test2,comparator);
		
		val2 = Experiments.comparisonCount; 
		comparisons = val2 - val1; 
		Experiments.resetCount(); 
		System.out.println(comparisons);
		Experiments.sortCheck(unsorted , test2, comparator, true); 
		
		for(int e = 0; e<100; e++)
		{
			test2[e] = Math.random() * 10; 
			//System.out.println(test2[e]); 
		}
		
		unsorted = test2; 
		
		
		val1 = Experiments.comparisonCount;
		//Experiments.quicksort(test2, comparator);
		Experiments.threeWayMergesort(test2,comparator);
		
		val2 = Experiments.comparisonCount; 
		comparisons = val2 - val1; 
		Experiments.resetCount(); 
		System.out.println(comparisons);
		Experiments.sortCheck(unsorted , test2, comparator, true); 
		
	
		
		test1 = Experiments.generateMixedStrings(10, 25); 
		String[] unsortedTest1 = test1;
		 val1 = Experiments.comparisonCount;
		Experiments.twoWayMergesort(test1,comparator2);
		val2 = Experiments.comparisonCount;
		comparisons = val2 - val1; 
		
		System.out.println(comparisons); 
		Experiments.sortCheck(unsortedTest1 , test1, comparator2, true); 
		}
		
		
		
		
		
		
		System.out.println("Sorted!"); 
	//	Experiments.partition(test3,0,0,comparator);		
		
		
		//System.out.println(test3[0]);
	
	
	

	}
	//only use doubles for this comparator
	@SuppressWarnings("hiding")
	public static class TestDoubleComparator<T> implements Comparator<T>
	{
		

		@Override
		public int compare(Object o1, Object o2) {
			
			Double holderA = (Double) o1; 
			Double holderB = (Double) o2; 
			
			if(holderA < holderB)
				return 1; 
			
			if(holderB < holderA)
				return -1; 
			
			if(holderB.equals(holderA))
				return 0;
		
			
			return 0; 
		
		}

		
	}
	
	public static class TestStringComparator<T> implements Comparator<T>
	{
		

		@Override
		public int compare(Object o1, Object o2) {
			
			String holderA = (String) o1; 
			String holderB = (String) o1; 
			
			return holderA.compareTo(holderB); 
		
			
		
		
		}

		
	}

}
