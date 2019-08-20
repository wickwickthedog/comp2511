package unsw.dungeon.model;

/**
 * Polymorphism
 * @author harvey
 */
public abstract class Item extends Entity{

	public Item(int x, int y) {
		super(x, y);
	}
	
	abstract public String getName();
	abstract public void useItem(Dungeon dungeon, Player player);
	abstract public BombState getBombState();
	abstract public int getId();
	
}
