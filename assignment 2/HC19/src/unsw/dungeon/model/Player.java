package unsw.dungeon.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The player entity
 * @author Robert Clifton-Everest
 * @author Harvey
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private PlayerState playerState;
    private List<Item> inventory;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.playerState = new VulnerableState();
        this.inventory = new ArrayList<Item>();
    }

    /**
     * move up
     */
    public void moveUp() {
        if (!playerState.equals(new GameOverState()) && getY() > 0)
        	if (checkColumn(getY() - 1)) {
        		y().set(getY() - 1);
        		checkCurrentSpot();
        		addItem();
        	}
    }

    /**
     * move down
     */
    public void moveDown() {
        if (!playerState.equals(new GameOverState()) && getY() < dungeon.getHeight() - 1)
        	if (checkColumn(getY() + 1)) {
        		y().set(getY() + 1);
        		checkCurrentSpot();
        		addItem();
        	}
    }

    /**
     * move left
     */
    public void moveLeft() {
        if (!playerState.equals(new GameOverState()) && getX() > 0)
        	if (checkRow(getX() - 1)) {
        		x().set(getX() - 1);
        		checkCurrentSpot();
        		addItem();
        	}
    }

    /**
     * move right
     */
    public void moveRight() {
        if (!playerState.equals(new GameOverState()) && getX() < dungeon.getWidth() - 1)
        	if (checkRow(getX() + 1)) {
        		x().set(getX() + 1);
        		checkCurrentSpot();
        		addItem();
        	}
    }
    
    /**
     * checks current spot
     */
    private void checkCurrentSpot() {
    	Enemy enemy = dungeon.getEnemy(this.getX(), this.getY());
    	if (enemy != null) {
    		enemy.killPlayer(dungeon);
    	}
    }
    
    /**
     * Check Row of immediate left or right
     * @param x coordinate
     * @return true if no collision 
     * else false
     */
    private boolean checkRow(int x) {
    	Enemy enemy = dungeon.getEnemy(x, this.getY());
    	Obstacle obstacle = dungeon.getObstacle(x, this.getY());
    	Obstacle stacked = dungeon.getObstacles(x, this.getY(), "boulder");
    	Item litBomb = dungeon.getLitBomb(x, this.getY(), "bomb", new LitBomb());
    	if (litBomb != null) {
    		System.out.println("lit bomb @ [" + x + ", " +  this.getY() + "]");
    		return false; // collision
    	}
    	if (enemy != null) {
    		System.out.println("enemy @ [" + x + ", " +  this.getY() + "]");
    		return true; // can collision
    	}
    	if (obstacle != null) {
    		System.out.println(obstacle.getName() + " @ [" + x + ", " +  this.getY() + "]");
    		obstacle.collision(dungeon, this);
    		obstacle.refresh(dungeon, this);
    		if (obstacle.isTriggered(dungeon)) {
    			obstacle.collision(dungeon, this);
    			if (stacked != null) stacked.collision(dungeon, this);
    			return false; // collision
    		}
    		if (obstacle.getName().equals("exit") || obstacle.getName().equals("switch")) return true; // end game || trigger switch
    		if (obstacle.door(dungeon, this)) return false;
    		else if (!obstacle.door(dungeon, this)) return true;
    		return false; // collision
    	}
    	return true; // no collision
    }
    
    /**
     * Check column of immediate up or down
     * @param y coordinate
     * @return true if no collision 
     * else false
     */
    private boolean checkColumn(int y) {
    	Enemy enemy = dungeon.getEnemy(this.getX(), y);
    	Obstacle obstacle = dungeon.getObstacle(this.getX(), y);
    	Obstacle stacked = dungeon.getObstacles(this.getX(), y, "boulder");
    	Item litBomb = dungeon.getLitBomb(this.getX(), y, "bomb", new LitBomb());
    	if (litBomb != null) {
    		System.out.println("lit bomb @ [" + this.getX() + ", " +  y + "]");
    		return false; // collision
    	}
    	if (enemy != null) {
    		System.out.println("enemy @ [" + this.getX() + ", " +  y + "]");
    		return true; // can collision
    	}
    	if (obstacle != null) {
    		System.out.println(obstacle.getName() + " @ [" + this.getX() + ", " +  y + "]");
    		obstacle.collision(dungeon, this);
    		obstacle.refresh(dungeon, this);
    		if (obstacle.isTriggered(dungeon)) {
    			obstacle.collision(dungeon, this);
    			if (stacked != null) stacked.collision(dungeon, this);
    			return false; // collision
    		}
    		if (obstacle.getName().equals("exit") || obstacle.getName().equals("switch")) return true; // end game || trigger switch
    		if (obstacle.door(dungeon, this)) return false;
    		else if (!obstacle.door(dungeon, this)) return true;
    		return false; // collision
    	}
    	return true; // no collision
    }

	/**
	 * @return the playerState
	 */
	public PlayerState getPlayerState() {
		return playerState;
	}

	/**
	 * @param playerState the playerState to set
	 */
	public void setPlayerState(PlayerState playerState) {
		this.playerState = playerState;
	}
	
	/**
	 * @return the inventory
	 */
	public List<Item> getInventory() {
		return inventory;
	}
	
	/**
	 * @return the first item in inventory
	 */
	public Item getItem() {
		if (inventory.isEmpty()) {
			System.out.println("inventory is EMPTY!");
			return null;
		}
		else return inventory.get(0);
	}

	/**
	 * @param inventory the item to add
	 */
	public void addItem() {
		Item itemOnGround = dungeon.getItem(this.getX(), this.getY());
		if(itemOnGround != null) {
			if (itemOnGround.getName().equals("key")) {
				for (Item item : inventory) {
					if (item.getName().equals("key")) return;
				}
			}
			this.inventory.add(itemOnGround);
			dungeon.removeItem(itemOnGround);
			if (itemOnGround.getId() > 0) System.out.println("Obtained " + itemOnGround.getName() + " with id: "+ itemOnGround.getId() + "!");
			else System.out.println("Obtained " + itemOnGround.getName() + "!");
			if(itemOnGround.getName().equals("treasure")) {
				itemOnGround.useItem(dungeon, this);
				if (this.playerState.equals(new GameOverState())) {
					this.playerState.toGameOver(this);
				}
			}
		}
	}
    
	/**
	 * @param inventory the item to del
	 */
	public void removeItem(Item item) {
		this.inventory.remove(item);
	}
	
	/**
	 * use the first item in the inventory
	 * by pressing the SPACE BAR
	 */
	public void useItem() {
		if (getItem() != null) {
			getItem().useItem(dungeon, this);
			if (getItem().getName().equals("bomb") || getItem().getName().equals("invincibility") || getItem().getName().equals("key")) {
				if (getItem().getName().equals("invincibility"))
					dungeon.removeItem(getItem());
				else if (getItem().getName().equals("key")) {
					Key key = (Key) getItem();
					if (key.getOnceRule() == 0) {
						dungeon.removeItem(getItem());
					} else if (key.getOnceRule() == 1) {
						return;
					}
				}
					
				removeItem(getItem());
			}
		} else System.out.println("can't use ITEM!");
	}
	
	public void displayInventory() {
		System.out.println("---OPEN INVENTORY---");
		for (Item i: inventory) {
			System.out.println(i.getName());
		}
		System.out.println("---CLOSE INVENTORY---");
	}
	
	/**
	 * swap items in the inventory
	 * by pressing C key
	 */
	public void rotateInventory() {
		if (inventory.isEmpty() || inventory.size() == 1) {
			System.out.println("Can't rotate not enough item in inventory!");
			return;
		}
		for (int i = 0; i < inventory.size(); i++) {
			int j = i + 1;
			if (j == inventory.size()) break;
			Collections.swap(inventory, i, j);
		}
	}
	
	/**
	 * remove the first item in the inventory
	 * by pressing X key
	 */
	public void removeFirstItem() {
		if (inventory.isEmpty()) System.out.println("Inventory issa empty!");
		else {
			getItem().x().set(getX());
			getItem().y().set(getY());
			dungeon.addItem(getItem());
			System.out.println("Dropped " + getItem().getName() + " @ [" + getItem().getX() + ", " + getItem().getY() + "]");
			inventory.remove(0);
		}
	}
}
