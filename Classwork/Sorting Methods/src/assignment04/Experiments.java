// Make sure this class is in package assignment04

package assignment04;

import java.math.BigInteger;
import java.util.*;

/**
 * <p>
 * This Experiments class contains experimental code: static utility methods for
 * performing various sort operations, code for performing comparisons and
 * measuring performance, and code for building random data sets.
 * </p>
 * 
 * <p>
 * Students are not allowed to modify the existing public 'interface' to this
 * class. Public method headers, public variables, and method contracts may not
 * change. (In other words, don't break the tester.)  Additionally, this class
 * provides a few set-up methods for students to use, but our tests will not
 * involve these set-up methods. (See below.)
 * </p>
 * 
 * <p>
 * This class is designed for experimental purposes only - it is not designed
 * for use in larger projects. Thus, some coding decisions are not appropriate
 * for anything other than experimentation: modifiable public static variables,
 * public static helper methods, etc.
 * </p>
 * 
 * @author [ Ben Stewart ]
 * @version [ 10/19/2017 ]
 */
final public class Experiments  
{
	/*
	 * Students may place additional static classes, functions, and variables in
	 * this class (private or public). You may store experimental data in static
	 * variables, but you cannot use static variables in place of parameters,
	 * return values, or local variables. Don't move values between method calls
	 * in static variables, and don't use them for things like loop counters or
	 * temporary values.
	 */


	/* Do not replace this constructor/code.  No other constructor allowed. */

	private Experiments () { throw new RuntimeException ("Cannot build Experiments objects - use the static methods."); }

	/* Required functions - Notice the use of a generic type parameter. */

	/**
	 * <p>
	 * Sorts (in ascending order using insertion sort) a range of data in in an
	 * array. The specified comparator is used to determine the relative
	 * ordering of pairs of elements.
	 * </p>
	 * 
	 * <p>
	 * The range of data is [startPos, endPos). (Remember, this mathematical
	 * syntax indicates that we include the element at startPos, but that the
	 * element at endPos is excluded. If you sort the range [4,8), elements 4,
	 * 5, 6, and 7 will be in ascending order.)
	 * </p>
	 * 
	 * <p>
	 * The sort is stable and in-place. There is no return value because the
	 * data array is passed by reference. (Both the caller and this method have
	 * a reference to the same array.)
	 * </p>
	 * 
	 * <p>
	 * This method performs no unnecessary or duplicate comparisons.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param data
	 *            an array of data elements
	 * @param startPos
	 *            the first position to sort (inclusive)
	 * @param endPos
	 *            the last position to sort (exclusive)
	 * @param comparator
	 *            the comparison function object to use to compare elements
	 */
	public static <T> void insertionSort (T[] data, int startPos, int endPos, Comparator<T> comparator)
	{

		if(data.length == 1 || data.length == 0  || data == null)
			return; 


		T holderValue = null;

		for(int idx = startPos + 1; idx <= endPos; idx ++ )
		{
			int idx2 = idx; 
			holderValue = data[idx2]; 
			while(count(comparator.compare(holderValue, data[idx2-1])) < 0 && idx2 > startPos) {
				
				data[idx2] = data[idx2 - 1];     
				idx2 -= 1;
				if(idx2 == 0 )
					break; 

			}

			data[idx2] = holderValue; 	

		}





	}

