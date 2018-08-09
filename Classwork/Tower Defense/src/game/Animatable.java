package game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public interface Animatable {
	
	
	public void update(); 
	public void draw(Graphics g);
	public Point getLocation(); 
	

} 
