package unsw.dungeon.model;

/**
 * The sword item
 * @author Harvey
 */
public class Sword extends Item{

	private final String name = "sword";
	
	public Sword(int x, int y) {
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
	 * swing up
	 * @param dungeon
	 * @param player
	 */
	private void swingUp(Dungeon dungeon, Player player) {
		if (getY() > 0 && checkColumn(dungeon, player, getY() - 1)) {
			System.out.println("swing " + this.name + " to [" + this.getX() + ", " + (this.getY() - 1) + "]");
		}
		else System.out.println("HIT @ [" + this.getX() + ", " + (this.getY() - 1) + "]");	
	}
	
	/**
	 * swing down
	 * @param dungeon
	 * @param player
	 */
	private void swingDown(Dungeon dungeon, Player player) {
		if (getY() < dungeon.getHeight() - 1 && checkColumn(dungeon, player, getY() + 1)) {
			System.out.println("swing " + this.name + " to [" + this.getX() + ", " + (this.getY() + 1) + "]");
		}
		else System.out.println("HIT @ [" + this.getX() + ", " + (this.getY() + 1) + "]");	
	}

	/**
	 * swing left
	 * @param dungeon
	 * @param player
	 */
	private void swingLeft(Dungeon dungeon, Player player) {
		if (getX() > 0 && checkRow(dungeon, player, getX() - 1)) {
			System.out.println("swing " + this.name + " to [" + (this.getX() - 1) + ", " + this.getY() + "]");
		}
		else System.out.println("HIT @ [" + (this.getX() - 1) + ", " + this.getY() + "]");
	}
	
	/**
	 * swing right
	 * @param dungeon
	 * @param player
	 */
	private void swingRight(Dungeon dungeon, Player player) {
		if (getX() < dungeon.getWidth() - 1 && checkRow(dungeon, player, getX() + 1)) {
			System.out.println("swing " + this.name + " to [" + (this.getX() + 1) + ", " + this.getY() + "]");
		}
		else System.out.println("HIT @ [" + (this.getX() + 1) + ", " + this.getY() + "]");
	}
	
	/**
     * Check Row of immediate left or right
     * @param x coordinate
     * @return true if no collision 
     * else false
     */
    private boolean checkRow(Dungeon dungeon, Player player, int x) {
    	Enemy enemy = dungeon.getEnemy(x, this.getY());
    	if (enemy != null) {
    		System.out.println("enemy @ [" + x + ", " +  this.getY() + "]");
    		dungeon.removeEnemy(enemy);
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
    	if (enemy != null) {
    		System.out.println("enemy @ [" + this.getX() + ", " +  y + "]");
    		dungeon.removeEnemy(enemy);
    		return false; // collision
    	}
    	return true; // no collision
    }
	
    /**
     * swing all direction
     */
	@Override
	public void useItem(Dungeon dungeon, Player player) {
		// sword can't kill enemy because didnt update sword coordinate
//		this.x().set(player.getX());
//		this.y().set(player.getY());
		System.out.println("Attack with " + name + "!");
		// pretty much just swing around lmao need better solution
		swingRight(dungeon, player);
		swingLeft(dungeon, player);
		swingDown(dungeon, player);
		swingUp(dungeon, player);
		
		System.out.println("Dungeon has " + dungeon.getEnemies() + " enemies left!");
		if (dungeon.getEnemies() == 0) {
    		player.getPlayerState().toGameOver(player);
		} 
	}

	@Override
	public BombState getBombState() {
		return null;
	}

	@Override
	public int getId() {
		return -1;
	}

}
