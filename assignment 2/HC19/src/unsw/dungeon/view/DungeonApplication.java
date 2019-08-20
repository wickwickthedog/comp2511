package unsw.dungeon.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unsw.dungeon.controller.DungeonController;
import unsw.dungeon.controller.DungeonControllerLoader;

/**
 * javafx application
 * @author Harvey
 */
public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

    	StartScreen startScreen = new StartScreen(primaryStage);
    	LevelScreen levelScreen = new LevelScreen(primaryStage);
    	GameOverScreen gameOverScreen = new GameOverScreen(primaryStage);
    	startScreen.getController().setLevelScreen(levelScreen);
    	levelScreen.getController().setStartScreen(startScreen);
    	levelScreen.getController().setLevelScreen(levelScreen);
    	levelScreen.getController().setGameOverScreen(gameOverScreen);
    	gameOverScreen.getController().setLevelScreen(levelScreen);
    	startScreen.start();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
