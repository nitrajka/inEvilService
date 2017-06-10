
public class Enemy extends Animal {
	private Player player;
	/**
	 * Initializes Enemy's coordinates so that Player and Enemy will not be initialized on the same position.
	 * @param paneWidth width of Scene
	 * @param paneHeight height of Scene
	 * @param white boolean value, expressed whether enemy is white,
	 * @param player the Player instance
	 */
	public Enemy(int paneWidth, int paneHeight, boolean white, Player player) {
		super(paneWidth, paneHeight, white );
		
		this.player = player;
		int playerX = player.getX();
		int playerY = player.getY();
		while( enemyPlayerDistance( playerX, playerY ) < radius*2 + radius ) {
			x = r.nextInt( (int) (paneWidth - radius*2 + 1) );
			y = r.nextInt( (int) (paneHeight - radius*2 + 1) );
		}
	}
	
	private double enemyPlayerDistance(double playerX, double playerY) {
		double distanceX = (x - playerX);
		double distanceY = (y - playerY);
		return Math.sqrt( Math.pow(distanceX, 2) + Math.pow(distanceY, 2) );
	}
	
	/**
	 * Updates Enemy position. Enemy chases player.
	 * Moves animal to the left/right/left/down so that enemy doesn't go behind the Playground borders.
	 */	
	@Override
	public void update() {
		if( x < Math.abs(dx)  ) dx = Math.abs(dx);
		if( y < Math.abs(dy)  ) dy = Math.abs(dy);
		if( x > paneWidth - 50-Math.abs(dx) ) dx = -Math.abs(dx);
		if( y > paneHeight - 50-Math.abs(dy) ) dy = -Math.abs(dy);
		if( x != player.getX() ) {
			x += (x > player.getX()) ? -dx : dx;
		}
		if( y != player.getY() ) {
			y += (y > player.getY()) ? -dy : dy;
		}
	}
}
