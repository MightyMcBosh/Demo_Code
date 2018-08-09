package game;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import javax.swing.JPanel;

public class ResourceLoader {

	static private ResourceLoader loader;
	private Map imageMap; 
	private Map pathMap; 
	private ClassLoader l; 
	
	//keep the constructor private so there is no way to creat more than one

	private ResourceLoader()
	{
		//Create new maps to store data
		imageMap = new HashMap<String,BufferedImage>(); 
		pathMap = new HashMap<String,Path>(); 
		//create the class loader
		l = this.getClass().getClassLoader(); 
	}
	
	//designed to only have one loader. Keeps us from unintentionally having several sets of assets. 
	static public ResourceLoader getLoader()
	{

		if(loader == null)
			loader = new ResourceLoader();

		return loader;
	}

	public BufferedImage getImage(String image)
	{
		if(imageMap.containsKey(image))
			return (BufferedImage) imageMap.get(image);
		else
		{
			//runs through this code if the image hasn't been loaded yet
			BufferedImage img; 

			InputStream imgStream = l.getResourceAsStream("resources/" + image); 

			try {
				img = javax.imageio.ImageIO.read(imgStream);

				imageMap.put(image,img); 

				return img; 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Couldn't load image" + image);
				System.exit(0);
				return null; 
			} 
		}


	}

	public Path getPath(String text)
	{
		//giving me weird errors
		Path gamePath; 
		if(pathMap.containsKey(text))
		{
			 gamePath = (Path) pathMap.get(text);
			 return gamePath;
		}
		else
		{
			InputStream pathFile = l.getResourceAsStream("resources/" + text);
			if(pathFile == null)
					{
			System.out.println("IS NULL"); 
					}
			
			//File pathFile = new File("D:/workspace/Tower Defense/src/resources/path2.txt");
			Scanner in = new Scanner (pathFile);  // Scan from the text file.            

			// Build the path object (using the scanner).
			
			 
			int length = in.nextInt(); 
			int[] x = new int[length]; 
			int[] y = new int[length]; 
			//Loops through, setting the x and y coordinates in the Path object.
			for(int i = 0; i < length;  i++)
			{
				x[i] = in.nextInt(); 
				y[i] = in.nextInt(); 

			}
			in.close(); 
			gamePath = new Path(length,x,y); 
			pathMap.put(text,gamePath); 
			return gamePath; 
		}
			
		
	}







}