	/**
	 * <p>
	 * Partitions an array around a pivot using the quicksort partition
	 * algorithm. This method chooses the element at to be the pivot element.
	 * The final position of the pivot element is returned to the caller.
	 * </p>
	 * 
	 * <p>
	 * To ensure consistency, partitioning proceeds as follows:
	 * </p>
	 * 
	 * <ul>
	 * <li>The element at <idx>floor((startPos+endPos)/2)</idx> is selected as the
	 *     pivot.</li>
	 * <li>The pivot is swapped with the leftmost element. The pivot is now at
	 *     startPos.</li>
	 * <li>The remaining elements are partitioned into smaller and then larger
	 *     elements in a single pass (using the std. partition algorithm).</li>
	 * <li>The pivot is swapped with the rightmost smaller element, moving it to
	 *     the middle of the partitions.</li>
	 * </ul>
	 * 
	 * <p>As a double-check:  Partitioning these elements (2 9 3 7 8 6 4) would produce 
	 * (6 2 4 3 7 8 9).  </p>
	 * 
	 * <p>
	 * This method performs no unnecessary or duplicate comparisons and does not
	 * create any new arrays.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param data
	 *            an array of data elements
	 * @param startPos
	 *            the first position to partition (inclusive)
	 * @param endPos
	 *            the last position to partition (exclusive)
	 * @param comparator
	 *            the comparison function object to use to compare elements
	 * @return the position of the pivot element
	 */
	public static <T> int partition (T[] data, int startPos, int endPos, Comparator<T> comparator)
	{        
		int pivot = (startPos + endPos)/2; 
		T holderValue  = data[pivot]; 
		data[pivot] = data[startPos];
		data[startPos] = holderValue;
		pivot = startPos; 

		for(int idx = startPos+1; idx <= endPos; idx++)
		{
			if(count(comparator.compare( data[idx], data[pivot])) < 0)
			{

				T holderValue2 = data[idx]; 
				//move all the data down one, and insert the new data at start pos
				for(int idx2 = idx; idx2 > pivot ; idx2--)
					data[idx2] = data[idx2-1]; 		


				pivot++; 
				data[pivot - 1] = holderValue2; 
			}	

		}

		
		return pivot; 
	}

	/**
	 * <p>
	 * Sorts (in ascending order) a subarray. Quicksort is used if the size of
	 * the subarray &gt; cutoff, otherwise insertion sort is used.
	 * </p>
	 * 
	 * <p>
	 * This method uses the partition and insertionSort methods above. This
	 * method is recursive.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param data
	 *            an array of data elements
	 * @param startPos
	 *            the first position to sort (inclusive)
	 * @param endPos
	 *            the last position to sort (exclusive)
	 * @param comparator
	 *            the comparison function object to use to compare elements
	 * @param cutoff
	 *            the quicksort / insertion sort cutoff
	 */
	public static <T> void quicksortWithCutoff (T[] data, int startPos, int endPos, Comparator<T> comparator, int cutoff)
	{
		if(startPos == endPos)
			return; 

		//if(endPos - startPos == 1)




		if(startPos - endPos <= cutoff && cutoff > 1 ) {
			insertionSort(data,startPos,endPos,comparator); 
			return; 
		} 



		int partitionIndex = partition(data, startPos,endPos,comparator);




		//if(startPos != partitionIndex)
		quicksortWithCutoff(data,startPos,partitionIndex,comparator,cutoff); 

		if (partitionIndex != endPos)
			quicksortWithCutoff(data,partitionIndex+1,endPos,comparator,cutoff); 

	}	

	/**
	 * <p>
	 * Sorts (in ascending order) an array using quicksort.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param data
	 *            an array of data elements
	 * @param comparator
	 *            the comparison function object to use to compare elements
	 */
	public static <T> void quicksort (T[] data, Comparator<T> comparator)
	{
		//Don't see the point in writing everything again
		quicksortWithCutoff(data, 0, data.length-1, comparator, 1); 
	}

	/**
	 * <p>
	 * Sorts (in ascending order) an array. When the array is treated as smaller
	 * subarrays, quicksort is used if the size of a subarray &gt; cutoff,
	 * otherwise insertion sort is used on that subarray.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param data
	 *            an array of data elements
	 * @param comparator
	 *            the comparison function object to use to compare elements
	 * @param cutoff
	 *            the quicksort / insertion sort cutoff
	 */
	public static <T> void quicksortWithCutoff (T[] data, Comparator<T> comparator, int cutoff)
	{
		quicksortWithCutoff(data,0,data.length-1,comparator,cutoff);
	}


