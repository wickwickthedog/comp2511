package unsw.automata;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * A JavaFX controller for the Conway's Game of Live Application.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLifeController {

	@FXML
	private Pane pane;
	
	@FXML
	private GridPane squares;
	
	@FXML
	private Button tick;
	
	@FXML
	private Button play;
	
	@FXML
	private Button stop;
	
	private GameOfLife gol;
	private Timeline timeline;
	
	public GameOfLifeController() {
		gol = new GameOfLife();
		timeline  = new Timeline(); 
		timeline.setCycleCount(Timeline.INDEFINITE);
		EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                 gol.tick();
            }
        };
		KeyFrame playFrame = new KeyFrame(Duration.millis(500), onFinished);
		timeline.getKeyFrames().add(playFrame);
	}
	
	@FXML
	public void handleTick(ActionEvent event) {
		Random rand = new Random();
		gol.ensureAlive(((rand.nextInt((9 + 0) + 1) + 0)), ((rand.nextInt((9 + 0) + 1) + 0)));
	}
	
	@FXML
	public void handlePlay(ActionEvent event) {
//		gol.tick();
		timeline.play();
	}
	
	@FXML
	public void handleStop(ActionEvent event) {
		timeline.stop();
	}
	
	@FXML
	public void initialize() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j< 10; j++) {
				CheckBox checkBox = new CheckBox("");
//				checkBox.setTextFill(Color.WHITE);
		        checkBox.setAlignment(Pos.CENTER);
		        checkBox.selectedProperty().bindBidirectional(gol.cellProperty(i, j));
				squares.add(checkBox, i, j);
			}
		}
//		squares.setAlignment(Pos.TOP_CENTER);
	}
}

