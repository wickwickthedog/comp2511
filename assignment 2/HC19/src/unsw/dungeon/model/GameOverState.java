package unsw.dungeon.model;

/**
 * Game Over State
 * @author Harvey
 */
public class GameOverState implements PlayerState{

	String state;
	
	public GameOverState() {
		state = "GameOverState";
	}
	
	@Override
	public void toInvincible(Player player) {
	}

	@Override
	public void toVulnerable(Player player) {
	}

	/**
	 * change to game over screen
	 */
	@Override
	public void toGameOver(Player player) {
		System.out.println("---Game Over!---");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		GameOverState otherObj = (GameOverState) obj;
		return this.state == otherObj.state;
	}

}
