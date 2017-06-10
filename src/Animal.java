import java.util.Random;

public class Animal {
	protected int paneWidth, paneHeight;
	protected int dy, dx;
	protected int x, y;
	static int radius;
	protected Random r;
	protected boolean white;
	
	/**
	 * Initializes Animal instance. Sets it's coordinates to random int value
	 * @param paneWidth width of Scene
	 * @param paneHeight height of Scene
	 * @param white tells if animal instance is white or black (good or bad)
	 */
	public Animal( int paneWidth, int paneHeight, boolean white ) {
		this.paneWidth = paneWidth;
		this.paneHeight = paneHeight;
		this.white = white;
		r = new Random();
		dx = 1; dy = 1;
		x = r.nextInt( (int) (paneWidth - radius*2 + 1) );
		y = r.nextInt( (int) (paneHeight - radius*2) + 1 );
	}
	
	/**
	 * Checks if animal is white or not.
	 * @return true if animal is white
	 */
	public boolean isWhite() {
		return white;
	}
	
	/**
	 * Changes Animal color to white.
	 */
	public void setWhite() {
		white = true;
	}
	
	/**
	 * Returns x coordinate of an animal.
	 * @return x coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns y coordinate of an animal.
	 * @return y coordinate
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Returns radius of sheep. Images of Animals in Pane are in circle shape. 
	 * So I consider them as circles. Their width and height is the same. 
	 * Radius is distance from the middle of image to the side parallel with x or y axe.
	 * @return radius
	 */
	public int getRadius() {
		return radius;
	}
	
	
	/**
	 * Updates Animal position. In probability 1:20 Animal moves randomly
	 * Moves animal to the left/right/left/down so that animal doesn't go behind the Playground borders.
	 */
	public void update() {
		if( x > Math.abs(dx) && x < paneWidth -50-Math.abs(dx) ) {
			if ( r.nextInt(20) == 1 ) { //pravdepodobnost 1:20
				dx = -dx;
			}
		}
		
		if( y > Math.abs(dy) && y < paneHeight -50 -Math.abs(dy) ) {
			if ( r.nextInt(20) == 1 ) {
				dy = -dy;
			}
		}

		//ak je na suradnici x = 3, tak sa neodrazi "kvazi od steny" ale o 3 px vedla
		if( x < Math.abs(dx)  ) dx = Math.abs(dx);
		if( y < Math.abs(dy)  ) dy = Math.abs(dy);
		if( x > paneWidth - 50-Math.abs(dx) ) dx = -Math.abs(dx);
		if( y > paneHeight - 50-Math.abs(dy) ) dy = -Math.abs(dy);
		x += dx;
		y += dy;
	}
	
	/**
	 * Takes another instance of animal and returns boolean value
	 * which represents whether they met. Distance of the Image's middles is smaller than radius *2
	 * @param animal another instance of Animal
	 * @return true if animal met
	 */
	public boolean collision(Animal animal) {
		return Math.sqrt( Math.pow(x + radius  - (animal.getX() + animal.getRadius()) , 2) +
							Math.pow(y + radius - (animal.getY() + animal.getRadius()), 2) ) <= radius + animal.getRadius();
	}
}
