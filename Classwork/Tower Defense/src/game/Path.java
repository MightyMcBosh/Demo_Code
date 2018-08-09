package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

//this class saves the path object.
public class Path {
	
	
	private int[] x; 
	private int[] y; 
	private int length; 
	private double[] segmentLengths;
	private double maxpathLength; 
	
	public Path(int length, int[] x, int[] y)  
	{
		
		this.x = x; 
		this.y = y; 
		
		segmentLengths = new double[length-1]; 
		for(int i = 0; i < length - 1; i++ )
		{
			double temp = Math.pow((x[i+1] - x[i]),2)  + Math.pow((y[i+1]-y[i]),2); 
			//System.out.println(temp); 
			temp = Math.pow(temp, .5); 
			segmentLengths[i] = temp; 
		}
		
		this.length = length;
		
		
	}
	//returns the x and y coordinate arrays.
	public int[] getX()
	{
			return x;
	}
	public int[] getY()
	{
		
		return y;
	
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.RED);
		for(int i = 0; i < length - 1; i++)
		{
			g.drawLine(x[i],y[i],x[i+1], y[i+1]);
		}
		
		
	}
	
	public int getLength()
	{
		return length;
	}
	
	//calculates the length of the path in pixels
	public double getPathLength()
	{
		double pathLength = 0;
		for(int i = 0; i < length - 1; i++ )
		{
			pathLength = pathLength + segmentLengths[i]; 
		}
		maxpathLength = pathLength; 
		return pathLength; 
	}
	
	public int[] getPathPosition(double position)
	{
		int[] endposition = new int[2];
			double currentLength=0;
			double targetLength=0; 
			double totalLength=0; 
			for(int i = 0; i < length - 1; i++ )
			{
				totalLength = totalLength + segmentLengths[i]; 
			}
			targetLength = totalLength*position; 
			//System.out.println(targetLength);
			int index = 0; 
			//scans through the array of segment lengths 
			//ands stops when it goes too far
			while(currentLength <= targetLength)
			{
				
				currentLength = currentLength + segmentLengths[index]; 
				index = index+1;
			}
			//backs up one iteratiuon
				if(index > 0)
				{
				index = index - 1;
				currentLength = currentLength - segmentLengths[index]; 
				}
				//calculates the exact spot on the chosen line segment
				double segmentTarget = targetLength - currentLength; 
				double coeff = segmentTarget / segmentLengths[index];
				
				int xTarget = (int)(coeff*(x[index+1] - x[index]) + x[index]);
				int yTarget = (int)(coeff*(y[index+1] - y[index]) + y[index]);
				
				endposition[0] = xTarget;
				endposition[1] = yTarget; 
				
					
				
				
		
		return endposition; 
		
		
	}
	
	
}