	/**
	 * <p>
	 * Merges two sorted subarrays (from a source array) into one larger sorted
	 * subarray (in a destination array).
	 * </p>
	 * 
	 * <p>
	 * It is assumed that there are two sublists that occupy adjacent elements
	 * in the source array. The first sublist is the elements [startPosA,
	 * startPosB), and the second sublist is [startPosB, endPosB). Each sublist
	 * contains elements in sorted order, but the first element of the second
	 * sublist may be smaller than the last element of the first sublist.
	 * </p>
	 * 
	 * <p>
	 * The first subArray (starting at startPosA) must have a non-zero size. The
	 * other subarray may have a zero size.
	 * </p>
	 * 
	 * <p>
	 * The elements are merged (in ascending sorted order) into a single sublist
	 * in the destination array. The destination sublist has the indices
	 * [startPosA, endPosB). The merge operation is stable. Equal elements from
	 * the source array will be in the same relative order in the destination
	 * arrray.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param source
	 *            the source array
	 * @param dest
	 *            the destination array
	 * @param startPosA
	 *            sublist start
	 * @param startPosB
	 *            sublist start/end
	 * @param endPosB
	 *            sublist end
	 * @param comparator
	 *            the comparison function object to use to compare elements
	 */
	public static <T> void twoWayMerge (final T[] source, final T[] dest, int startPosA, int startPosB, int endPosB, final Comparator<T> comparator)
	{

		//holder values for the merge 
		int idx1,idx2; 

		idx1 = startPosA;
		idx2 = startPosB; 

		for(int idx = startPosA; idx <= endPosB; idx++)
		{

			if(idx1 < startPosB &&  idx2 <= endPosB)
			{
				int result = count(comparator.compare(source[idx1], source[idx2])); 
				//selects the smaller of the two values 
				if(result < 0)
				{
					dest[idx] = source[idx1];
					idx1++;
				}
				else if(result > 0 )
				{
					dest[idx] = source[idx2];
					idx2++;
				}
				else if(result == 0) {
					dest[idx] = source[idx1];
					dest[idx+1] = source[idx2]; 
					idx1++; 
					idx2++; 
					idx++; 
				}
			}

			else if(idx1 >= startPosB && idx2 <= endPosB) {
				dest[idx] = source[idx2]; 
				idx2++;
			}
			else if(idx2 > endPosB && idx1 < startPosB )
			{
				dest[idx] = source[idx1];
			}
		}





	}

