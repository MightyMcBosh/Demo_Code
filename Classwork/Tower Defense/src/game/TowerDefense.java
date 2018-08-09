package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


/*
 * Feinstein's Revenge
 * 
 * This entire game is a reference to the scene in season 6 of parks and rec 
 * when Andy and the ninjas TP Dennis Feinstein's office.
 * 
 * A tower defense game where dennis feinstein fights back. 
 * 
 * 
 * Also, side note, i am not an artist. At all. I did the best I could with what i had but dont judge how the game looks. 
 * 
 * @author Ben Stewart/ Some Code amended from the base code by Peter Jensen. 
 * 
 * 
 */
public class TowerDefense extends JPanel implements 
Runnable,
ActionListener,
MouseListener,
MouseMotionListener
{
	// This constant avoids an obnoxious warning, but it is totally unused by us.
	//   It would only be relevant if we were using object serialization.

	private static final long serialVersionUID = 42L;

	// Fields (object variables) 
	
	//Declared up here because it would throw a null pointer exception if I tried to put it in the run method 
	 private GameState gameState = new GameState();; 
	double totalPathLength; 
	double percentTraveled; 
	Timer animTimer;
	TowerDefense TD;  
	public static void main (String[] args)
    {
      
		
		
        SwingUtilities.invokeLater(new TowerDefense());

        // Done.  Let the main thread of execution finish.  All the
        //   remaining work will be done by the GUI thread.
    }
    
    
    public void run ()
    {
    	
    	JFrame f = new JFrame("Feinstein's Ninja Attack!");
        //gameState = new GameState(); 
        
         
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //can't directly set the area of the content frame
        Dimension panelSize = new Dimension(gameState.backdrop.getWidth() + 200 , gameState.backdrop.getHeight()); 
        
        //System.out.println(panelSize.toString());
		
		
        //TD = new TowerDefense();
        
        f.setContentPane(this);
        
        //add mouse listener  logic
        addMouseListener(this); 	
        addMouseMotionListener(this); 
    		
        this.setMinimumSize(panelSize);
		this.setPreferredSize(panelSize);
		this.setMaximumSize(panelSize);
		
        
       
        f.setLocationRelativeTo(null);  // Centers window
        f.setVisible(true);
        

    	
    	f.pack();
    	f.setResizable(false);  
    	animTimer = new Timer(16,this);
        animTimer.start(); 
        
    }





    public void paint (Graphics g)
	{
    	
	   	//System.out.println("Update");
    	gameState.draw(g); 
	}
    //Documentation said it may work
    
//    public void update (Graphics g)
//   	{
//   		//Draw the game state
//   		gameState.draw(g); 
//   		
//   	}


	
	
	public void actionPerformed (ActionEvent e)
	{
		Object source = e.getSource(); 
		if(source.equals(animTimer))
			{
				
				gameState.update(); 
				repaint(); 
				//System.out.println("paint");
				animTimer.start(); 
			}
		//if(source = )
		
	}

	//Sets a flag in the game state. The game will check to see if it was a click on an object.
	public void mouseClicked  (MouseEvent e) {	 
	
	}
	public void mouseMoved    (MouseEvent e) { 
		//every time the mouse moves the position will be put here 
	Point point =  new Point(e.getX(), e.getY());  

	gameState.setMouseLocation(point);

	
	//System.out.println(e.getY() + " " + e.getX()); 
		
	}
//unused methods
	public void mouseDragged  (MouseEvent e) {}
	public void mouseEntered  (MouseEvent e) { }
	public void mouseExited   (MouseEvent e) { }
	
	public void mouseReleased (MouseEvent e) {
		gameState.setMouseState(false);
	}
	public void mousePressed  (MouseEvent e) {
		gameState.setMouseState(true); 
	}
	
	


}


