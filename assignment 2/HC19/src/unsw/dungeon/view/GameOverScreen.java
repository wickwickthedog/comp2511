package unsw.dungeon.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unsw.dungeon.controller.GameOverController;

public class GameOverScreen {

	private Stage stage;
	private String title;
	private GameOverController controller;
	private Scene scene;
	
	public GameOverScreen(Stage stage) throws IOException {
		this.stage = stage;
		this.title = "Game Over - 游戏结束";
		
		controller = new GameOverController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOverView.fxml"));
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
	public GameOverController getController() {
		return controller;
	}
}
