package unsw.dungeon.model;

/**
 * The boulder obstacle
 * @author Harvey
 */
public class Boulder extends Obstacle{

	private final String name = "boulder";
	
	public Boulder(int x, int y) {
		super(x, y);
	}

	/**
	 * @return name
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * boulder move up
	 * @param dungeon
	 * @param player
	 */
	private void moveUp(Dungeon dungeon, Player player) {
		if (getY() > 0 && checkColumn(dungeon, player, getY() - 1)) {
			y().set(getY() - 1);
			player.moveUp();
			System.out.println("PUSHED " + this.name + " to [" + this.getX() + ", " + this.getY() + "]");
		}
		else System.out.println("BLOCKED! CAN'T PUSH " + this.name + " @ [" + this.getX() + ", " + this.getY() + "]");	
	}
	
	/**
	 * boulder move down
	 * @param dungeon
	 * @param player
	 */
	private void moveDown(Dungeon dungeon, Player player) {
		if (getY() < dungeon.getHeight() - 1 && checkColumn(dungeon, player, getY() + 1)) {
			y().set(getY() + 1);
			player.moveDown();
			System.out.println("PUSHED " + this.name + " to [" + this.getX() + ", " + this.getY() + "]");
		}
		else System.out.println("BLOCKED! CAN'T PUSH " + this.name + " @ [" + this.getX() + ", " + this.getY() + "]");	
	}

	/**
	 * boulder move left
	 * @param dungeon
	 * @param player
	 */
	private void moveLeft(Dungeon dungeon, Player player) {
		if (getX() > 0 && checkRow(dungeon, player, getX() - 1)) {
			x().set(getX() - 1);
			player.moveLeft();
			System.out.println("PUSHED " + this.name + " to [" + this.getX() + ", " + this.getY() + "]");
		}
		else System.out.println("BLOCKED! CAN'T PUSH " + this.name + " @ [" + this.getX() + ", " + this.getY() + "]");
	}
	
	/**
	 * boulder move right
	 * @param dungeon
	 * @param player
	 */
	private void moveRight(Dungeon dungeon, Player player) {
		if (getX() < dungeon.getWidth() - 1 && checkRow(dungeon, player, getX() + 1)) {
			x().set(getX() + 1);
			player.moveRight();
			System.out.println("PUSHED " + this.name + " to [" + this.getX() + ", " + this.getY() + "]");
		}
		else System.out.println("BLOCKED! CAN'T PUSH" + this.name + " @ [" + this.getX() + ", " + this.getY() + "]");
	}
	
	/**
	 * checks player position 
	 * @param player
	 * @return player's position
	 */
	private String checkPlayerPosition(Player player) {
		if (player.getX() == this.getX() && player.getY() - 1 == this.getY())
			return "bottom";
		if (player.getX() == this.getX() && player.getY() + 1 == this.getY())
			return "top";
		if (player.getX() - 1 == this.getX() && player.getY() == this.getY())
			return "right";
		if (player.getX() + 1 == this.getX() && player.getY() == this.getY())
			return "left";
		return null;
	}
	
	/**
     * Check Row of immediate left or right
     * @param x coordinate
     * @return true if no collision 
     * else false
     */
    private boolean checkRow(Dungeon dungeon, Player player, int x) {
    	Enemy enemy = dungeon.getEnemy(x, this.getY());
    	Obstacle obstacle = dungeon.getObstacle(x, this.getY());
    	if (enemy != null) {
    		System.out.println("enemy @ [" + x + ", " +  this.getY() + "]");
    		return false; // collision
    	}
    	if (obstacle != null) {
    		obstacle.collision(dungeon, player);
    		if (obstacle.isTriggered(dungeon)) return false; // collision
    		if (obstacle.getName().equals("switch")) return true; 
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
    	Enemy enemy = dungeon.getEnemy(this.getX(), y);
    	Obstacle obstacle = dungeon.getObstacle(this.getX(), y);
    	if (enemy != null) {
    		System.out.println("enemy @ [" + this.getX() + ", " +  y + "]");
    		return false; // collision
    	}
    	if (obstacle != null) {
    		System.out.println(obstacle.getName() + " @ [" + this.getX() + ", " +  y + "]");
    		obstacle.collision(dungeon, player);
    		if (obstacle.isTriggered(dungeon)) return false; // collision
    		if (obstacle.getName().equals("switch")) return true; 
    		return false; // collision
    	}
    	return true; // no collision
    }

	/**
	 * condition to move boulder when
	 * on floor switch
	 * @return false 
	 */
	@Override
	public boolean isTriggered(Dungeon dungeon) {
		return false;
	}

	/**
	 * updates and checks if floor switch is triggered
	 */
	@Override
	public void refresh(Dungeon dungeon, Player player) {
		Obstacle obstacle = dungeon.getObstacles(getX(), getY(), "switch");
		if (obstacle != null) {
			System.out.println("---UPDATING---");
			obstacle.collision(dungeon, player);
		}
	}

	@Override
	public boolean door(Dungeon dungeon, Player player) {
		return true;
	}

	@Override
	public int getId() {
		return -1;
	}

	@Override
	public void setIsUnlocked() {
		// Auto-generated method stub
	
	}

	/**
	 * checks player position
	 * then updates position
	 */
	@Override
	public void collision(Dungeon dungeon, Player player) {
		// DONE Auto-generated method stub
		if (checkPlayerPosition(player) != null) {
			if (checkPlayerPosition(player).equals("bottom"))
				moveUp(dungeon, player);
			if (checkPlayerPosition(player).equals("top"))  
				moveDown(dungeon, player);
			if (checkPlayerPosition(player).equals("left")) 
				moveRight(dungeon, player);
			if (checkPlayerPosition(player).equals("right")) 
				moveLeft(dungeon, player);
		}
	}
	
}
