package unsw.dungeon.model;

/**
 * State pattern for bomb state
 * @author Harvey
 */
public interface BombState {
	public void unlitBomb(Bomb bomb);
	public void litBomb(Bomb bomb);
}
