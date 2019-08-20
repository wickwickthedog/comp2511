package unsw.dungeon.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import unsw.dungeon.view.DungeonScreen;
import unsw.dungeon.view.GameOverScreen;
import unsw.dungeon.view.LevelScreen;
import unsw.dungeon.view.StartScreen;

/**
 * A JavaFX controller for the level screen.
 * @author Harvey
 *
 */
public class LevelController {

	@FXML
	private Button exit;
	
	@FXML
	private Button fswitch;
	
	@FXML
	private Button treasure;
	
	@FXML
	private Button enemy;
	
	@FXML
	private Button marking;
	
	@FXML
	private Button back;
	
	private StartScreen startScreen;
	
	private LevelScreen levelScreen;
	
	private GameOverScreen gameOverScreen;
	
	/**
	 * loads maze.json dungeon
	 * exit condition
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void handleExit(ActionEvent event) throws IOException {
		String path = "maze.json";
    	DungeonScreen dungeonScreen = new DungeonScreen(startScreen.getStage(), "Dungeon-冒险 (EXIT CONDITION)", path);
    	dungeonScreen.getController().setDungeonScreen(dungeonScreen);
    	dungeonScreen.getController().setLevelScreen(levelScreen);
    	dungeonScreen.start();
	}
	
	/**
	 * loads boulders.json dungeon
	 * floor switch condition
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void handleSwitch(ActionEvent event) throws IOException {
		String path = "boulders.json";
    	DungeonScreen dungeonScreen = new DungeonScreen(startScreen.getStage(), "Dungeon-冒险 (SWITCH CONDITION)", path);
    	dungeonScreen.getController().setDungeonScreen(dungeonScreen);
    	dungeonScreen.getController().setLevelScreen(levelScreen);
    	dungeonScreen.start();
	}
	
	/**
	 * loads treasure.json dungeon
	 * treasure condition
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void handleTreasure(ActionEvent event) throws IOException {
		String path = "treasure.json";
    	DungeonScreen dungeonScreen = new DungeonScreen(startScreen.getStage(), "Dungeon-冒险 (TREASURE CONDITION)", path);
    	dungeonScreen.getController().setDungeonScreen(dungeonScreen);
    	dungeonScreen.getController().setLevelScreen(levelScreen);
    	dungeonScreen.start();
	}
	
	/**
	 * loads enemy.json dungeon
	 * enemy condition
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void handleEnemy(ActionEvent event) throws IOException {
		String path = "enemy.json";
    	DungeonScreen dungeonScreen = new DungeonScreen(startScreen.getStage(), "Dungeon-冒险 (ENEMY CONDITION)", path);
    	dungeonScreen.getController().setDungeonScreen(dungeonScreen);
    	dungeonScreen.getController().setLevelScreen(levelScreen);
    	dungeonScreen.start();
	}
	
	/**
	 * loads marking.json dungeon
	 * any condition
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void handleMarking(ActionEvent event) throws IOException {
		String path = "marking.json";
    	DungeonScreen dungeonScreen = new DungeonScreen(startScreen.getStage(), "Dungeon-冒险 (ANY CONDITION)", path);
    	dungeonScreen.getController().setDungeonScreen(dungeonScreen);
    	dungeonScreen.getController().setLevelScreen(levelScreen);
    	dungeonScreen.start();
	}
	
	/**
	 * returns to main menu (start screen)
	 * @param event
	 */
	@FXML
	public void handleBack(ActionEvent event) {
		this.startScreen.start();
	}

	/**
	 * @param levelScreen the levelScreen to set
	 */
	public void setLevelScreen(LevelScreen levelScreen) {
		this.levelScreen = levelScreen;
	}
	
	/**
	 * @param startScreen the startScreen to set
	 */
	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
	}
	
	/**
	 * @param gameOverScreen the gameOverScreen to set
	 */
	public void setGameOverScreen(GameOverScreen gameOverScreen) {
		this.gameOverScreen = gameOverScreen;
	}

	/**
	 * @return the gameOverScreen
	 */
	public GameOverScreen getGameOverScreen() {
		return gameOverScreen;
	}
}
