package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


//Class to hold and control the game state. 
//Ben Stewart, 4/7/17
public class GameState {

	/**
	 * 
	 */

	//fields
	public BufferedImage backdrop; 
	public Path gamePath; 
	double percentTraveled; 
	ResourceLoader loader; 
	private int spawnTracker; 
	private int lives;  
	private int dollas; 
	public List<Animatable> Active;  
	public List<Animatable> Defeated; 
	public List<Effect>	effects; 
	private Point mouseLocation;
	boolean didClick; 
	public int round; 
	private int waveTracker; 
	boolean isGameOver; 
	boolean isGamePlaying; 
	


	//Flags for tower selection
	//will add more for the more towers

	boolean itemSelected; 
	SmallCologneMenu smallCologneMenu;
	SmallCologneMenu smallSelected; 
	LargeCologneMenu largeCologneMenu;
	LargeCologneMenu largeSelected; 


	//Fonts
	Font gameOverFont;  
	Font menuFont;
	Font statFont; 


	public GameState() {
		//get the resource loader
		loader = ResourceLoader.getLoader();
		//initialize the path position
		percentTraveled = 0; 
		//initialize the path and the background
		backdrop = loader.getImage("path_2.jpg"); 
		gamePath = loader.getPath("path_2.txt"); 
		lives = 10; 
		dollas = 1200; 
		Active = new ArrayList<Animatable>();  
		Defeated = new ArrayList<Animatable>(); 
		effects = new ArrayList<Effect>(); 
		spawnTracker = 1;  
		isGameOver = false;
		isGamePlaying = false; 
		GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		gameOverFont = new Font("Pristina", Font.BOLD, 48);
		menuFont = new Font("Pristina",Font.PLAIN, 24);
		statFont = new Font("Pristina",Font.BOLD, 14);
		mouseLocation = new Point(0,0); 
		didClick = false; 
		round = 1; 
		//Builds all the menu items
		smallCologneMenu = new SmallCologneMenu(this, gamePath);
		largeCologneMenu = new LargeCologneMenu(this, gamePath); 
		smallSelected = new SmallCologneMenu(this,gamePath);
		largeSelected = new LargeCologneMenu(this, gamePath); 
		Active.add(smallCologneMenu); 
		Active.add(largeCologneMenu); 

	}

