package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

//class that controls all the logic for the enemies in the tower defense project. 
public abstract class Enemy implements Animatable{
	GameState game; 
	double percentTraveled; 
	int[] coords; 
	Path gamePath;
	BufferedImage sprite; 
	double hitPoints; 
	double maxHitPoints;
	int credits; 
	

	public Enemy(GameState game, Path gamePath)
	{
		this.game = game; 
		this.gamePath = gamePath; 
	}

	public void update() {
		coords = gamePath.getPathPosition(percentTraveled);
		percentTraveled = percentTraveled + .0015; 

		if(percentTraveled >= .9975)
		{
			this.game.Defeated.add(this); 
			this.game.adjustLives(-1); 
		}
		
		if (hitPoints <= 0){
			game.Defeated.add(this);
			game.adjustCredits(credits);
		}
		
	}
	
	public Point getLocation()
	{
		int[] pos; 
		pos = gamePath.getPathPosition(percentTraveled);
		return new Point(pos[0], pos[1]); 
	}
	
	public void draw(Graphics g) {
		//draws the image sprite
		g.drawImage(sprite, coords[0] - sprite.getWidth()/2, coords[1] - sprite.getHeight()/2 , null); 
		drawHealthBar(g); 
	}
	public void drawHealthBar(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(coords[0] , coords[1] - sprite.getHeight()/2 - 5, 40, 5); 
		g.setColor(Color.GREEN);
		g.fillRect(coords[0] , coords[1] - sprite.getHeight()/2 - 5, (int)((hitPoints) / (maxHitPoints) * 40), 5);
	}

}
