package lab06;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//import CS 2420 Fall 2017 Assignment 03.assignment03; 


public class IteratorPractice {

public static void main(String[] args)
{
	List<Integer> list = Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9}); 

	for(int e : list)
	{
		list.remove(0); 
	}
	System.out.println(list);
	
}
	
	





public <T> Iterator<T> iterator() {
	return new Iterator<T>()
			{

				@Override
				public boolean hasNext() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public E next() {
					// TODO Auto-generated method stub
					return null;
				}
		
			};
	
	
}
}
