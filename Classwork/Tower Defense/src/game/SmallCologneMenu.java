package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class SmallCologneMenu extends Tower{
	


		
		
		int hitPoints; 
		BufferedImage sprite;  
		ResourceLoader loader; 
		public boolean isSelected; 
		
		
		public SmallCologneMenu(GameState game, Path gamePath) {
			super(game, gamePath);
			isSelected = false; 
			loader = ResourceLoader.getLoader(); 
			sprite = loader.getImage("small_cologne.png"); 
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
				location = new Point(640,350); 
				g.drawImage(sprite, (int)location.getX() - sprite.getWidth()/2, (int)location.getY() - sprite.getHeight()/2 , null); 
				drawStats(g); 
			}
			
			
		}	
		
		public void drawStats(Graphics g){
			g.setColor(Color.RED);
			g.fillRect(675,330,40,5);
			g.fillRect(675,350,40,5);
			g.fillRect(675,370,40,5);
			
			g.setColor(Color.green); 
			//range
			g.fillRect(675,330,30,5);
			//attack speed
			g.fillRect(675,350,40,5);
			//damage
			g.fillRect(675,370,17,5);
			
			g.setColor(Color.black);
			g.setFont(game.statFont);
			g.drawString("Rage. By feinstein. -600 cr",620,300);
			g.drawString("Range",720,335);
			g.drawString("Attack speed",720,355);
			g.drawString("Damage",720,375);
			
		}
		
		
		
		
				
		
		

}
