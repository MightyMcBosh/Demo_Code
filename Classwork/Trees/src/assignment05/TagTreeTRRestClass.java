package assignment05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TagTreeTRRestClass {
	
	
	
	public static void main(String args[])
	{
		TagTree tree; 
		Scanner in; 
		try {
		File scan = new File("src/source.txt"); 
		
		in = new Scanner(scan);
		tree = new TagTree(in); 
		
		int height = tree.getHeight(); 
		//int deg = tree.getDegree(); 
		
		String pre = tree.outputPrefix();
		String post = tree.outputPostfix(); 
		System.out.println(pre);
		System.out.println(post);
		System.out.println(tree.findDepth("E"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
