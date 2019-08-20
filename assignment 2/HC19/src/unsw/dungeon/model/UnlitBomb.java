package unsw.dungeon.model;

/**
 * unlit bomb State
 * @author Harvey
 */
public class UnlitBomb implements BombState{
	private final String name = "unlitBomb";
	
	@Override
	public void unlitBomb(Bomb bomb) {
		// Auto-generated method stub
	}

	/**
	 * change to lit bomb state
	 */
	@Override
	public void litBomb(Bomb bomb) {
		bomb.setBombStatus(new LitBomb());
		System.out.println("lit bomb!");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		UnlitBomb otherObj = (UnlitBomb) obj;
		return this.name == otherObj.name;
	}
}
