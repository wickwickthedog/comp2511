package unsw.dungeon.model;

/**
 * The key item
 * @author Caleb
 * @author Harvey
 */
public class Key extends Item{
	private int id;
	private int onceRule;
	private final String name = "key";
	
	public Key(int x, int y, int id) {
		super(x, y);
		this.id = id;
		onceRule = 1;
	}
	
	/**
	 * @return onceRule
	 */
	public int getOnceRule() {
		return this.onceRule;
	}
	
	/**
	 * @return name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * unlock at top
	 * @param dungeon
	 * @param player
	 */
	private void unlockUp(Dungeon dungeon, Player player) {
		Obstacle door = dungeon.getObstacles(player.getX(), player.getY() - 1, "door");
		if (player.getY() - 1 > 0) {
			if (door == null) return;
			System.out.println("door @ [" + player.getX() + ", " +  (player.getY() - 1) + "]");
			if (door.getId() == this.getId()) {
				door.setIsUnlocked();
				onceRule--;
				dungeon.removeObstacles(door);
				System.out.println("UNLOCKED DOOR USING " + this.name + " to [" + player.getX() + ", " + (player.getY() - 1) + "]");
			} else System.out.println("Not the right key");
		}	
		else System.out.println("-- @ [" + player.getX() + ", " + (player.getY() - 1) + "]");	
	}
	
	/**
	 * unlock at bottom
	 * @param dungeon
	 * @param player
	 */
	private void unlockDown(Dungeon dungeon, Player player) {
		Obstacle door = dungeon.getObstacles(player.getX(), player.getY() + 1, "door");
		if (player.getY() + 1 < dungeon.getHeight() - 1) {
			if (door == null) return;
			System.out.println("door @ [" + player.getX() + ", " +  (player.getY() + 1) + "]");
			if (door.getId() == this.getId()) {
				door.setIsUnlocked();
				onceRule--;
				dungeon.removeObstacles(door);
				System.out.println("UNLOCKED DOOR USING " + this.name + " to [" + player.getX() + ", " + (player.getY() + 1) + "]");
			} else System.out.println("Not the right key");
		}		
		else System.out.println("-- @ [" + player.getX() + ", " + (player.getY() + 1) + "]");	
	}

	/**
	 * unlock at left
	 * @param dungeon
	 * @param player
	 */
	private void unlockLeft(Dungeon dungeon, Player player) {
		Obstacle door = dungeon.getObstacles(player.getX() - 1, player.getY(), "door");
		if (player.getX() - 1 > 0) {
			if (door == null) return;
			System.out.println("door @ [" + (player.getX() - 1) + ", " +  this.getY() + "]");
			if (door.getId() == this.getId()) {
				door.setIsUnlocked();
				onceRule--;
				dungeon.removeObstacles(door);
				System.out.println("UNLOCKED DOOR USING " + this.name + " to [" + (player.getX() - 1) + ", " + player.getY() + "]");
			} else System.out.println("Not the right key. " + "No. of times it has unlocked its door: " + onceRule);
		}
		else System.out.println("-- @ [" + (player.getX() - 1) + ", " + player.getY() + "]");
	}
	
	/**
	 * unlock at right
	 * @param dungeon
	 * @param player
	 */
	private void unlockRight(Dungeon dungeon, Player player) {
		Obstacle door = dungeon.getObstacles(player.getX() + 1, player.getY(), "door");
		if (player.getX() < dungeon.getWidth() - 1) {
			if (door == null) return;
			System.out.println("door @ [" + (player.getX() + 1) + ", " +  this.getY() + "]");
			if (door.getId() == this.getId()) {
				door.setIsUnlocked();
				onceRule--;
				dungeon.removeObstacles(door);
				System.out.println("UNLOCKED DOOR USING " + this.name + " to [" + (player.getX() + 1) + ", " + player.getY() + "]");
			} else System.out.println("Not the right key");
		} 
		else System.out.println("-- @ [" + (player.getX() + 1) + ", " + player.getY() + "]");
	}
	
	/**
     * Check Row of immediate left or right
     * @param x coordinate
     * @return true if no collision 
     * else false
     */
    private boolean checkRow(Dungeon dungeon, Player player, int x) {
    	Obstacle door = dungeon.getObstacles(x, this.getY(), "door");
    	if (door != null) {
    		System.out.println("door @ [" + x + ", " +  this.getY() + "]");
    		dungeon.removeObstacles(door);
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
    private boolean checkColumn(Dungeon dungeon, Player player, int y) {
    	Obstacle door = dungeon.getObstacles(this.getX(), y, "door");
    	if (door != null) {
    		System.out.println("door @ [" + this.getX() + ", " +  y + "]");
    		dungeon.removeObstacles(door);
    		return false; // collision
    	}
    	return true; // no collision
    }
	
    /**
     * use key to try unlock door at all direction
     */
	@Override
	public void useItem(Dungeon dungeon, Player player) {
		System.out.println("Use " + name + " " + player.getInventory().get(0).getId() + " @ [" + player.getX() + ", " + player.getY() +  "] to unlock door!");
		unlockRight(dungeon, player);
		unlockLeft(dungeon, player);
		unlockDown(dungeon, player);
		unlockUp(dungeon, player);
	}

	@Override
	public BombState getBombState() {
		return null;
	}
	
	/**
	 * @return id
	 */
	@Override
	public int getId() {
		return this.id;
	}
}
