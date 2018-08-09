package game;

import java.awt.Point;

public class SmallTowerShot extends Effect{

	public SmallTowerShot(GameState game, Point location, double direction, Tower source)
	{
		
		super(game,location,direction,source);
		damage = 1; 
		sprite = ResourceLoader.getLoader().getImage("smallTowerAttack.png"); 
		moveSpeed = 8;
		
	
	}
}
