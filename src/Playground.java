import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Playground extends Pane {
	private int width, height, imgWidthHeight;
	private State state;
	private Image black = new Image("blacksheep.png", 60, 60, false, false);
	private Image white = new Image("whitesheep.png", 60, 60, false, false);
	private Image dog = new Image("dogCut.png", 60, 60, false, false);
	private final String restart = "Press ENTER to restart game";
	private final String win = "Congratulations! You won!\n\n" + restart;
	private final String lose = "You lost. Try again?\n\n" + restart;
	private Timeline animation;
	
	/**
	 * Initializes new State, sets TimeLine for animation
	 * @param width width of Scene
	 * @param height height of Scene
	 */
	public Playground( int width, int height ) {
		this.width = width;
		this.height = height;
		imgWidthHeight = 30;
		state = new State( width, height, imgWidthHeight );
		
		animation = new Timeline(new KeyFrame(Duration.millis(35), e-> {
			state.makeMove();
			repaint();
			state.checkCollisions();
			checkEnded();
		}));
		
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();
		System.out.println("new game");
	}
	
	/**
	 * Calls player's handlePressed function.
	 * @param e Key which was pressed
	 */
	public void handleKeyPressed(KeyEvent e) {
		state.getPlayer().handlePressed(e);
	}
	
	/**
	 * Returns State instance. Used to check whether game has ended, to restart it.
	 * @return state instance
	 */
	public State getState() {
		return state;
	}
	
	/**
	 * Calls player's handleReleased function.
	 * @param e Key which was released
	 */
	public void handleKeyReleased(KeyEvent e) {
		state.getPlayer().handleReleased(e);
	}
	
	private void setImageProperty(ImageView iw, int x, int y, Animal a) {
		iw.setX(a.getX());
		iw.setY(a.getY());
		iw.setFitWidth(imgWidthHeight);
		iw.setFitHeight(imgWidthHeight);
	}
	
	private void repaint() {
		this.getChildren().clear();
		for( Animal a: state.getAnimals() ) {
			if( a instanceof Sheep || a instanceof Player ) {
				ImageView iw = new ImageView(black);
				
				if( a.isWhite() ) {
					iw = new ImageView(white);
				}
				setImageProperty(iw, a.getX(), a.getY(), a);
				this.getChildren().add(iw);
				
			} else if( a instanceof Enemy ) {
				ImageView iw = new ImageView(dog);
				setImageProperty(iw, a.getX(), a.getY(), a);
				this.getChildren().add(iw);
			}
		}
	}
	
	private void stopGame(boolean won) {
		animation.stop();
		if( won ) {
			this.getChildren().clear();
			printText(win);
			return;
		}
		
		//prefarbit vsetky ovecky na biele
		this.getChildren().clear();
		for( Animal a: state.getAnimals() ){
			if( a instanceof Player ) {
			}
			a.setWhite();
		}
		repaint();
		printText(lose);		
	}
	
	private void checkEnded() {
		if( state.getEnded() ) {
			if( state.getWin() ) {
				stopGame(true);
				return;
			}
			stopGame(false);
		}	
	}
	
	private void printText(String text) {
		Text t = new Text(text);
		t.setX(width/2 - t.prefWidth(width)/2);
		t.setY(height/2);
		t.setStroke(Color.BLACK);
		this.getChildren().add(t);
	}
}