	/**
	 * <p>
	 * Merges three sorted subarrays (from a source array) into one larger
	 * sorted subarray (in a destination array).
	 * </p>
	 * 
	 * <p>
	 * It is assumed that there are three sublists that occupy adjacent elements
	 * in the source array. The first sublist is the elements [startPosA,
	 * startPosB), the second sublist is [startPosB, startPosC), and the third
	 * sublist is [startPosC, endPosC). Each sublist contains elements in sorted
	 * order, but the first element of any sublist may be smaller than the last
	 * element of any other sublist.
	 * </p>
	 * 
	 * <p>
	 * The first subArray (starting at startPosA) must have a non-zero size.
	 * The other subarrays may have a zero size.
	 * </p>
	 * 
	 * <p>
	 * The elements are merged (in ascending sorted order) into a single sublist
	 * in the destination array. The destination sublist has the indices
	 * [startPosA, endPosC). The merge operation is stable. Equal elements from
	 * the source array will be in the same relative order in the destination
	 * arrray.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param source
	 *            the source array
	 * @param dest
	 *            the destination array
	 * @param startPosA
	 *            sublist start
	 * @param startPosB
	 *            sublist start/end
	 * @param startPosC
	 *            sublist start/end
	 * @param endPosC
	 *            sublist end
	 * @param comparator
	 *            the comparison function object to use to compare elements
	 */
	public static <T> void threeWayMerge (final T[] source, final T[] dest, int startPosA, int startPosB, int startPosC, int endPosC, final Comparator<T> comparator)
	{

		//holder values for the merge 
		int idx1,idx2,idx3; 
		
		

		if(startPosA < source.length)
			idx1 = startPosA;
		else {
			idx1 = source.length - 1; 
			startPosA = idx1; 
		}
		if(startPosB < source.length)
			idx2 = startPosB; 
		else {
			idx2 = source.length -1;
			startPosB = idx2; 
		}
		if(startPosC < source.length)
			idx3 = startPosC;
		else {
			idx3 = source.length -1; 
			startPosC = idx3; 
		}

		int smallestResult = 0; 
		for(int idx = startPosA; idx <= endPosC; idx++)
		{

			//if all three arrays still have values left 
			if(idx1 < startPosB && idx2 < startPosC && idx3 <= endPosC)
			{
				int result = count(comparator.compare(source[idx1], source[idx2]));

				if(result < 0 || result == 0 ) {
					int res = count(comparator.compare(source[idx1], source[idx3]));
					if( res < 0 || res == 0)
					{
						smallestResult = 1; 
					}
					else if(res > 0)
					{
						smallestResult = 3;
					}
					if(result == 0 && res == 0)
					{
						smallestResult = 4; 
					}

				} 
				else if(result > 0) {
					int res = count(comparator.compare(source[idx2], source[idx3]));
					if( res < 0 || res == 0)
					{
						smallestResult = 2; 
					}
					else if(res > 0)
					{
						smallestResult = 3;
					}
				} 


			}

			//if one of the sub arrays has gone all the way through
			else if(idx1 >= startPosB && idx2 < startPosC && idx3 <= endPosC)
			{
				int res = count(comparator.compare(source[idx2], source[idx3]));
				if( res < 0 || res == 0)
				{
					smallestResult = 2; 
				}
				else if(res > 0)
				{
					smallestResult = 3;
				}

			}

			else if(idx1 < startPosB && idx2 >= startPosC && idx3 <= endPosC)
			{
				int res = count(comparator.compare(source[idx1], source[idx3]));
				if( res < 0 || res == 0)
				{
					smallestResult = 1; 
				}
				else if(res > 0)
				{
					smallestResult = 3;
				}

			}

			else if(idx1 < startPosB && idx2 < startPosC && idx3 > endPosC)
			{
				int res = count(comparator.compare(source[idx1], source[idx2]));
				if( res < 0 || res == 0)
				{
					smallestResult = 1; 
				}
				else if(res > 0)
				{
					smallestResult = 2;
				}

			}
			//if two of the subarrays are done

			else if(idx1 >= startPosB && idx2 >= startPosC && idx3 <= endPosC)
			{
				smallestResult = 3;
			}
			else if(idx1 >= startPosB && idx2 < startPosC && idx3 > endPosC)
			{
				smallestResult = 2;
			}
			else if(idx1 < startPosB && idx2 >= startPosC && idx3 > endPosC)
			{
				smallestResult = 1;
			}



			switch (smallestResult)
			{
			case 1:
				dest[idx] = source[idx1];
				idx1++;
				break; 
			case 2:
				dest[idx] = source[idx2];
				idx2++;
				break; 
			case 3:
				dest[idx] = source[idx3];
 				idx3++;
				break;
			case 4: 
				dest[idx] = source[idx1];
				idx1++;
				idx++; 
				dest[idx] = source[idx2];
				idx2++;
				idx++;  
				dest[idx] = source[idx3];
				idx3++; 
				idx++;
				break; 


			}




		}
	}

