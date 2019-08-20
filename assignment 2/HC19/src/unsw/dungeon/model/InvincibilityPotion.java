package unsw.dungeon.model;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The invincibility potion item
 * @author Harvey
 */
public class InvincibilityPotion extends Item{

	private final String name = "invincibility";
	
	public InvincibilityPotion(int x, int y) {
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
	 * change player state to from
	 * vulnerable -> inviincible
	 * effect last for 3 secs
	 */
	@Override
	public void useItem(Dungeon dungeon, Player player) {
		System.out.println("player is now invincible!");
		player.getPlayerState().toInvincible(player);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				player.getPlayerState().toVulnerable(player);
				System.out.println("player is now vulnerable!");
			}
		};
		timer.schedule(task, 3000);
		
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
