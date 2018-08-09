package game;


import java.awt.Point;
import java.awt.image.BufferedImage;

public class LargeCologneActive extends Tower{

	
	int[] coords;  
	int hitPoints; 
	ResourceLoader loader; 
	int frame; 
	
	public LargeCologneActive(GameState game, Path gamePath) {
		super(game, gamePath);
		
		loader = ResourceLoader.getLoader(); 
		sprite = loader.getImage("large_cologne.png"); 
		attackRadius = 150; 
		frame = 0; 
		
		
		
	}
	
	@Override
	public void update()
	{
		Enemy target = findNearestEnemy();
		if(frame%10 == 0)
		{
			
			//1target.sprite = loader.getImage("large_cologne.png"); 
			if(target != null){
			if(isInRange(target))
				fire(target); 
			}
			}
		//see SmallTowerActive
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
		
		LargeTowerShot shot = new LargeTowerShot(game, location, angle, this); 	
		//had to add a second list in order to avoid a concurrent eidt error or what ever its called
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

