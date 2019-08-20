package unsw.dungeon.model;

/**
 * lit bomb State
 * @author Harvey
 */
public class LitBomb implements BombState{

	private final String name = "litBomb";
	
	@Override
	public void unlitBomb(Bomb bomb) {
	}
	
	@Override
	public void litBomb(Bomb bomb) {
		System.out.println("Already Lit!");
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		LitBomb otherObj = (LitBomb) obj;
		return this.name == otherObj.name;
	}


}
