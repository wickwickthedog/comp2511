package unsw.dungeon.model;

/**
 * Vulnerable State
 * @author Harvey
 */
public class VulnerableState implements PlayerState {

	String state;
	
	public VulnerableState() {
		state = "VulnerableState";
	}
	
	/**
	 * change to invincible state
	 */
	@Override
	public void toInvincible(Player player) {
		player.setPlayerState(new InvincibleState());
	}

	/**
	 * change to vulnerable state
	 */
	@Override
	public void toVulnerable(Player player) {
		player.setPlayerState(new VulnerableState());
	}

	/**
	 * change to game over state
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
		VulnerableState otherObj = (VulnerableState) obj;
		return this.state == otherObj.state;
	}

}