	public void update ()
	{
		/*
		 * 
		 * this section controls the logic for the rounds. 
		 * generally, each round will appear in
		 * Each round will have three waves. The round also changes how many hitpoints each enemy has and the number of enemies in each wave
		 */

		if(!isGameOver && isGamePlaying )
		{

			if(round < 10 )
			{
			if(round%3 == 1)
			{


				if(spawnTracker %15 == 0 && spawnTracker < 75)  
				{

					Active.add(new EnemyNinja(this,gamePath)); 
				}
				if(spawnTracker == 75)
					Active.add(new JohnnyKarate(this,gamePath)); 
				//Resets the spawn tracker to 0 so it will spawn a new wave. 
				if(spawnTracker >= 300){
					spawnTracker = 1;
				
				waveTracker++;
			}
			}
			if(round%3 == 2){
				if(spawnTracker % (int)(15) == 0 && spawnTracker < 60 )  
				{

					Active.add(new EnemyNinja(this,gamePath)); 
				}
				if(spawnTracker == 75 || spawnTracker == 60)
					Active.add(new JohnnyKarate(this,gamePath)); 
				//Resets the spawn tracker to 0 so it will spawn a new wave. 
				if(spawnTracker >= 240){
					spawnTracker = 1;
					waveTracker++;
				}
			
			
			}
			if(round%3 == 0)
			{
				if(spawnTracker % (int)(15) == 0 && spawnTracker < 45 )  
				{

					Active.add(new EnemyNinja(this,gamePath)); 
				}
				if(spawnTracker == 75 || spawnTracker == 60 || spawnTracker == 45)
					Active.add(new JohnnyKarate(this,gamePath)); 
				//Resets the spawn tracker to 0 so it will spawn a new wave. 
				if(spawnTracker >= 180){
					spawnTracker = 1;
					waveTracker++;
				}
				
			}
			spawnTracker++; 
			
			
			
		
			
			
			
			if(waveTracker > 3){
				waveTracker = 1;
				round++; 
			}
			}
			
			if(round >= 10 && round < 15)
			{
					if(spawnTracker % 15 == 0 && spawnTracker < 30 )  
					{

						Active.add(new EnemyNinja(this,gamePath)); 
					}
					if(spawnTracker == 30 || spawnTracker == 60 || spawnTracker == 45 || spawnTracker == 75)
						Active.add(new JohnnyKarate(this,gamePath)); 
					//Resets the spawn tracker to 0 so it will spawn a new wave. 
					if(spawnTracker >= 180){
						spawnTracker = 1;
						waveTracker++;
					}
					spawnTracker++; 
					
					
					
					
					
					
					
					if(waveTracker > 3){
						waveTracker = 1;
						round++; 
				}
				
			}
				
				//once you get to this point it's all johnny karate
				if(round >= 15)
				{
						if(spawnTracker%15 == 0 && spawnTracker <= 75) 
							Active.add(new JohnnyKarate(this,gamePath)); 
						//Resets the spawn tracker to 0 so it will spawn a new wave. 
						if(spawnTracker >= 150){
							spawnTracker = 1;
							waveTracker++;
						}
						
				
					spawnTracker++; 
					
					
					
				
					
					
					
					if(waveTracker > 3){
						waveTracker = 1;
						round++; 
				}
				}
				


			//Calls update for all the game objects

			for(Animatable a: Active){
				a.update(); 
			}


			if(didClick)
			{

				if(isInSmallCologneMenu(getMouseLocation()) && !itemSelected && dollas >= 600)
				{
					smallSelected.isSelected = true; 
					Active.add(smallSelected);

					itemSelected = true; 
				}
				if(isInLargeCologneMenu(getMouseLocation()) && !itemSelected && dollas >= 2000)
				{
					largeSelected.isSelected = true; 
					Active.add(largeSelected);

					itemSelected = true; 
				}
				if(isInPicture(getMouseLocation()) && smallSelected.isSelected && itemSelected )
				{
					SmallCologneActive newAdd = new SmallCologneActive(this,gamePath); 
					newAdd.location = getMouseLocation(); 
					Active.add(newAdd); 
					smallSelected.isSelected = false;
					Active.remove(smallSelected); 
					itemSelected = false;
					dollas = dollas - 600;  

				}
				if(isInPicture(getMouseLocation()) && largeSelected.isSelected && itemSelected )
				{
					LargeCologneActive newAdd = new LargeCologneActive(this,gamePath); 
					newAdd.location = getMouseLocation(); 
					Active.add(newAdd); 
					largeSelected.isSelected = false;
					Active.remove(largeSelected); 
					itemSelected = false;
					dollas = dollas - 2000;  

				}


			}
			Active.addAll(effects); 
			Active.removeAll(Defeated); 
			effects.clear();

		}
		if(!isGameOver && !isGamePlaying)
		{
			//checks to see if it clicked the rectangle in the middle of ths screen
			if(didClick && getMouseLocation().getX() < 520 && getMouseLocation().getX() > 120 && getMouseLocation().getY() < 215 && getMouseLocation().getY() > 160 )
			{
				isGamePlaying = true;
			}
		}



		//Ends the game when the lives run out
		if(lives == 0){
			isGameOver = true;
			isGamePlaying = false; 
		}
		
		
		if(isGameOver && !isGamePlaying)
		{
			//checks to see if you clicked the retry button
			if(didClick && getMouseLocation().getX() < 380 && getMouseLocation().getX() > 220 && getMouseLocation().getY() > 340 && getMouseLocation().getY() < 400 )
			{
				//resets the game
				reset(); 
			}
		}
			

	}



