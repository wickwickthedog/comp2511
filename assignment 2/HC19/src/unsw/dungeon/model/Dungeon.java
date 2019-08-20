/**
 *
 */
package unsw.dungeon.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 * @author Harvey
 */
public class Dungeon {
	
    private int width, height;
    private List<Entity> entities;
    private List<Obstacle> obstacles;
    private List<Item> items;
    private List<Enemy> enemies;
    private Player player;
    private boolean hasEnemies;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.obstacles = new ArrayList<Obstacle>();
        this.items = new ArrayList<Item>();
        this.enemies = new ArrayList<Enemy>();
        this.player = null;
        this.hasEnemies = false;
    }

    /**
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * set player to dungeon
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    /**
	 * @return the hasEnemies
	 */
	public boolean isHasEnemies() {
		return hasEnemies;
	}

	/**
	 * @param hasEnemies the hasEnemies to set
	 */
	public void setHasEnemies(boolean hasEnemies) {
		this.hasEnemies = hasEnemies;
	}

	/**
     * add entity to entities
     * @param entity
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    /**
     * remove entity from entities
     * @param entity
     */
    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    }
    
    /**
     * add obstacle to obstacles
     * @param obstacle
     */
    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }
    
    /**
     * remove obstacle from obstacles
     * @param obstacle
     */
    public void removeObstacles(Obstacle obstacle) {
    	obstacles.remove(obstacle);
//    	obstacle = null;
//    	if (obstacle.getName().equals("door")) {
//    		for (ImageView e: dcl.getEntities()) {
//    			if (e.getX() == obstacle.getX() && e.getY() == obstacle.getY() && obstacle.isTriggered(this)) 
//    				e = new ImageView("/open_door.png");
//    		}
//    		
//    	}
    	obstacle.x().set(getNumObstaclesByName("wall"));
    	obstacle.y().set(getNumObstaclesByName("wall"));
    }
    
    /**
     * add item to items
     * @param item
     */
    public void addItem(Item item) {
        items.add(item);
    }
    
    /**
     * remove item from items
     * @param item
     */
    public void removeItem(Item item) {
        items.remove(item);
//        item = null;
        item.x().set(getNumObstaclesByName("wall"));
        item.y().set(getNumObstaclesByName("wall"));
    }
    
    /**
     * add enemy to enemies
     * @param enemy
     */
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }
    
    /**
     * remove enemy from enemies
     * @param enemy
     */
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
        enemy.x().set(getNumObstaclesByName("wall"));
        enemy.y().set(getNumObstaclesByName("wall"));
    }
    
    /**
     * @return size of enemies
     */
    public int getEnemies() {
    	return enemies.size();
    }
    
    /**
     * @param x
     * @param y
     * @return entity at (x, y)
     */
    public Entity getEntity(int x, int y) {
    	for (Entity e: entities) {
    		if (e.getX() == x && e.getY() == y) {
    			return e;
    		}
    	}
    	return null;
    }
    
    /**
     * @param x
     * @param y
     * @return obstacle at (x, y)
     */
    public Obstacle getObstacle(int x, int y) {
    	for (Obstacle o: obstacles) {
    		if (o.getX() == x && o.getY() == y) {
    			return o;
    		}
    	}
    	return null;
    }

    /**
     * for boulder on floor switch checking
     * @param x
     * @param y
     * @param name
     * @return obstacle named name at (x, y)
     */
    public Obstacle getObstacles(int x, int y, String name) {
    	Obstacle stackedObstacles = null;
    	for (Obstacle o: obstacles) {
    		if (stackedObstacles != null) break;
    		if (o.getName().equals(name) && o.getX() == x && o.getY() == y) {
    			stackedObstacles = o;
    			System.out.println("I am a <" + o.getName() + ">");
    		}
    	}
    	return stackedObstacles;
    }
    
    /**
     * @param name
     * @return obstacle named name
     */
    public ArrayList<Obstacle> getObstacles(String name) {
    	ArrayList<Obstacle> stackedObstacles = new ArrayList<Obstacle>();
    	for (Obstacle o: obstacles) {
    		if (o.getName().equals(name)) {
    			stackedObstacles.add(o);
    		}
    	}
    	return stackedObstacles;
    }
    
    /**
     * @param x
     * @param y
     * @return enemy at (x, y)
     */
    public Enemy getEnemy(int x, int y) {
    	for (Enemy e: enemies) {
    		if (e.getX() == x && e.getY() == y) {
    			return e;
    		}
    	}
    	return null;
    }
    
    /**
     * @param x
     * @param y
     * @return item at (x, y)
     */
    public Item getItem(int x, int y) {
    	for (Item i: items) {
    		if (i.getX() == x && i.getY() == y) {
    			return i;
    		}
    	}
    	return null;
    }

    /**
     * @param x
     * @param y
     * @param name
     * @return item named name at (x , y)
     */
    public Item getItem(int x, int y, String name) {
        Item stackedItems = null;
        for (Item i: items) {
            if (stackedItems != null) break;
            if (i.getName().equals(name) && i.getX() == x && i.getY() == y) {
                stackedItems = i;
                System.out.println("I am a <" + i.getName() + ">");
            }
        }
        return stackedItems;
    }
    
    /**
     * @param x
     * @param y
     * @param name
     * @param bombstate
     * @return litbomb
     */
    public Item getLitBomb(int x, int y, String name, BombState bombstate) {
    	for (Item i: items) {
    		if (i.getX() == x && i.getY() == y && i.getName().equals(name) && i.getBombState().equals(bombstate)) {
    			return i;
    		}
    	}
    	return null;
    }
    
    /**
     * @param name
     * @return number of item named name
     */
    public int getNumItemByName(String name) {
    	ArrayList<Item> item = new ArrayList<Item>();
    	for (Item i: items) {
    		if (i.getName().equals(name)) {
    			item.add(i);
    		}
    	}
    	return item.size();
    }
    
    /**
     * @param name
     * @return number of obstacle named name
     */
    public int getNumObstaclesByName(String name) {
    	ArrayList<Obstacle> obstacle = new ArrayList<Obstacle>();
    	for (Obstacle o: obstacles) {
    		if (o.getName().equals(name)) {
    			obstacle.add(o);
    		}
    	}
    	return obstacle.size();
    }

	/**
	 * @return the obstacles
	 */
	public List<Obstacle> getListofObstacles() {
		return obstacles;
	}

	/**
	 * @return the enemies
	 */
	public List<Enemy> getListofEnemies() {
		return enemies;
	}
	
}