	/**
	 * <p>
	 * Mergesorts (via two-way splits and merges) the data array into ascending
	 * order.
	 * </p>
	 * 
	 * <p>
	 * This function guarantees non-recursive behavior, and guarantees that it
	 * only creates a single additional array, and that the additional array is
	 * the same length as the original data array.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param data
	 *            an array of elements
	 * @param comparator
	 *            the comparison function object to use to compare elements
	 */
	public static <T> void twoWayMergesort (T[] data, Comparator<T> comparator)
	{
		@SuppressWarnings("unchecked")
		T destinationArray[] = (T[]) new Object[data.length];
		int subArraySize;
		//iterates and increases until the expected subarray size < 1; 
		for(subArraySize = 1;subArraySize < data.length ;subArraySize = 2*subArraySize)
		{
			for(int j = 0; j < data.length; j = j + 2*subArraySize)
			{
				int startA,startB,endB; 
				
				startA = j;
				if(j + subArraySize < data.length)
					startB = j + subArraySize; 
				else 
					startB = data.length - 1; 
			
				if(startB + subArraySize < data.length)
					endB = startB + subArraySize - 1; 
				else 
					endB = data.length - 1; 
				
				twoWayMerge(data, destinationArray,startA,startB,endB,comparator); 
					
			
			}
			for(int idx = 0; idx < data.length; idx++ )
				data[idx] = destinationArray[idx]; 
			
		}
		
		
		
		
	}

	/**
	 * <p>
	 * Mergesorts (via three-way splits and merges) the data array into
	 * ascending order.
	 * </p>
	 * 
	 * <p>
	 * This function guarantees non-recursive behavior, and guarantees that it
	 * only creates a single additional array, and that the additional array is
	 * the same length as the original data array.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param data
	 *            an array of elements
	 * @param comparator
	 *            the comparison function object to use to compare elements
	 */
	public static <T> void threeWayMergesort (T[] data, Comparator<T> comparator)
	{
		@SuppressWarnings("unchecked")
		T destinationArray[] = (T[]) new Object[data.length];
		int subArraySize;
		//iterates and increases until the expected subarray size < 1; 
		for(subArraySize = 1;subArraySize < data.length ;subArraySize = 3*subArraySize)
		{
			for(int j = 0; j < data.length; j = j + 3*subArraySize)
			{
				int startA,startB,startC,endC; 
				
				startA = j;
				startB = j + subArraySize; 
				startC = startB + subArraySize; 
				if(startC + subArraySize < data.length)
					endC = startC + subArraySize - 1; 
				else 
					endC = data.length - 1; 
				threeWayMerge(data, destinationArray,startA,startB,startC,endC,comparator); 
					
			
			}
			for(int idx = 0; idx < data.length; idx++ )
				data[idx] = destinationArray[idx]; 
			
		}
		
		
	}

	/* Utility functions for testing. */

	private static String[] words = {"alpha", "beta", "gamma", "delta", "omega", "sigma", "bob"};

	/**
	 * <p>
	 * Makes an array of random strings. The only guarantee is that strings are
	 * not null.  (Students may not rely on the random distributions given in this method.)
	 * </p>
	 * 
	 * @param size
	 *            the desired array size
	 * @param seed
	 *            the random seed to use
	 * @return an array of random strings
	 */
	public static String[] generateMixedStrings (int size, long seed)
	{
		Random r = new Random(seed);

		// Regardless of the final array size, generate 1000 strings.

		String[] elements = new String[1000];

		for (int idx = 0; idx < elements.length - 10; idx++)
		{
			elements[idx] = "";
			while (elements[idx].length() == 0 || r.nextBoolean())
				if (r.nextBoolean())
					elements[idx] += words[r.nextInt(words.length)];
				else
					elements[idx] += r.nextInt(99)+1;
		}

		// Make sure there are a few duplicates but with different object references.

		for (int idx = elements.length - 10; idx < elements.length; idx++)
			elements[idx] = new String(elements[r.nextInt(elements.length - 10)]);

		// Build the array, populate it with choices from the 1000 prebuilt strings.
		//   (This helps keep the memory footprint smaller, and it increases the
		//   likelihood of duplicates in the final data.)

		String[] result = new String[size];

		for (int idx = 0; idx < size; idx++)
			result[idx] = elements[r.nextInt(elements.length)];

		return result;
	}

