package assignment05;

import java.util.ArrayList;
import java.util.Scanner;
/*
 * Tag Tree class- Creates a tree ADT to hold a group of tags, such as XML or HTML5.
 * constructor - Write a public constructor that takes one parameter, a Scanner object.  The constructor (or any helper methods it calls)
 *  will use the Scanner object to scan in the tag tree data and build a tag tree data structure within 'this' object.  
 *  (It is the caller's responsibility to build the Scanner object on some valid source of tag data.)

Write a public method named getHeight that takes no parameters and returns an int.  
The method returns the height of this tag tree.

Write a public method named getMaxDegree that takes no parameters and returns an int. 
 The method returns the maximum degree (maximum child count) of any node in this tag tree.

Write a public method named outputPrefix that takes no parameters and returns a String reference.  
The returned string should contain the node names in prefix order (pre-order traversal order),
 separated by whitespace.  For the tree above (with the root 'color'), the output would be "color green blue yellow".  

Write a public method named outputPostfix that takes no parameters and returns a String reference.  
The returned string should contain the node names in postfix order (post-order traversal order), 
separated by whitespace.  For the tree above (with the root 'color'), the output would be "green blue yellow color".

Write a public method named isBinaryTree that takes no parameters and returns a boolean. 
 The method returns true if this tag tree is a binary tree, and false otherwise.

Write a public method named isTwoTree that takes no parameters and returns a boolean.  T
he method returns true if this tag tree is a two-tree, and false otherwise.

Write a public method named isFullBinaryTree that takes no parameters and returns a boolean. 
 The method returns true if this tag tree is a full binary tree, and false otherwise.

Write a public method named findDepth that takes a String as a parameter and returns an int. 
 This method searches for the node who's name equals the String and returns the depth of that node.  
 If the String is not the name of any node, -1 is returned.  If the String is the name of more than one node, 
 the depth of the node that occurred first in the tag data is returned. 
 * 
 * 
 *@author Ben Stewart
 *@date 10/24/2017
 */
public class TagTree {

	private Node root; 
	int height; 

	public TagTree(Scanner in)
	{
		String firstTag = in.next(); 
		String tagName = firstTag.substring(1); 
		root = parse(in, tagName); 
	}


	public int getHeight()
	{
		return getHeightHelper(root); 
	}

	public int getMaxDegree()
	{
		return getDegreeHelper(root); 
	}


	public String outputPrefix()
	{
		StringBuilder inString = new StringBuilder(""); 
		return prefix(root,inString).toString(); 
	}
	//Helper method
	private StringBuilder prefix(Node in,StringBuilder inString) {


		inString.append(in.data+ " "); 



		for(Node e : in.children)
		{

			prefix(e,inString);  
		}

		return inString; 
	}

	private String prefixTest(Node in,String inString) {

		String newString = ""; 

		newString = inString + " " + in.data + " "; 


		return newString; 
	}
	private StringBuilder postfix(Node in,StringBuilder inString) {

		for(Node e : in.children)
		{

			prefix(e,inString);  
		}

		inString.append(in.data+ " "); 
		return inString; 
	}


	public String outputPostfix()
	{

		StringBuilder inString = new StringBuilder(""); 
		return postfix(root,inString).toString(); 
	}

	public boolean isTwoTree()
	{
		if(root !=null)
			return isBinaryTree(); 
		else
		return false; 
	}

	public boolean isBinaryTree()
	{
		if(root !=null)
		return (getDegreeHelper(root) == 2);
		else
		return false;
	}

	public boolean isFullBinaryTree()
	{
		if(root !=null)
		return getBinaryHelper(root);
		else
		return false; 
	}
//returns -1 if not found
	public int findDepth(String in)
	{
		
		if(root != null)
		{
			int depth = findDepthHelper(root,in);
			
		return depth;
		}
		else
		return -1; 
	}
	
	private int findDepthHelper(Node in, String data)
	{
		if(in.data.equals(data))
			return getDepth(in); 
		else {
			int depth; 
			for(Node e : in.children)
			{
				depth = findDepthHelper(e,data); 
				if(depth != -1)
					return depth; 
			}
			return -1; 
		
		}
		
	}
	
	private int getDepth(Node in)
	{
		int count = 0; 
		while(in.parent != null)
		{
			in = in.parent;
			count++;  
		}
		return count; 
	}


	private int getHeightHelper(Node in)
	{

		int layers = -1;

		if(in == null)
			return layers; 


		for(Node e : in.children)
		{
			int tempHeight = getHeightHelper(e); 
			if (tempHeight > layers)
				layers = tempHeight; 
		}


		layers = layers + 1;

		return layers; 
	}

	private int getDegreeHelper(Node in) {


		int degree  = in.children.size(); 


		for(Node e : in.children)
		{
			int tempDeg = getDegreeHelper(e); 
			if (tempDeg > degree)
				degree = tempDeg;  
		}




		return degree; 

	}
	private boolean getBinaryHelper(Node in) {


		int degree  = in.children.size(); 

		boolean res = (degree == 2); 


		for(Node e : in.children)
		{
			boolean result  = getBinaryHelper(e); 
			if(!result)
				return result;  
		}




		return res; 

	}




	public Node parse(Scanner in, String tag)
	{
		Node current;

		String id = "</" + tag + ">";

		String next = in.next();  
		current = new Node(next.substring(0,next.length() - 1 )); 
		next = in.next();

		while(!next.equals(id))
		{
			if(!next.substring(0,2).equals("</") && !next.substring(0,1).equals("<")) 
			{
				current = new Node(next.substring(0,next.length() - 2 )); 				
			}
			else if(!next.substring(0,2).equals("</") && next.substring(0,1).equals("<"))
			{
				Node child = parse(in,next.substring(1)); 
				current.add(child);
				next = in.next(); 
			}

		}
		return current; 
	}

	//Class to hold the tree data

	static private class Node
	{
		String data;
		Node parent; 
		ArrayList<Node> children; 


		Node(String data)
		{
			this.data = data;
			children = new ArrayList<Node>(); 

		}
		//adds a child to the referenced node.
		
		public void add(Node child)
		{
			children.add(child); 
			//Also added a parent because it made finding depth ten times easier.
			child.parent = this; 
		}
	}
}
