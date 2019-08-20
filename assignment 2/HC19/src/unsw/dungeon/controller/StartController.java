package unsw.dungeon.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import unsw.dungeon.view.LevelScreen;

/**
 * A JavaFX controller for the start screen.
 * @author Harvey
 *
 */
public class StartController {

	@FXML
	private Button play;
	
	private LevelScreen levelScreen;
	
	/**
	 * goto level screen
	 * @param event
	 */
	@FXML
	public void handlePlay(ActionEvent event) {
		this.levelScreen.start();
		System.out.println("Play button pressed!");
	}

	/**
	 * @param levelScreen the levelScreen to set
	 */
	public void setLevelScreen(LevelScreen levelScreen) {
		this.levelScreen = levelScreen;
	}
}
