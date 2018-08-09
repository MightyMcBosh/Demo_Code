package assignment03;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * @author Benjamin Stewart
 */
public class MyLinkedList<T> implements UtahList<T>,Iterable<T> {

	private Node<T> beginning; 
	private int chainLength = 1; 
	private int modCount = 0; 

	public MyLinkedList()
	{
		beginning = new Node<T>(null);
		modCount++; 
	}
	//Methods from Utah list

	@Override
	public T getElement(int index) {
		// TODO Auto-generated method stub


		Node<T> target = walk(index); 

		return (T) target.data; 
	}

	@Override
	public void setElement(int index, T data) {
		Node<T> target = walk(index);
		target.data = data; 

	}


	@Override
	public void insert(int index, T data) {
		if(index > chainLength || index < 0)
			throw new ArrayIndexOutOfBoundsException("IndexOutOfBounds");

		Node<T> newNode = new Node<T>(data); 

		if(index == chainLength)
		{
			Node<T> lastNode = walk(chainLength - 1); 
			lastNode.next = newNode; 
			newNode.prev = lastNode; 
			modCount += 2;
			chainLength += 1; 
			return; 
		}

		Node<T> Target = walk(index); 




		newNode.next = Target; 
		modCount+=3;
		if(Target.prev != null)
		{
			newNode.prev = Target.prev;
			Target.prev.next  =  newNode; 
			Target.prev = newNode; 
			modCount+=3; 
		}

		chainLength += 1; 

	}

	@Override
	public T remove(int index) {
		if(index >= chainLength || index < 0)
			throw new ArrayIndexOutOfBoundsException("index out of bounds"); 

		Node<T> target = walk(index); 
		if(target.prev != null && target.next != null) 
		{
			target.prev.next = target.next; 
			target.next.prev = target.prev; 
			modCount += 2; 
		}
		else if(target.prev == null && target.next != null)
		{
			target.next.prev = null; 
			modCount += 1; 
		}
		else if(target.prev != null && target.next == null)
		{
			target.prev.next = null; 
			modCount += 1; 
		}


		chainLength -= 1; 
		return target.data;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return chainLength; 
	}

	@Override
	public int getModificationCount() {
		// TODO Auto-generated method stub
		return modCount; 
	}

	@Override
	public void resetModificationCount() {
		modCount = 0; 

	}
	//helper method that walks along the node chain to the target element
	private Node<T> walk(int index)
	{
		if(index >= chainLength || index < 0)
			throw new ArrayIndexOutOfBoundsException("index out of bounds: " + index); 

		Node<T> curr = beginning; 
		int currIdx = 0; 
		while(currIdx != index)
		{
			curr = curr.next; 
			currIdx += 1; 
		}
		return curr; 
	}



	///Private node class

	private class Node<E>
	{

		Node<E> prev; 
		Node<E> next; 
		E data;
		public Node(E Data)
		{
			this.data = Data; 
			prev = null; 
			next = null;
		}
	}
	//iterator class
@Override
	public Iterator<T> iterator() {
		return new Iterator<T>()
		{
			Node cursor = beginning; 
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return (cursor.next != null); 
			}

			@SuppressWarnings("unchecked")
			@Override
			public T next() {
				if (!hasNext())
					throw new NoSuchElementException("No next element dumbass");
				cursor = cursor.next; 
				return (T) cursor.data; 					
			}
			
		@Override
		public void remove() {
			
		}
			
		};






	}
}
