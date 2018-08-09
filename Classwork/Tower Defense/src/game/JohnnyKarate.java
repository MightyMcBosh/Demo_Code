package game;
//the bigger, scarier enemy 
public class JohnnyKarate extends Enemy {

	//Object field 
		ResourceLoader loader; 
		
		
		//Constructor
		public JohnnyKarate(GameState gameState, Path path) 
		{
			super(gameState,path);
			this.game = gameState; loader = ResourceLoader.getLoader(); 
			this.sprite = loader.getImage("chris pratt.png");
			percentTraveled = 0;
			coords = path.getPathPosition(0);
			gamePath = path;
			hitPoints = 5 + game.round; 
			maxHitPoints = 5 + game.round; 
			
			
			credits = 102 - 2*game.round; 
			if(credits <= 0)
				credits = 1; 

		}
		
		
		
	}
