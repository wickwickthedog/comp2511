package unsw.dungeon.model;

/**
 * The door obstacle
 * @author Caleb
 * @author Harvey
 */
public class Door extends Obstacle {
	private int id;
	private final String name = "door";
	private boolean isUnlocked;
	
    public Door(int x, int y, int id) {
        super(x, y);
        this.id = id;
        isUnlocked = false;
    }
    
    /**
	 * @return name
	 */
    @Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isTriggered(Dungeon dungeon) {
		return isUnlocked;
	}

	@Override
	public void refresh(Dungeon dungeon, Player player) {
		// Auto-generated method stub
		
	}
	
	/**
	 * checks if door is unlocked
	 */
	@Override
	public boolean door(Dungeon dungeon, Player player) {
		if (isUnlocked) {
			System.out.println("UNLOCKED " + name + " @ [" + this.getX() + ", " + this.getY() + "]");
			return false;
		} else {
			System.out.println("BLOCKED! DOOR " + getId() + " IS LOCKED.");
			return true;
		}
	}

	/**
	 * @return id
	 */
	@Override
    public int getId() {
		return this.id;
	}
	
	/**
	 * unlock door
	 */
	@Override
	public void setIsUnlocked() {
		this.isUnlocked = true;
	}

	@Override
	public void collision(Dungeon dungeon, Player player) {
		// Auto-generated method stub

	}
	
}
