package game;


import java.awt.Point;
import java.awt.image.BufferedImage;

public class SmallCologneActive extends Tower{

	
	int[] coords;  
	int hitPoints; 
	ResourceLoader loader; 
	int frame; 
	 
	
	public SmallCologneActive(GameState game, Path gamePath) {
		super(game, gamePath);
		attackRadius = 90; 
		loader = ResourceLoader.getLoader(); 
		sprite = loader.getImage("small_cologne.png"); 
		frame = 0; 
		
	}
	
	
	
	@Override
	public void update()
	{
		//fires every five frames
		Enemy target = findNearestEnemy();
		if(frame%5 == 0)
		{
			
			//1target.sprite = loader.getImage("large_cologne.png"); 
			if(target != null){
			if(isInRange(target))
				fire(target); 
			}
			
		}
		//Cant figure out how to tell if the tower is on a path without massive cpu overhead,
		//so if you put the tower on a path, the first enemy that touches it will destroy it
		if(target != null){
		if(Point.distance(location.getX(),location.getY(),target.getLocation().getX(),target.getLocation().getY()) < 45)
			game.Defeated.add(this); 
		}
		frame += 1; 
		if(frame == 100)
			frame = 0; 
	}
	
	@Override
	
	//fires a shot at the nearest enemy
	public void fire(Enemy target)
	{
		Point enemyLocation = target.getLocation(); 
		double angle = Math.atan((-enemyLocation.getY()+location.getY())/(-enemyLocation.getX() + location.getX())); 
		
		if(enemyLocation.getX() < location.getX())
			angle = angle + Math.PI; 
		
		SmallTowerShot shot = new SmallTowerShot(game, location, angle, this); 	
		//had to add a second list in order to avoid a concurrent edit error or what ever its called
		game.effects.add(shot);
		
	}
	//checks to see if the targeted enemy is in range before firing
	public boolean isInRange(Enemy target)
	{
		double range = Point.distance(location.getX(), location.getY(), target.getLocation().getX(), target.getLocation().getY());
		//System.out.println(range);
		return range < (double) attackRadius;  
	}
	
	
	
	
	

	

}
