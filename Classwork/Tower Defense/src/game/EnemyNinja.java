package game;

public class EnemyNinja extends Enemy {

	//Object fields

	ResourceLoader loader; 
	//Constructor
	public EnemyNinja(GameState gameState, Path path) 
	{
		super(gameState,path); 
		loader = ResourceLoader.getLoader(); 
		this.sprite = loader.getImage("ninja.png");
		this.game = gameState;
		percentTraveled = 0;
		coords = path.getPathPosition(0);
		gamePath = path;
		
		
		//Enemy stats change depending on the round
		hitPoints = 2 + game.round;
		maxHitPoints = 2 + game.round; 
		 
		credits = 25 - 2*game.round; 
		if(credits <= 0)
			credits = 1; 

	}
}
	

