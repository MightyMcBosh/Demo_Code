package game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

//controls effects
public abstract class Effect implements Animatable {
	//
	GameState game; 
	public Point location;
	public double direction;
	public double moveSpeed; 
	BufferedImage sprite; 
	double distanceTraveled; 
	Effect current;
	Tower source;
	protected int damage; 
		
	public Effect(GameState game, Point location, double direction, Tower source )
	{
		this.game = game;
		this.location = location; 
		this.direction = direction;
		distanceTraveled = 0; 
		this.source = source; 

	}
	
	//moves the effect in the specified direction, scaled by move speed
	public void update() {
		
		double newX = Math.cos(direction)*moveSpeed + location.getX(); 
		double newY = Math.sin(direction)*moveSpeed + location.getY(); 
		
		distanceTraveled = Point.distance(location.getX(), location.getY(), newX, newY) + distanceTraveled; 
		System.out.println(distanceTraveled);
		location = new Point((int) newX, (int) newY); 
		if (distanceTraveled > (double)source.attackRadius)
		{
			game.Defeated.add(this); 
		}
		//runs through the list, sees if it hit anything
		for(Animatable a : game.Active)
		{
			if(a instanceof EnemyNinja)
			{
				EnemyNinja enemy = (EnemyNinja) a; 
				if(isHit(enemy))
				{
					enemy.hitPoints = enemy.hitPoints - damage; 
					game.Defeated.add(this); 
				}
					
			}
			if(a instanceof JohnnyKarate)
			{
				JohnnyKarate enemy = (JohnnyKarate) a; 
				if(isHit(enemy))
				{
					enemy.hitPoints = enemy.hitPoints - damage; 
					game.Defeated.add(this); 
				}
					
			}
		}
		location = new Point((int) newX, (int) newY); 
		
				
		
	}


	public void draw(Graphics g) {
		
		g.drawImage(sprite, (int)location.getX() - sprite.getWidth()/2, (int)location.getY() - sprite.getHeight()/2, null); 
		
		
	}


	public Point getLocation() {
		// TODO Auto-generated method stub
		return location;
		
		
	}	
	
	public boolean isHit(Enemy e)
	{
		boolean result = Math.abs(e.getLocation().getX() - location.getX()) < 25 && Math.abs(e.getLocation().getY() - location.getY()) < 25;
		//System.out.println(result);
		return result;  
			
	}
	
	
	
	
	
	
}
