package assignment03;
/*
 * @author Benjamin Stewart
 */

public class MyQueue<T> implements UtahQueue<T> {
	UtahList<T> list;
	
	public MyQueue(UtahList<T> dataList)
	{
		list = dataList;
	}
	@Override
	public void enqueue(T data) {
		list.insert(list.size() - 1, data); 	
	}

	@Override
	public T dequeue() {
		// TODO Auto-generated method stub
		T data = list.remove(0); 
		return (T) data;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return list.size();
	} 
	
}
