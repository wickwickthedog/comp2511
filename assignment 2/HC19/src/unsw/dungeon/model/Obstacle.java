package unsw.dungeon.model;

/**
 * Polymorphism
 * @author harvey
 */
public abstract class Obstacle extends Entity{

	public Obstacle(int x, int y) {
		super(x, y);
	}

	abstract public String getName();
	abstract public boolean isTriggered(Dungeon dungeon);
	abstract public void refresh(Dungeon dungeon, Player player);
	abstract public boolean door(Dungeon dungeon, Player player);	
	abstract public int getId();
	abstract public void setIsUnlocked();
	abstract public void collision(Dungeon dungeon, Player player);
}
