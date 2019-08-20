package unsw.dungeon.model;

import java.util.ArrayList;

/**
 * The floor switch obstacle
 * @author Harvey
 */
public class FloorSwitch extends Obstacle{

	private final String name = "switch";
	private boolean isTriggered;
	
	public FloorSwitch(int x, int y) {
		super(x, y);
		isTriggered = false;
	}

	/**
	 * @return name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Check if there is a boulder on a switch
	 * and set isTriggered if true
	 * @param dungeon
	 * @param player
	 */
	private void checkIsTriggered(Dungeon dungeon, Player player) {
		if (dungeon.getObstacles(this.getX(), this.getY(), "boulder") != null) isTriggered = true;
    	else isTriggered = false;
	}
	
	/**
	 * check all the switches
	 * @param dungeon
	 * @return true if all switches are triggered
	 * else false
	 */
	private boolean checkAllSwitch(Dungeon dungeon) {
		ArrayList<Obstacle> switches = dungeon.getObstacles("switch");
		int triggered = 0;
		for (Obstacle o: switches) {
			if(o.isTriggered(dungeon)) triggered ++;
		}
		System.out.println(switches.size() - triggered + " " + this.name + "left!");
		return triggered == switches.size();
	}

	/**
	 * @return isTriggered
	 */
	@Override
	public boolean isTriggered(Dungeon dungeon) {
		return isTriggered;
	}

	/**
	 * updates the floor switches
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
		return false;
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
	 * check if isTriggered
	 * if all switch isTriggered then player state to game over state
	 */
	@Override
	public void collision(Dungeon dungeon, Player player) {
		// DONE Auto-generated method stub
		checkIsTriggered(dungeon, player);
		if (isTriggered) {
			System.out.println("TRIGGERED " + name + " @ [" + this.getX() + ", " + this.getY() + "]");
		}
		if (checkAllSwitch(dungeon)) {
			player.getPlayerState().toGameOver(player); // from vulnerable state to game over
			player.getPlayerState().toGameOver(player); // TODO game over state just change to game over scene
		}
	}
}
