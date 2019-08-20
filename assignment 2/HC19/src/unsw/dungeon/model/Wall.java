package unsw.dungeon.model;

/**
 * The wall obstacle
 * @author Harvey
 */
public class Wall extends Obstacle {
	
	private final String name = "wall";
	
    public Wall(int x, int y) {
        super(x, y);
    }
    
    /**
     * @return name
     */
    @Override
	public String getName() {
		return name;
	}

	@Override
	public boolean door(Dungeon dungeon, Player player) {
		return false;
	}

	@Override
	public boolean isTriggered(Dungeon dungeon) {
		return true;
	}
	
	@Override
	public void refresh(Dungeon dungeon, Player player) {
		// Auto-generated method stub
		
	}

	@Override
	public int getId() {
		return -1;
	}

	@Override
	public void setIsUnlocked() {
		// Auto-generated method stub
		
	}

	@Override
	public void collision(Dungeon dungeon, Player player) {
		// Auto-generated method stub
		System.out.println("Wall Blocked!");
	}
}
