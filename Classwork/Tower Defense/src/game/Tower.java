package game;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public abstract class Tower implements Animatable {
	
	GameState game; 
	Point location; 
	Path gamePath;
	BufferedImage sprite; 
	int attackRadius;
	int hitPoints; 
	

	public Tower(GameState game, Path gamePath)
	{
		this.game = game; 
		this.gamePath = gamePath; 
	}

	public void update() {
		
	}
	@Override
	public Point getLocation()
	{
		return location; 
	}
	
	
	@Override
	public void draw(Graphics g) {
		//draws the image sprite
		
		g.drawImage(sprite, (int)location.getX() - sprite.getWidth()/2, (int)location.getY() - sprite.getHeight()/2 , null); 
		g.setColor(new Color(0.0f,0.0f,0.0f, .5f)); 
		g.drawOval((int) (location.getX() - attackRadius), (int) (location.getY() - attackRadius),  attackRadius*2, attackRadius*2); 
		}
	
	public Enemy findNearestEnemy()
	{
		Enemy currentNearest = null; 
		//set baseline distance to something really far
		double shortestDistance = 1000; 
		for(Animatable a : game.Active){
			//if the current animatable is an enemy
			if(a instanceof Enemy)
			{
				//cast into enemy type
				Enemy e = (Enemy) a;
				//System.out.println(e.getLocation()); 
				double distance = findDistance(e);
			//System.out.println(distance);
				//if the current enemy is closer the the current closest enemy, this becomes the current closest
				
				if(distance < shortestDistance){
					currentNearest = e;
					shortestDistance = distance; 
				}
				
				
					
				
			}
		
			
		}
		
		return currentNearest; 	
		
		
	}
	//uses simple trig to find distance
	public double findDistance(Animatable a)
	{
		
		double distance = Point.distance(location.getX(), location.getY(), a.getLocation().getX(), a.getLocation().getY()); 
		//System.out.println(distance);
		return distance; 
	
	}
	//fires at the specified enemy, overriden in the subclasses
	public void fire(Enemy e)
	{
		 
	}
	

	


}
