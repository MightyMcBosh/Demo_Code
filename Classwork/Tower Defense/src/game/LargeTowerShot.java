package game;

import java.awt.Graphics;
import java.awt.Point;

public class LargeTowerShot extends Effect {
	
	
	
	
	public LargeTowerShot(GameState game, Point location, double direction, Tower source)
	{
		
		super(game,location,direction,source);
		damage = 2; 
		sprite = ResourceLoader.getLoader().getImage("largeTowerAttack.png"); 
		moveSpeed = 6;
		
	
	}
	

	

	
}
