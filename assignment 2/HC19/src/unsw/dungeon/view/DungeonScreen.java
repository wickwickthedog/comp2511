package unsw.dungeon.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unsw.dungeon.controller.DungeonController;
import unsw.dungeon.controller.DungeonControllerLoader;

/**
 * javafx dungeon screen
 * @author Harvey
 */
public class DungeonScreen {
	
	private Stage stage;
	private String title;
	private String path;
	private DungeonController controller;
	private Scene scene;
	
	public DungeonScreen(Stage stage, String title, String path) throws IOException{
		this.stage = stage;
		this.title = title;
		this.path = path;
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(path);
		
		controller = dungeonLoader.loadController();
		
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
          
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
	}
	
	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * @return the stage
	 */
	public Stage getStage() {
		return stage;
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return the controller
	 */
	public DungeonController getController() {
		return controller;
	}
	
}
