import java.util.HashMap;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Player extends Animal {
	
	private final String UP = "UP";
	private final String LEFT = "LEFT";
	private final String DOWN = "DOWN";
	private final String RIGHT = "RIGHT";
	private HashMap<String, Integer> pressed;
	
	/**
	 * Initializes new Player, maps keys on hashmap
	 * @param paneWidth width of Scene
	 * @param paneHeight height of Scene
	 * @param white boolean value, expressed whether player is white,
	 */
	public Player(int paneWidth, int paneHeight, boolean white ) {
		super(paneWidth, paneHeight, white);
		pressed = new HashMap<String, Integer>(); 
		pressed.put( UP, 0 ); pressed.put( LEFT, 0 ); pressed.put( DOWN, 0 ); pressed.put( RIGHT, 0 );
		dx = 3; dy = 3;
	}
	
	/**
	 * Sets direction in which Player will be moved. 
	 * @param e KeyCode instance
	 */
	public void handlePressed(KeyEvent e) {
		if( e.getCode().equals(KeyCode.UP) ) {
			pressed.put( UP, -dy );
			pressed.put( DOWN, 0 );
		}
		
		if( e.getCode().equals(KeyCode.DOWN) ) {
			pressed.put( DOWN, dy );
			pressed.put( UP, 0 );
		}
		
		if( e.getCode().equals(KeyCode.LEFT) ) {
			pressed.put( LEFT, -dx );
			pressed.put( RIGHT, 0 );
		}
		
		if( e.getCode().equals(KeyCode.RIGHT) ) {
			pressed.put( RIGHT, dx );
			pressed.put( LEFT, 0 );
		}
		
	}
	
	/**
	 * Unsets direction in which Player will not be moved. 
	 * @param e KeyCode instance
	 */
	public void handleReleased(KeyEvent e) {
		if( e.getCode().equals(KeyCode.UP) ) {
			pressed.put( UP, 0 );
		}
		
		if( e.getCode().equals(KeyCode.DOWN) ) {
			pressed.put( DOWN, 0 );
		}
		
		if( e.getCode().equals(KeyCode.LEFT) ) {
			pressed.put( LEFT, 0 );
		}
		
		if( e.getCode().equals(KeyCode.RIGHT) ) {
			pressed.put( RIGHT, 0 );
		}
	}
	
	/**
	 * Moves player in particular direction.
	 */
	public void update() {
		if( x + pressed.get(LEFT) >= 0 ) { 
			x += pressed.get(LEFT);
		}
		
		if( x + pressed.get(RIGHT) < paneWidth - 2*radius ) { 
			x += pressed.get(RIGHT);
		}
		
		if( y + pressed.get(UP) >= 0 ) { 
			y += pressed.get(UP);
		}
		
		if( y + pressed.get(DOWN) < paneHeight - 2*radius ) { 
			y += pressed.get(DOWN);
		}
	}
}
