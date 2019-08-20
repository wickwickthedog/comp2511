package unsw.dungeon.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import unsw.dungeon.model.Bomb;
import unsw.dungeon.model.Boulder;
import unsw.dungeon.model.Door;
import unsw.dungeon.model.Enemy;
import unsw.dungeon.model.Entity;
import unsw.dungeon.model.Exit;
import unsw.dungeon.model.FloorSwitch;
import unsw.dungeon.model.InvincibilityPotion;
import unsw.dungeon.model.Key;
import unsw.dungeon.model.Sword;
import unsw.dungeon.model.Treasure;
import unsw.dungeon.model.Wall;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image exitImage;
    private Image boulderImage;
    private Image switchImage;
    private Image swordImage;
    private Image bombImage;
    private Image enemyImage;
    private Image treasureImage;
    private Image invincibilityImage;
    private Image doorImage;
    private Image keyImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/deep_elf_master_archer.png");
        wallImage = new Image("/brick_brown_0.png");
        exitImage = new Image("/exit.png");
        boulderImage = new Image("/boulder.png");
        switchImage = new Image("/pressure_plate.png");
        swordImage = new Image("/greatsword_1_new.png");
        bombImage = new Image("/bomb_unlit.png");
        enemyImage = new Image("/hound.png");
        treasureImage = new Image("/gold_pile.png");
        invincibilityImage = new Image("/bubbly.png");
        doorImage = new Image("/closed_door.png");
        keyImage = new Image("/key.png");
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }

    @Override
	public void onLoad(Exit exit) {
		ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
	}
    
    @Override
	public void onLoad(Boulder boulder) {
		ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
	}
    
    @Override
	public void onLoad(FloorSwitch floorSwitch) {
		ImageView view = new ImageView(switchImage);
        addEntity(floorSwitch, view);
	}
    
    @Override
	public void onLoad(Sword sword) {
		ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
	}
    
    @Override
	public void onLoad(Bomb bomb) {
		ImageView view = new ImageView(bombImage);
        addEntity(bomb, view);
	}
    
    @Override
	public void onLoad(Enemy enemy) {
		ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view);
	}
    
    @Override
	public void onLoad(Treasure treasure) {
		ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
	}
    
    @Override
	public void onLoad(InvincibilityPotion invincibility) {
		ImageView view = new ImageView(invincibilityImage);
        addEntity(invincibility, view);
	}

    @Override
    public void onLoad(Door door) {
        ImageView view = new ImageView(doorImage);
        addEntity(door, view);
    }
    
    @Override
    public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        addEntity(key, view);
    }

    /**
     * Adds the entity and image
     * set both to be tracked
     * @param entity
     * @param view
     */
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }
    
    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
    	GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }

}
