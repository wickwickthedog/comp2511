package unsw.dungeon.model;

import java.util.ArrayList;

/**
 * The treasure item
 * @author Harvey
 */
public class Treasure extends Item{

	private final String name = "treasure";
	
	public Treasure(int x, int y) {
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
	 * checks how many treasures left in the dungeon
	 */
	@Override
	public void useItem(Dungeon dungeon, Player player) {
		System.out.println("Dungeon has " + dungeon.getNumItemByName(name) + " " + this.name + " left!");
		ArrayList<Item> treasures = new ArrayList<Item>();
		for (Item i: player.getInventory()) {
			if (i.getName().equals(name)) {
				treasures.add(i);
			}
		}
		if (dungeon.getNumItemByName(name) == 0 && treasures.size() > 0) {
			player.setPlayerState(new GameOverState());
		} 
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
