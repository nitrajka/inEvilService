import java.util.ArrayList;
import java.util.Random;

public class State {
	private ArrayList<Animal> animals;
	private final int sheepsCount = 15;
	private final int enemiesCount = 2;
	Random r = new Random();
	private Player player;
	private boolean win, ended;
	
	/**
	 * Initializes new State, sets TimeLine for animation
	 * @param width width of Scene
	 * @param height height of Scene
	 * @param imgWidthHeight height and width parameter for sheep Image 
	 */
	public State( int width, int height, int imgWidthHeight ) {
		Animal.radius = imgWidthHeight/2;
		animals = new ArrayList<Animal>();
		ended = false;
		
		for( int i = 0; i < sheepsCount; i++ ) {
			animals.add(new Sheep(width, height, true));
		}
		
		player = new Player(width, height, false);
		animals.add(player);
		
		for( int i = 0; i < enemiesCount; i++ ) {
			animals.add(new Enemy(width, height, true, player ));
		}
	}
	
	/**
	 * Getter for player instance.
	 * @return player instance of Player
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Getter for animals array.
	 * @return ArrayList of Animal instances
	 */
	public ArrayList<Animal> getAnimals() {
		return animals;
	}
	
	/**
	 * Updates position of every animal
	 */
	public void makeMove() {
		for( Animal a: animals ) {
			a.update();
		}
	}
	
	/**
	 * Check collisions between animals. And changes color of Sheeps if needed.
	 */
	public void checkCollisions() {
		for( int i = 0; i < animals.size(); i++ ) {
			for( int j = 0; j < animals.size(); j++ ) {
				if( i == j ) {
					continue;
				}
				
				Animal a1 = animals.get(i);
				Animal a2 = animals.get(j);
				if( a1.collision(a2) ) {
					if( a1.isWhite() && a2.isWhite() ) {
						continue;
					}
					if( !a1.isWhite() && !a2.isWhite() ) {
						continue;
					}

					if( a1 instanceof Sheep || a2 instanceof Sheep ) {
						if( a1 instanceof Enemy || a2 instanceof Enemy ) {
							if( a1 instanceof Sheep ) {
								((Sheep) a1).setWhite();
								continue;
							} 
							((Sheep) a2).setWhite();
							continue;
						}
						
						if( a1 instanceof Player || a2 instanceof Player ) {
							if( a1 instanceof Sheep ) {
								((Sheep) a1).setBlack();
								checkWin();
								continue;
							} 
							((Sheep) a2).setBlack();
							checkWin();
							continue;
						}
						
						((Sheep) a2).setBlack();
						((Sheep) a1).setBlack();
						checkWin();
						
					} else if( a1 instanceof Player || a2 instanceof Player ) {
						ended = true;
						win = false;
					}
				}
			}
		}
	}
	
	private void checkWin() {
		for(Animal a: animals ) {
			if( a instanceof Sheep && a.isWhite() ) {
				return;
			}
		}
		ended = true;
		win = true;
	}
	
	/**
	 * Getter for variable which expresses whether the game has ended or not.
	 * @return ended boolean, expressed whether the game ended
	 */
	public boolean getEnded() {
		return ended;
	}
	
	/**
	 * Returns boolean, whether player won after game ending.
	 * @return win boolean
	 */
	public boolean getWin() {
		return win;
	}
}
