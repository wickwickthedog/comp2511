package unsw.dungeon.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.Enemy;
import unsw.dungeon.model.GameOverState;
import unsw.dungeon.model.Player;
import unsw.dungeon.view.DungeonScreen;
import unsw.dungeon.view.GameOverScreen;
import unsw.dungeon.view.LevelScreen;

/**
 * A JavaFX controller for the dungeon screen.
 * @author Robert Clifton-Everest
 * @author Harvey
 */
public class DungeonController {

	@FXML
	private SplitPane sPane;
	
    @FXML
    private GridPane squares;

    @FXML
    private Button reset;
    
    @FXML
    private Button back;
    
    @FXML
    private Label equiped;
    
    private LevelScreen levelScreen;
    
    private DungeonScreen dungeonScreen;
    
    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    /**
     * create a DungeonController object
     * @param dungeon
     * @param initialEntities
     */
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    /**
     * init the gridpane
     */
    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
    }

    /**
     * detects key press on keyboard and 
     * updates entities in gridpane
     * @param event
     */
    @FXML
    public void handleKeyPress(KeyEvent event) {
    	if (player.getPlayerState().equals(new GameOverState())) {
    		levelScreen.getController().getGameOverScreen().getController().setDungeonScreen(dungeonScreen);
    		levelScreen.getController().getGameOverScreen().start();
    	}
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            System.out.println("move UP @ " + player.getX() + ", " + player.getY());
            if (!dungeon.getListofEnemies().isEmpty()) {
            	for (Enemy e : dungeon.getListofEnemies()) {
            		e.enemyMove(dungeon);
            	}
            }
            if (player.getInventory().isEmpty()) equiped.setText("EMPTY");
        	else equiped.setText(player.getItem().getName());
//            if (!dungeon.getListofEnemies().isEmpty()) dungeon.getListofEnemies().get(0).displayMap(dungeon);
            break;
        case DOWN:
        	player.moveDown();
            System.out.println("move DOWN @ " + player.getX() + ", " + player.getY());
            if (!dungeon.getListofEnemies().isEmpty()) {
            	for (Enemy e : dungeon.getListofEnemies()) {
            		e.enemyMove(dungeon);
            	}
            }
            if (player.getInventory().isEmpty()) equiped.setText("EMPTY");
        	else equiped.setText(player.getItem().getName());
//            if (!dungeon.getListofEnemies().isEmpty()) dungeon.getListofEnemies().get(0).displayMap(dungeon);
            break;
        case LEFT:
            player.moveLeft();
            System.out.println("move LEFT @ " + player.getX() + ", " + player.getY());
            if (!dungeon.getListofEnemies().isEmpty()) {
            	for (Enemy e : dungeon.getListofEnemies()) {
            		e.enemyMove(dungeon);
            	}
            }
            if (player.getInventory().isEmpty()) equiped.setText("EMPTY");
        	else equiped.setText(player.getItem().getName());
//            if (!dungeon.getListofEnemies().isEmpty()) dungeon.getListofEnemies().get(0).displayMap(dungeon);
            break;
        case RIGHT:
        	player.moveRight();
            System.out.println("move RIGHT @ " + player.getX() + ", " + player.getY());
            if (!dungeon.getListofEnemies().isEmpty()) {
            	for (Enemy e : dungeon.getListofEnemies()) {
            		e.enemyMove(dungeon);
            	}
            }
            if (player.getInventory().isEmpty()) equiped.setText("EMPTY");
        	else equiped.setText(player.getItem().getName());
//            if (!dungeon.getListofEnemies().isEmpty()) dungeon.getListofEnemies().get(0).displayMap(dungeon);
            break;
        case SPACE:
        	player.useItem();
        	player.displayInventory();
        	if (player.getInventory().isEmpty()) equiped.setText("EMPTY");
        	else equiped.setText(player.getItem().getName());
        	break;
        case C: 
        	player.rotateInventory();
        	player.displayInventory();
        	if (player.getInventory().isEmpty()) equiped.setText("EMPTY");
        	else equiped.setText(player.getItem().getName());
        	break;
        case X:
        	player.removeFirstItem();
        	player.displayInventory();
        	if (player.getInventory().isEmpty()) equiped.setText("EMPTY");
        	else equiped.setText(player.getItem().getName());
        	break;
        default:
        	System.out.println("Press Arrow Keys");
            break;
        }
    }

    /**
     * resets the dungeon to initial state
     * @param event
     * @throws IOException
     */
    @FXML
    public void handleReset(ActionEvent event) throws IOException {
    	if (dungeonScreen != null) {
    		DungeonScreen newDungeon = new DungeonScreen(dungeonScreen.getStage(), dungeonScreen.getTitle(), dungeonScreen.getPath());
    		newDungeon.getController().setDungeonScreen(newDungeon);
    		newDungeon.getController().setLevelScreen(levelScreen);
    		
    		newDungeon.start();
        	System.out.println("-----DUNGEON RESET SUCCESSFUL!-----");
    	} else System.out.println("-----DUNGEON RESET FAIL!-----");
    }

    /**
     * returns to level screen
     * @param event
     */
    @FXML
	public void handleBack(ActionEvent event) {
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

