package assignment03;
/*
 * @author Benjamin Stewart
 */
public class MyStack<T> implements UtahStack<T> {
	UtahList<T> list;
	int stackSize; 
	
	
	
	public MyStack(UtahList<T> backingList) {
		
		list = backingList;
		stackSize = list.size(); 
	}

	@Override
	public void push(T data) {
		list.insert(stackSize , data);	
		stackSize = list.size(); 
	}

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		T data = list.remove(stackSize-1); 
		stackSize = list.size(); 
		return data; 
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return stackSize;
	}

}