	/**
	 * <p>
	 * Determines the relative order of two strings with embedded numbers.
	 * If a &lt; b, -1 is returned.  If a &gt; b, 1 is returned.  If a == b, 0 is returned.
	 * </p>
	 * 
	 * @param a a string that may contain an embedded number
	 * @param b another string that may contain an embedded number
	 * @return -1, 0, or 1 if a is less than, equal to, or greater than b
	 */
	public static int compareMixedStrings (String a, String b)
	{
		// Students are not allowed to change this code.  It is inefficient on 
		//   purpose.

		if (a.equals(b))
			return 0;

		int aPos = 0;
		int bPos = 0;

		while (true)
		{
			if (aPos < a.length() && bPos == b.length())
				return 1;
			if (aPos == a.length() && bPos < b.length())
				return -1;

			if (Character.isDigit(a.charAt(aPos)) && !Character.isDigit(b.charAt(bPos)))
				return -1;
			if (!Character.isDigit(a.charAt(aPos)) && Character.isDigit(b.charAt(bPos)))
				return 1;

			if (!Character.isDigit(a.charAt(aPos)) && a.charAt(aPos) < b.charAt(bPos))
				return -1;
			if (!Character.isDigit(a.charAt(aPos)) && a.charAt(aPos) > b.charAt(bPos))
				return 1;
			if (!Character.isDigit(a.charAt(aPos)) && a.charAt(aPos) == b.charAt(bPos))
			{
				aPos++;
				bPos++;
				continue;
			}

			String aDigits = "", bDigits = "";

			while (aPos < a.length() && Character.isDigit(a.charAt(aPos)))
				aDigits += a.charAt(aPos++);

			while (bPos < b.length() && Character.isDigit(b.charAt(bPos)))
				bDigits += b.charAt(bPos++);

			BigInteger aInteger = new BigInteger(aDigits);
			BigInteger bInteger = new BigInteger(bDigits);

			int result = aInteger.compareTo(bInteger);

			if (result != 0)
				return result;
		}        
	}

	/**
	 * <p>
	 * Makes an array of random integers. The only guarantee is that integers are
	 * not null.  (Students may not rely on the random distributions given in this method.)
	 * </p>
	 * 
	 * @param size
	 *            the desired array size
	 * @param seed
	 *            the random seed to use
	 * @return an array of random integers
	 */
	public static Integer[] generateIntegers (int size, long seed)
	{
		Random r = new Random(seed);

		Integer[] result = new Integer[size];

		for (int idx = 0; idx < size; idx++)
			result[idx] = r.nextInt(9000)+1000;

		return result;
	}

	/* A bit of code to track comparisons */

	// Incremented every time a comparator is used within this class.

	public static long comparisonCount;

	/**
	 * <p>
	 * Increments the comparisonCount, then returns it's parameter. Thus, this
	 * function can wrap calls to a comparator to simplify counting comparisons:
	 * </p>
	 * 
	 * <pre>
	 * comparator.compare(x, y);
	 * </pre>
	 * 
	 * <p>
	 * becomes
	 * </p>
	 * 
	 * <pre>
	 * count(comparator.compare(x, y));
	 * </pre>
	 * 
	 * 
	 * @param v
	 *            any int
	 * @return the same int
	 */
	private static int count (int v)
	{
		comparisonCount++;
		return v;
	}

	//resets comparison count to 0 for testing purposes
	public static void resetCount() 
	{
		comparisonCount = 0; 
	}
	
