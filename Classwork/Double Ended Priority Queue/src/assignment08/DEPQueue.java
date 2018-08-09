package assignment08;



/*
 * This class is for a double ended priority queue. IT is contained within a max heap and a min heap, stored in arrays.
 * An internal node class maintains the data and its respective locations in the queue.
 * 
 *  @author Benjamin Stewart
 *  @date 11/29/2017
 * 
 */
public class DEPQueue{

	//Fields 
	private long comparisons; 
	private long swaps; 
	private Node[] maxQ; 
	private Node[] minQ; 
	private int size; 

	//constructor

	public DEPQueue()
	{
		//picked some arbitrary starting value
		minQ = new Node[5];  
		maxQ = new Node[5]; 
		comparisons = 0; 
		swaps = 0; 
		size = 0; 
	}

	//Methods

	/*
	 * Inserts a value into the queue. 
	 * @param data the data to be inserted into the queue. 
	 */
	public void insert(String data) {
		//only using one array cause i will design the arrays to be equal in length
		if(size == maxQ.length)
		{
			engageGrowthRay(); 
		}
		size++; 

		Node newNode = new Node(data,size-1,size-1); 
		minQ[size-1] = newNode; 
		maxQ[size-1] = newNode; 
		//now fix the heap
		minHeapUpward(size-1);
		maxHeapUpward(size-1); 


	}

	//grows the arrays that hold the heaps to make room for more nodes if necessary
	private void engageGrowthRay()
	{
		//creates bigger array to hold nodes if they need it
		//Borrowing from the ArrayList growth scheme
		Node newMax[] = new Node[maxQ.length * 3 / 2];
		Node newMin[] = new Node[maxQ.length * 3 / 2];
		for(int idx = 0; idx < size; idx++)
		{
			newMax[idx] = maxQ[idx];
			newMin[idx] = minQ[idx];
		}
		minQ = newMin; 
		maxQ = newMax; 
	}



	/*
	 * Ensures that a that an element inserted into the minimum heap at the end is dragged up into the heap where it needs to go
	 *  @param index the subtree that starts at that index
	 */
	private void minHeapUpward(int index)
	{
		if(size <= 1)
			return; 

		int parent = 0; 
		//if a child is even, it is a right child. if it is odd, it is a left child.
		if(index%2 == 0)
			parent = (index - 2)/2;
		if(index%2 == 1)
			parent = (index - 1)/2;
		//Runs until the node is at its proper place in the tree
		while(parent >= 0 && compare(minQ[index].data, minQ[parent].data) < 0){
			swap(minQ, parent, index); 
			index = parent; 
			if(index%2 == 0)
				parent = (index - 2)/2;
			if(index%2 == 1)
				parent = (index - 1)/2;
		}

	}
	/*
	 * Ensures that a that an element inserted into the minimum heap at the end is pushed down into the heap where it needs to go
	 *  
	 */
	private void minHeapDownward(int index)
	{
		if(size <= 1)
			return; 
		int left = 2*index + 1; 
		int smallest;
		int right = 2*index + 2;

		//Compare left side, then right side. left side is an earlier index, so we want that comparison first.

		if(left < size && compare(minQ[index].data,minQ[left].data) > 0)
		{
			smallest = left;
		}
		else
		{
			smallest = index; 
		}


		if(right < size && compare(minQ[smallest].data,minQ[right].data) > 0)
		{
			smallest = right; 
		}
		//if one of the children is larger than the parent, swap indices and positions.
		if(smallest != index)
		{

			swap(minQ,smallest,index);
			//Now we propagate through the rest of the branch. Couldnt think of a condition that wouldn't run additional comparisons,
			//so I used recursion even though it is a hair slower.
			minHeapDownward(smallest); 
		}
	}


