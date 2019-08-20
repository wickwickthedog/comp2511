package unsw.dungeon.model;

/**
 * The exit obstacle
 * @author Harvey
 */
public class Exit extends Obstacle{

	private final String name = "exit";
	
	public Exit(int x, int y) {
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
	 * condition to exit
	 * @return false 
	 */
	@Override
	public boolean isTriggered(Dungeon dungeon) {
		return false;
	}

	@Override
	public void refresh(Dungeon dungeon, Player player) {
	
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
	
	}
	
	/**
	 * update player state to game over state
	 */
	@Override
	public void collision(Dungeon dungeon, Player player) {
		player.getPlayerState().toGameOver(player); // from vulnerable state to game over
		player.getPlayerState().toGameOver(player); // TODO game over state just change to game over scene 
	}
}