	/**
	 * <p>
	 * A method for testing if sorted data is correct and matches unsorted data.
	 * </p>
	 * 
	 * <p>
	 * The implementation of this method is left somewhat uncommented / vague.
	 * It is not intended to replace a student's own investigations / testing.
	 * It will tell you something is wrong, but it is up to you to discover the
	 * details of your errors.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param unsorted
	 *            the unsorted array
	 * @param sorted
	 *            the sorted array
	 * @param comparator
	 *            the comparator to use for checking order
	 * @param checkStability
	 *            true if this method should check for sort stability
	 */
	public static <T> void sortCheck (T[] unsorted, T[] sorted, Comparator<T> comparator, boolean checkStability)
	{
		// Did the sort work?

		for (int idx = 1; idx < sorted.length; idx++)
			if (comparator.compare(sorted[idx-1], sorted[idx]) > 0)
				throw new RuntimeException ("Elements out of order at positions " + (idx-1) + " and " + idx + ": " + sorted[idx-1] + " " + sorted[idx]);                        

		// Check for unmatched elements.

		IdentityHashMap<Object,Object> unsortedElements = new IdentityHashMap<Object,Object>();
		IdentityHashMap<Object,Object> sortedElements = new IdentityHashMap<Object,Object>();

		for (T e : unsorted)
			unsortedElements.put(e, null);

		for (T e : sorted)
		{
			if (!unsortedElements.containsKey(e))
				throw new RuntimeException ("An unknown element has appeared in the sorted array: " + e);        
			sortedElements.put(e, null);
		}       

		for (T e : unsorted)
		{
			if (!sortedElements.containsKey(e))
				throw new RuntimeException ("At least one copy of an element reference does not appear in the sorted array: " + e);
		}       

		// Check for stability.  
		// Note that it is not possible to test stability on equal references.  A side effect
		//   of this test is that missing/duplicate elements will be detected.

		if (checkStability)
		{
			Map<T,Set<Integer>> unsortedLocations = new HashMap<T,Set<Integer>>();
			Map<T,Set<Integer>> sortedLocations = new HashMap<T,Set<Integer>>();

			// Build sets of locations for equal values.

			for (int idx = 0; idx < unsorted.length; idx++)
			{
				if (!unsortedLocations.containsKey(unsorted[idx]))
					unsortedLocations.put(unsorted[idx], new TreeSet<Integer>());
				unsortedLocations.get(unsorted[idx]).add(idx);
			}

			for (int idx = 0; idx < sorted.length; idx++)
			{
				if (!sortedLocations.containsKey(sorted[idx]))
					sortedLocations.put(sorted[idx], new TreeSet<Integer>());
				sortedLocations.get(sorted[idx]).add(idx);
			}

			// Compare references at locations in increasing location order.  The
			//   references should be identical.

			for (T equalElement : unsortedLocations.keySet())
			{
				Iterator<Integer> unsortedPositionIterator = unsortedLocations.get(equalElement).iterator();
				Iterator<Integer> sortedPositionIterator   = sortedLocations.get(equalElement).iterator();

				while (unsortedPositionIterator.hasNext() || sortedPositionIterator.hasNext())
				{
					if (!unsortedPositionIterator.hasNext())
						throw new RuntimeException ("At least one copy of an element reference has been duplicated in the sorted array: " + equalElement+ " at sorted position " + sortedPositionIterator.next());
					if (!sortedPositionIterator.hasNext())
						throw new RuntimeException ("At least one copy of an element reference does not appear in the sorted array: " + equalElement + " at unsorted position " + unsortedPositionIterator.next());
					int unsortedPos = unsortedPositionIterator.next();
					int sortedPos = sortedPositionIterator.next();
					if (unsorted[unsortedPos] != sorted[sortedPos])
						throw new RuntimeException ("An element sorted unstably:  Unsorted:  " + unsorted[unsortedPos] + " at " + unsortedPos + "  Sorted: " + sorted[sortedPos] + " at " + sortedPos);                    
				}
			}                        
		}
	}

}
