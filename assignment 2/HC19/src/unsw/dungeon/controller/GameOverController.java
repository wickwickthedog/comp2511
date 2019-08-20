package unsw.dungeon.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import unsw.dungeon.view.DungeonScreen;
import unsw.dungeon.view.LevelScreen;

public class GameOverController {

	@FXML
    private Button tryAgain;
    
    @FXML
    private Button mainMenu;
    
    private LevelScreen levelScreen;
    
    private DungeonScreen dungeonScreen;
    
    @FXML
    public void handleTryAgain(ActionEvent event) throws IOException {
    	if (dungeonScreen != null) {
    		DungeonScreen newDungeon = new DungeonScreen(dungeonScreen.getStage(), dungeonScreen.getTitle(), dungeonScreen.getPath());
    		newDungeon.getController().setDungeonScreen(newDungeon);
    		newDungeon.getController().setLevelScreen(levelScreen);
    		newDungeon.start();
        	System.out.println("-----DUNGEON RESET SUCCESSFUL!-----");
    	} else System.out.println("-----DUNGEON RESET FAIL!-----");
    }
    
    @FXML
    public void handleMainMenu(ActionEvent event) throws IOException {
    	this.levelScreen.start();
    }
    
    /**
	 * @param levelScreen the levelScreen to set
	 */
	public void setLevelScreen(LevelScreen levelScreen) {
		this.levelScreen = levelScreen;
	}
    
    /**
	 * @param dungeonScreen the dungeonScreen to set
	 */
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}

}
