package unsw.dungeon.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unsw.dungeon.controller.StartController;

/**
 * javafx start screen
 * @author Harvey
 */
public class StartScreen {

	private Stage stage;
	private String title;
	private StartController controller;
	private Scene scene;
	
	public StartScreen(Stage stage) throws IOException {
		this.stage = stage;
		this.title = "Main Menu-主选单";
		
		controller = new StartController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("StartView.fxml"));
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
	 * @return the stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * @return the controller
	 */
	public StartController getController() {
		return controller;
	}
}