	public void draw (Graphics g)
	{
		// draws bkgd

		g.drawImage(backdrop,  0, 0, null);  
		g.clearRect(backdrop.getWidth(), 0, 200, backdrop.getHeight());
		g.setFont(menuFont); 
		g.drawString("Lives = " + lives, backdrop.getWidth() + 50, 50); 
		g.drawString("Credits = " + dollas, backdrop.getWidth() + 45, 75); 
		g.drawString("Round = " + round, backdrop.getWidth() + 45, 100); 
		g.drawString("Click on tower",600, 175);
		g.drawString("then on the map to place", 600, 200); 
		g.drawString("Don't place towers on path",600, 225);
		g.drawString("or the ninjas will destroy it", 600, 250); 
		//draws enemies and towers and effects and stuff

		for(Animatable a: Active)
			a.draw(g);
		//draws game over message
		if(isGameOver)
		{
			g.setColor(new Color(0.0f,0.0f,0.0f,.3f));
			g.fillRect(0,0,825,625);				

			g.setColor(Color.BLACK);
			g.fillRect(145, 160, 310, 55);
			g.fillRect(220,340,160,60); 
			g.setColor(Color.RED);
			g.fillRect(165, 240,290, 65);

			g.setFont(gameOverFont);

			g.drawString("GAME OVER",150,200); 
			g.setColor(Color.BLACK);
			g.drawString("Max Round: " + round,170,280); 
			g.setColor(Color.RED);
			g.drawString("Retry!", 230, 380);
			
			
			

		}
		if(!isGameOver && !isGamePlaying)
		{
			g.setColor(new Color(0.0f,0.0f,0.0f,.3f));
			g.fillRect(0,0,825,625);				

			g.setColor(Color.RED);
			g.fillRect(120, 160, 400, 55);
			g.setColor(Color.BLACK);

			g.setFont(gameOverFont);

			g.drawString("CLICK TO START",125,200);
			g.setFont(menuFont);
			g.setColor(Color.RED);
			
			
			


		}

	}

	public void adjustLives(int i) {

		// TODO Auto-generated method stub
		lives = lives + i; 
	}
	//adjust Credits by the specified amount
	public void adjustCredits(int i) {

		// TODO Auto-generated method stub
		dollas = dollas + i;  
	}



	//Set/get methods for the mouse fed in from mouse events in the GUI. this allows us to keep track of the 
	//mouse pointer.
	public void setMouseLocation(Point point){
		mouseLocation = point; 				
	} 
	public Point getMouseLocation()
	{
		return mouseLocation; 
	}

	//Passes in the click event from the gui
	public void setMouseState(boolean didClick)
	{
		//System.out.println(isInSmallCologneMenu(getMouseLocation()));
		this.didClick = didClick; 
	}

	public boolean getMouseState(){
		return didClick; 

	}
	//just a helper method to check that the pointer is clicking withiin the menu sprite
	public boolean isInSmallCologneMenu(Point point)
	{
		if(point.getX() < 655&& point.getX() > 610 && point.getY() > 313 && point.getY() < 387)
			return true; 
		else
			return false; 


	}

	public boolean isInLargeCologneMenu(Point point)
	{
		if(point.getX() < 655 && point.getX() > 610 && point.getY() > 263 + 150 && point.getY() < 337 + 150){
			//System.out.println("true");
			return true; 

		}
		else
			return false; 


	}

	public boolean isInPicture(Point point)
	{
		if(point.getX() < backdrop.getWidth() && point.getX() > 0  && point.getY() > 0 && point.getY() < backdrop.getHeight())
			return true; 
		else
			return false; 
	}
	//resets the game. only called when the retry button at the end is clicked.
	public void reset()  
	{
		
		//initialize the path position
		percentTraveled = 0; 
		//initialize the path and the background
		 
		lives = 10; 
		dollas = 1200; 
		Active.clear(); 
		Defeated.clear(); 
		effects.clear(); 
		spawnTracker = 1;  
		isGameOver = false;
		isGamePlaying = false; 
		GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		gameOverFont = new Font("Pristina", Font.BOLD, 48);
		menuFont = new Font("Pristina",Font.PLAIN, 24);
		statFont = new Font("Pristina",Font.BOLD, 14);
		mouseLocation = new Point(0,0); 
		didClick = false; 
		round = 1; 
		//Builds all the menu items
		smallCologneMenu = new SmallCologneMenu(this, gamePath);
		largeCologneMenu = new LargeCologneMenu(this, gamePath); 
		smallSelected = new SmallCologneMenu(this,gamePath);
		largeSelected = new LargeCologneMenu(this, gamePath); 
		Active.add(smallCologneMenu); 
		Active.add(largeCologneMenu); 
	}




}
