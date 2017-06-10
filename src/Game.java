import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {
	
	private final int width = 500;
	private final int height = 500;
	private Playground p;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Takes an instance of Stage, creates new Scene, and Playground. 
	 * Sets onKeyPressed listeners on Scene instance. Handles key pressed during the game, and after game is finished.
	 * If game is finished, than ENTER will restart the game.
	 * @param primaryStage instance of Stage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		p = new Playground(width, height);
		Scene sc = new Scene(p, width, height);
		sc.setFill(Color.GREENYELLOW);
		primaryStage.setResizable(false);
		primaryStage.setScene(sc);
		primaryStage.show();
		
		sc.setOnKeyPressed(e-> {
			if( p.getState().getEnded() ) {
				if( e.getCode().equals(KeyCode.ENTER) ) {
					p = new Playground(width, height);
					sc.setRoot(p);
					return;
				}	
			}
			p.handleKeyPressed(e);
		});
		
		sc.setOnKeyReleased(e-> {
			p.handleKeyReleased(e);
		});
	}
}

//refactorizacia: AI: pes nahana ciernu ovcu