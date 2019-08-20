package unsw.dungeon.model;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The bomb item
 * @author Harvey
 */
public class Bomb extends Item{

	private final String name = "bomb";
	private BombState bombStatus;
	
	public Bomb(int x, int y) {
		super(x, y);
		// Auto-generated constructor stub
		bombStatus = new UnlitBomb();
	}

	/**
	 * @param bombStatus the bombStatus to set
	 */
	public void setBombStatus(BombState bombStatus) {
		this.bombStatus = bombStatus;
	}

	/**
	 * @return name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * explode at top
	 * @param dungeon
	 * @param player
	 */
	private void explodeUp(Dungeon dungeon, Player player) {
		if (getY() > 0 && checkColumn(dungeon, player, getY() - 1)) {
			System.out.println("explode " + this.name + " to [" + this.getX() + ", " + (this.getY() - 1) + "]");
		}
		else System.out.println("HIT @ [" + this.getX() + ", " + (this.getY() - 1) + "]");	
	}
	
	/**
	 * explode at down
	 * @param dungeon
	 * @param player
	 */
	private void explodeDown(Dungeon dungeon, Player player) {
		if (getY() < dungeon.getHeight() - 1 && checkColumn(dungeon, player, getY() + 1)) {
			System.out.println("explode " + this.name + " to [" + this.getX() + ", " + (this.getY() + 1) + "]");
		}
		else System.out.println("HIT @ [" + this.getX() + ", " + (this.getY() + 1) + "]");	
	}
	
	/**
	 * explode at left
	 * @param dungeon
	 * @param player
	 */
	private void explodeLeft(Dungeon dungeon, Player player) {
		if (getX() > 0 && checkRow(dungeon, player, getX() - 1)) {
			System.out.println("explode " + this.name + " to [" + (this.getX() - 1) + ", " + this.getY() + "]");
		}
		else System.out.println("HIT @ [" + (this.getX() - 1) + ", " + this.getY() + "]");
	}
	
	/**
	 * explode at right
	 * @param dungeon
	 * @param player
	 */
	private void explodeRight(Dungeon dungeon, Player player) {
		if (getX() < dungeon.getWidth() - 1 && checkRow(dungeon, player, getX() + 1)) {
			System.out.println("explode " + this.name + " to [" + (this.getX() + 1) + ", " + this.getY() + "]");
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
    	Entity isPlayer = dungeon.getEntity(x, this.getY());
    	Enemy enemy = dungeon.getEnemy(x, this.getY());
    	Obstacle boulder = dungeon.getObstacles(x, this.getY(), "boulder");
    	if (isPlayer != null && player.getPlayerState().equals(new VulnerableState())) {
    		System.out.println("Player @ [" + x + ", " +  this.getY() + "] dies!");
    		player.getPlayerState().toGameOver(player);
    		player.getPlayerState().toGameOver(player);
    		dungeon.setPlayer(null);
    		dungeon.removeEntity(player);
    		return false;
    	}
    	if (enemy != null) {
    		System.out.println("enemy @ [" + x + ", " +  this.getY() + "] dies!");
    		dungeon.removeEnemy(enemy);
    		return false; // collision
    	}
    	if (boulder != null) {
    		System.out.println(boulder.getName() + " @ [" + x+ ", " +  this.getY() + "] destroyed!");
    		dungeon.removeObstacles(boulder);
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
    	Entity isPlayer = dungeon.getEntity(this.getX(), y);
    	Enemy enemy = dungeon.getEnemy(this.getX(), y);
    	Obstacle boulder = dungeon.getObstacles(this.getX(), y, "boulder");
    	if (isPlayer != null && player.getPlayerState().equals(new VulnerableState())) {
    		System.out.println("Player @ [" + this.getX() + ", " +  y + "] dies!");
    		player.getPlayerState().toGameOver(player);
    		player.getPlayerState().toGameOver(player);
    		dungeon.setPlayer(null);
    		dungeon.removeEntity(player);
    		return false;
    	}
    	if (enemy != null) {
    		System.out.println("enemy @ [" + this.getX() + ", " +  y + "] dies!");
    		dungeon.removeEnemy(enemy);
    		return false; // collision
    	}
    	if (boulder != null) {
    		System.out.println(boulder.getName() + " @ [" + this.getX() + ", " +  y + "] destroyed!");
    		dungeon.removeObstacles(boulder);
    		return false; // collision
    	}
    	return true; // no collision
    }
	
    /**
     * explode at current spot
     * @param dungeon
     * @param player
     */
    private void explodeCurrentSpot(Dungeon dungeon, Player player) {
    	Enemy enemy = dungeon.getEnemy(this.getX(), this.getY());
    	Entity isPlayer = dungeon.getEntity(this.getX(), this.getY());
    	Obstacle boulder = dungeon.getObstacles(this.getX(), this.getY(), "boulder");
    	if (isPlayer != null && player.getPlayerState().equals(new VulnerableState())) {
    		System.out.println("player @ [" + this.getX() + ", " +  this.getY() + "] destroyed!");
    		System.out.println("HIT @ [" + this.getX() + ", " +  this.getY() + "]");
    		player.getPlayerState().toGameOver(player);
    		player.getPlayerState().toGameOver(player);
    		dungeon.setPlayer(null);
    		dungeon.removeEntity(player);
    	}
    	if (enemy != null) {
    		System.out.println("enemy @ [" + this.getX() + ", " +  this.getY() + "] dies!");
    		System.out.println("HIT @ [" + this.getX() + ", " +  this.getY() + "]");
    		dungeon.removeEnemy(enemy);
    	}
    	if (boulder != null) {
    		System.out.println(boulder.getName() + " @ [" + this.getX() + ", " +  this.getY() + "] dies!");
    		System.out.println("HIT @ [" + this.getX() + ", " +  this.getY() + "]");
    		dungeon.removeObstacles(boulder);
    	}
    }
    
    /**
     * explode all direction and current spot
     * @param dungeon
     * @param player
     */
    private void explode(Dungeon dungeon, Player player) {
    	explodeCurrentSpot(dungeon, player);
    	explodeUp(dungeon, player);
		explodeDown(dungeon, player);
		explodeLeft(dungeon, player);
		explodeRight(dungeon, player);
		dungeon.removeItem(this);
		
		System.out.println("Dungeon has " + dungeon.getEnemies() + " enemies left!");
		if (dungeon.isHasEnemies() && dungeon.getEnemies() == 0) {
			player.getPlayerState().toGameOver(player);
    		player.getPlayerState().toGameOver(player);
		} 
    }

//    private void timer(long startTime, int seconds) {
//    	long elapsedSeconds = 0;
//    	while (elapsedSeconds < seconds) {
//    		long elapsedTime = System.currentTimeMillis() - startTime;
//        	elapsedSeconds = elapsedTime / 1000;
//        	System.out.println(elapsedSeconds);
//    	}
//    }
    
    /**
     * ploymorphism useItem
     */
	@Override
	public void useItem(Dungeon dungeon, Player player) {
		this.x().set(player.getX());
		this.y().set(player.getY());
		dungeon.addItem(this);
		bombStatus.litBomb(this);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				explode(dungeon, player);
			}
		};
		timer.schedule(task, 3000);
	}

	/**
	 * @return bombStatus
	 */
	@Override
	public BombState getBombState() {
		// DONE Auto-generated method stub
		return bombStatus;
	}

	@Override
	public int getId() {
		// Auto-generated method stub
		return -1;
	}

}
