package unsw.dungeon.model;

/**
 * State pattern for Player State
 * @author Harvey
 */
public interface PlayerState {
	public void toInvincible(Player player);
	public void toVulnerable(Player player);
	public void toGameOver(Player player);
}
