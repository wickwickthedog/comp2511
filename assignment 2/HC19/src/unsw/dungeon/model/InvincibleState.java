package unsw.dungeon.model;

/**
 * Invincible State
 * @author Harvey
 */
public class InvincibleState implements PlayerState {

	String state;
	
	public InvincibleState() {
		state = "InvincibleState";
	}
	
	@Override
	public void toInvincible(Player player) {
	}

	/**
	 * change player state to vulnerable state
	 */
	@Override
	public void toVulnerable(Player player) {
		player.setPlayerState(new VulnerableState());
	}

	/**
	 * change player state to game over state
	 */
	@Override
	public void toGameOver(Player player) {
		player.setPlayerState(new GameOverState());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		InvincibleState otherObj = (InvincibleState) obj;
		return this.state == otherObj.state;
	}
}