	/*
	 * Ensures that a that an element inserted into the maximum heap at the end is dragged up into the heap where it needs to go
	 *  
	 */
	private void maxHeapUpward(int index)
	{
		if(size <= 1)
			return; 

		int parent = 0; 
		//if a child is even, it is a right child. if it is odd, it is a left child.
		if(index%2 == 0)
			parent = (index - 2)/2;
		if(index%2 == 1)
			parent = (index - 1)/2;

		while(parent >= 0 && compare(maxQ[index].data, maxQ[parent].data) > 0){
			swap(maxQ, parent, index); 
			index = parent; 
			if(index%2 == 0)
				parent = (index - 2)/2;
			if(index%2 == 1)
				parent = (index - 1)/2;
		}
	}
	/*
	 * Ensures that a that an element inserted into the maximum heap at the root is pushed into the heap where it needs to go
	 *  
	 */
	private void maxHeapDownward(int index)
	{
		if(size <= 1)
			return; 
		int left = 2*index + 1; 
		int largest;
		int right = 2*index + 2;
		//Compare left side, then right side. left side is an earlier index, so we want that comparison first.

		if(left < size && compare(maxQ[index].data,maxQ[left].data) < 0)
		{
			largest = left;
		}
		else
		{
			largest = index; 
		}


		if(right < size && compare(maxQ[largest].data,maxQ[right].data) < 0)
		{
			largest = right; 
		}
		//if one of the children is larger than the parent, swap indices and positions.
		if(largest != index)
		{

			swap(maxQ,largest,index);
			//Now we propagate through the rest of the branch. Couldnt think of a condition that wouldn't run additional comparisons.  
			maxHeapDownward(largest); 
		}
	}




	/*
	 * helper method to swap two strings in an array, and increment a swap counter. 
	 * @params arr: the array in which the two strings are 
	 * idx1: index of the first string
	 * idx2: index of the second string 
	 */
	private void swap(Node[] arr, int idx1, int idx2)
	{
		Node holder;
		//CHange index depending on which array it is
		if(arr == minQ)
		{
			arr[idx2].minIndex = idx1; 
			arr[idx1].minIndex = idx2;
		}
		else if(arr == maxQ)
		{
			arr[idx2].maxIndex = idx1; 
			arr[idx1].maxIndex = idx2;
		}
		//Swap values
		holder = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = holder;
		//Increment swaps
		swaps++; 
	}

	/*
	 * helper method to compare two strings and increment comparison count. to be used 
	 * in place of compareTo so comparisonCount never fails to be implemented due to oversight.
	 * 
	 *  @param a, b the strings to be compared
	 */
	private int compare(String a, String b)
	{
		comparisons++; 
		return a.compareTo(b); 
	}

	/*
	 * Returns the number of comparisons made. 
	 * @return number of comparisons 
	 */
	public long getComparisonCount() {

		return comparisons;
	}


	/*
	 *Gets the number of swaps made in the queue.
	 *@return number of swaps 
	 */
	public long getSwapCount() {

		return swaps;
	}

	/*
	 * returns the size of the array
	 */
	public int size() {

		return size;
	}
	/*
	 * removes the smallest object in the heap from both arrays, 
	 * then re-heaps them.
	 * @return String the data from the removed object.
	 */
	public String removeMin() {
		if(size>0)
		{
			Node min = minQ[0]; 
			//remove the node from the arrays
			remove(minQ,0);
			remove(maxQ,min.maxIndex);
			size--;
			//The remove function puts the last node in the array at the index in the . This
			//pushes the node to its proper place in the tree. 
			minHeapDownward(0);
			if(min.maxIndex != size) 

				maxHeapUpward(min.maxIndex); 

			return min.data;
		}
		return null; 
	}
	/*
	 * removes the largest node in the heap. 
	 * @returns String the data. Functionally similar to the previous, just mirrored.
	 */
	public String removeMax() {
		if(size > 0)
		{
			Node max = maxQ[0]; 

			remove(maxQ,0);
			remove(minQ,max.minIndex);
			size--; 
			maxHeapDownward(0);
			if(max.minIndex != size)
				minHeapUpward(max.minIndex); 

			return max.data;
		}
		return null; 
	}

	/*
	 * Helper method to remove a Node from an array
	 */
	private void remove(Node[] arr, int index)
	{
		if(size == 0 )
			return; 
		// if the node is the last one in the array, just delete it and replace it with null.
		if(index == size - 1)
		{
			arr[index] = null;
		}
		else
		{
			/*
			 * Replaces the node in question with the last node in the tree, and set the end of the tree to null. 
			 * Size is adjusted outside of this method.
			 * Increments a swap, then sets the indices in the node to the proper value depending on which array is getting edited.
			 */
			arr[index] = arr[size - 1]; 
			swaps++; 
			arr[size-1] = null;

			if(arr == maxQ)
				arr[index].maxIndex = index; 
			else
				arr[index].minIndex = index; 
		}

	}

	/*
	 * Wrapper class to hold indices in the min heap and max heap. Ensures O(log n) complexity by ensuring the 
	 * class doesnt have to search through arrays to find a node. 
	 */
	private class Node
	{
		int maxIndex;
		int minIndex; 
		String data; 


		public Node(String data, int min, int max) 
		{
			this.data = data;
			this.maxIndex = max; 
			this.minIndex = min; 
		}
	}

}
