package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class LargeCologneMenu extends Tower{
	 
	BufferedImage sprite;  
	ResourceLoader loader; 
	public boolean isSelected; 
	
	
	public LargeCologneMenu(GameState game, Path gamePath) {
		super(game, gamePath);
		isSelected = false; 
		loader = ResourceLoader.getLoader(); 
		sprite = loader.getImage("large_cologne.png"); 
		this.game = game; 
		attackRadius = 0; 
	
	}
	
	
	
	
	@Override
	public void draw(Graphics g)
	{
		if(isSelected){
			location = this.game.getMouseLocation(); 
		g.drawImage(sprite, (int)location.getX() - sprite.getWidth()/2, (int)location.getY() - sprite.getHeight()/2 , null); 
		}else{
			location = new Point(630,470); 
			g.drawImage(sprite, (int)location.getX() - sprite.getWidth()/2, (int)location.getY() - sprite.getHeight()/2 , null); 
			drawStats(g); 
		}
		
		
		
		
		
		
	}
	//draws tower stats
	public void drawStats(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(675,450,40,5);
		g.fillRect(675,470,40,5);
		g.fillRect(675,490,40,5);
		
		g.setColor(Color.green); 
		//range
		g.fillRect(675,450,40,5);
		//attack speed
		g.fillRect(675,470,15,5);
		//damage
		g.fillRect(675,490,40,5);
		
		g.setColor(Color.black);
		g.setFont(game.statFont);
		g.drawString("Power. By feinstein. -2000 cr",620,420);
		g.drawString("Range",720,455);
		g.drawString("Attack speed",720,475);
		g.drawString("Damage",720,495);
		
	}



	

}
