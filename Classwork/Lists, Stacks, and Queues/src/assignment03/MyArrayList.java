package assignment03;

/*
 * @author Benjamin Stewart
 */
public class MyArrayList<T> implements UtahList<T> {

	private int listSize; 
	private int beginningIdx;
	
	private T backingArray[];
	
	private int modCounter; 
	
	
	//This entire class is based heavily off of the rolling List interface from last assignment. Only uses a 2 for relative growth rate. 
	
	@SuppressWarnings("unchecked")
	public MyArrayList()
	{
		modCounter = 0; 
		beginningIdx = 0; 
		listSize = 0 ;
		backingArray = (T[]) new Object[5];  
	}
	
	@Override
	public T getElement(int index) {
		if(index > this.listSize -1 || index < 0)
		{
			throw new ArrayIndexOutOfBoundsException("Index Array out of bounds");
		}
			return backingArray[index + beginningIdx];
	}
	

	@Override
	public void setElement(int index, T data) {
		if(index >= listSize || index < 0)
			throw new ArrayIndexOutOfBoundsException("Index Array out of bounds");
		
		backingArray[index + beginningIdx] = data; 
		
	}

	@Override
	public void insert(int index, T data) {
		if(index > listSize || index < 0)
			throw new ArrayIndexOutOfBoundsException("Index Array out of bounds");
		
		if(beginningIdx > 0 && beginningIdx + listSize <= backingArray.length)
		{
			for(int i = beginningIdx ; i < index; i++ ) {
				backingArray[i-1] = backingArray[i];
				modCounter++; 
				
			}
			listSize += 1; 
			beginningIdx -= 1; 
			backingArray[index] = data; 
		}
		
		else if(beginningIdx == 0 && listSize >= 1)
		{
			ensureCapacity(listSize + 1);
			//moves the back half of the array up one after checking that the array is large enough to do so 
			for(int i = listSize - 1; i < index; i--)
			{
				backingArray[i+1] = backingArray[i];
				modCounter++; 
			}
			backingArray[index] = data; 
			listSize += 1; 
			modCounter += 1; 
				
		}
		//works for the first one and thats it 
		else if(listSize == 0)
		{
			//ensureCapacity(listSize + 1);
			//moves the back half of the array up one after checking that the array is large enough to do so 
			
			backingArray[index + beginningIdx] = data; 
				
		}
		
			
		
	}

	@Override
	public T remove(int index) {
		if(index >= listSize || index < 0)
			throw new ArrayIndexOutOfBoundsException("Index Array out of bounds");
		
		T data = getElement(index); 
		
		for(int i = index; i < listSize; i++)
		{
			backingArray[i] = backingArray[i+1];
			modCounter++; 
		}
		
		return data; 
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return listSize;
	}

	@Override
	public int getModificationCount() {
		// TODO Auto-generated method stub
		return modCounter; 
	}

	@Override
	public void resetModificationCount() {
		
		modCounter = 0; 
		
	}
	@SuppressWarnings("unchecked")
	private void ensureCapacity (int desiredCapacity)
	{
		
	
		if (backingArray.length < desiredCapacity)
		{
			
			T[] tmp = (T[]) new Object[listSize*2]; 
			for( int i = 0; i < backingArray.length; i++) 
			{
				tmp[i+2]  = backingArray[i]; 
				modCounter += 1; 
			}
			beginningIdx += 2; 
			backingArray = tmp; 
			modCounter += 1; 
		}	

	}

}
