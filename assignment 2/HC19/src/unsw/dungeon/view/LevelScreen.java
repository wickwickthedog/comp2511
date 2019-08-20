package unsw.dungeon.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unsw.dungeon.controller.LevelController;

/**
 * javafx level screen
 * @author Harvey
 */
public class LevelScreen {

	private Stage stage;
	private String title;
	private LevelController controller;
	private Scene scene;
	
	public LevelScreen(Stage stage) throws IOException {
		this.stage = stage;
		this.title = "Level Menu-选单";
		
		controller = new LevelController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        scene = new Scene(root);
	}
	
	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @return the controller
	 */
	public LevelController getController() {
		return controller;
	}
}
