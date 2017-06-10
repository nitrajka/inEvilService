
public class Sheep extends Animal {
	
	public Sheep( int paneWidth, int paneHeight, boolean white ) {
		super( paneWidth, paneHeight, white );
	}
	
	/**
	 * Changes Animal color to black. Only Sheep can be changed to black.
	 */
	public void setBlack() {
		white = false;
	}
}
