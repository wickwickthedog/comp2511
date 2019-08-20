package unsw.dungeon.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import unsw.dungeon.model.Bomb;
import unsw.dungeon.model.Boulder;
import unsw.dungeon.model.Door;
import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.Enemy;
import unsw.dungeon.model.Entity;
import unsw.dungeon.model.Exit;
import unsw.dungeon.model.FloorSwitch;
import unsw.dungeon.model.InvincibilityPotion;
import unsw.dungeon.model.Key;
import unsw.dungeon.model.Player;
import unsw.dungeon.model.Sword;
import unsw.dungeon.model.Treasure;
import unsw.dungeon.model.Wall;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {
    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }

    /**
     * loads dungeon in json files 
     * @param dungeon
     * @param json
     */
    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            dungeon.addObstacle(wall);
            break;
            
        // TODO Handle other possible entities
        case "exit":
        	Exit exit = new Exit(x, y);
        	onLoad(exit);
        	dungeon.addObstacle(exit);
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(x, y);
        	onLoad(boulder);
        	dungeon.addObstacle(boulder);
        	break;
        case "switch":
        	FloorSwitch floorSwitch = new FloorSwitch(x, y);
        	onLoad(floorSwitch);
        	dungeon.addObstacle(floorSwitch);
        	break;
        case "sword":
        	Sword sword = new Sword(x, y);
        	onLoad(sword);
        	dungeon.addItem(sword);
        	break;
        case "bomb":
        	Bomb bomb = new Bomb(x, y);
        	onLoad(bomb);
        	dungeon.addItem(bomb);
        	break;
        case "enemy":
        	Enemy enemy = new Enemy(x, y);
        	onLoad(enemy);
        	dungeon.addEnemy(enemy);
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x, y);
        	onLoad(treasure);
        	dungeon.addItem(treasure);
        	break;
        case "invincibility":
        	InvincibilityPotion invincibility = new InvincibilityPotion(x, y);
        	onLoad(invincibility);
        	dungeon.addItem(invincibility);
        	break;
        case "door":
        	Door door = new Door(x, y, 1 + dungeon.getNumObstaclesByName("door"));
        	onLoad(door);
        	dungeon.addObstacle(door);
        	break;
        case "key":
        	Key key = new Key(x, y, 1 + dungeon.getNumItemByName("key"));
        	onLoad(key);
    		dungeon.addItem(key);
        	break;
        }
        if (entity != null) dungeon.addEntity(entity);
        if (!dungeon.getListofEnemies().isEmpty()) dungeon.setHasEnemies(true);
    }

	public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    // DONE Create additional abstract methods for the other entities
    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(FloorSwitch floorSwitch);

	public abstract void onLoad(Sword sword);
	
	public abstract void onLoad(Bomb bomb);
	
	public abstract void onLoad(Enemy enemy);
	
	public abstract void onLoad(Treasure treasure);
	
	public abstract void onLoad(InvincibilityPotion invincibility);
	
	public abstract void onLoad(Door door);
	
	public abstract void onLoad(Key key);
}
