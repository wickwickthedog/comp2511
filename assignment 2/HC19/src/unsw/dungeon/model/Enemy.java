package unsw.dungeon.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The enemy entity
 * @author Harvey
 */
public class Enemy extends Entity{
	
	private List<String> paths;
	
	public Enemy(int x, int y) {
		super(x, y);
		paths = new ArrayList<String>();
	}
	
	/**
	 * move up (similar to player movement)
	 * @param dungeon
	 */
	public void moveUp(Dungeon dungeon) {
        if (getY() > 0)
        	if (checkColumn(dungeon, getY() - 1)) {
        		y().set(getY() - 1);
        		if (dungeon.getPlayer().getPlayerState().equals(new VulnerableState())) killPlayer(dungeon);
//        		addItem(); possible steal player 
        	}
    }

	/**
	 * move down (similar to player movement)
	 * @param dungeon
	 */
    public void moveDown(Dungeon dungeon) {
        if (getY() < dungeon.getHeight() - 1)
        	if (checkColumn(dungeon, getY() + 1)) {
        		y().set(getY() + 1);
        		if (dungeon.getPlayer().getPlayerState().equals(new VulnerableState())) killPlayer(dungeon);
//        		addItem();
        	}
    }

    /**
	 * move left (similar to player movement)
	 * @param dungeon
	 */
    public void moveLeft(Dungeon dungeon) {
        if (getX() > 0)
        	if (checkRow(dungeon, getX() - 1)) {
        		x().set(getX() - 1);
        		if (dungeon.getPlayer().getPlayerState().equals(new VulnerableState())) killPlayer(dungeon);
//        		addItem();
        	}
    }

    /**
	 * move right (similar to player movement)
	 * @param dungeon
	 */
    public void moveRight(Dungeon dungeon) {
        if (getX() < dungeon.getWidth() - 1)
        	if (checkRow(dungeon, getX() + 1)) {
        		x().set(getX() + 1);
        		if (dungeon.getPlayer().getPlayerState().equals(new VulnerableState())) killPlayer(dungeon);
//        		addItem();
        	}
    }
	
    /**
     * Check Row of immediate left or right
     * @param x coordinate
     * @return true if no collision 
     * else false
     */
    private boolean checkRow(Dungeon dungeon, int x) {
    	Entity isPlayer = dungeon.getEntity(x, this.getY());
    	Enemy enemy = dungeon.getEnemy(x, this.getY());
    	Obstacle obstacle = dungeon.getObstacle(x, this.getY());
    	Item litBomb = dungeon.getLitBomb(x, this.getY(), "bomb", new LitBomb());
    	if (isPlayer != null) {
    		System.out.println("Player is close...");
    		return true;
    	}
    	if (litBomb != null) {
    		System.out.println("lit bomb @ [" + x + ", " +  this.getY() + "]");
    		return false; // collision
    	}
    	if (enemy != null) {
    		System.out.println("enemy @ [" + x + ", " +  this.getY() + "]");
    		return false; // collision
    	}
    	if (obstacle != null) {
    		System.out.println(obstacle.getName() + " @ [" + x + ", " +  this.getY() + "]");
    		obstacle.collision(dungeon, dungeon.getPlayer());
    		if (obstacle.isTriggered(dungeon)) return false; // collision
    		if (obstacle.getName().equals("exit") || obstacle.getName().equals("switch")) return true; // end game || trigger switch
    		return false; // collision
    	}
    	return true; // no collision
    }
    
    /**
     * Check column of immediate up or down
     * @param y coordinate
     * @return true if no collision 
     * else false
     */
    private boolean checkColumn(Dungeon dungeon, int y) {
    	Entity isPlayer = dungeon.getEntity(this.getX(), y);
    	Enemy enemy = dungeon.getEnemy(this.getX(), y);
    	Obstacle obstacle = dungeon.getObstacle(this.getX(), y);
    	Item litBomb = dungeon.getLitBomb(this.getX(), y, "bomb", new LitBomb());
    	if (isPlayer != null) {
    		System.out.println("Player is close...");
    		return true;
    	}
    	if (litBomb != null) {
    		System.out.println("lit bomb @ [" + this.getX() + ", " +  y + "]");
    		return false; // collision
    	}
    	if (enemy != null) {
    		System.out.println("enemy @ [" + this.getX() + ", " +  y + "]");
    		return false; // collision
    	}
    	if (obstacle != null) {
    		System.out.println(obstacle.getName() + " @ [" + this.getX() + ", " +  y + "]");
    		obstacle.collision(dungeon, dungeon.getPlayer());
    		if (obstacle.isTriggered(dungeon)) return false; // collision
    		if (obstacle.getName().equals("exit") || obstacle.getName().equals("switch")) return true; // end game || trigger switch
    		return false; // collision
    	}
    	return true; // no collision
    }
    
    /**
     * kill player if collide
     * @param dungeon
     */
    public void killPlayer(Dungeon dungeon) {
    	if (this.getX() == dungeon.getPlayer().getX() && this.getY() == dungeon.getPlayer().getY() && dungeon.getPlayer().getPlayerState().equals(new VulnerableState())) {
    		System.out.println("enemy destroy player!");
    		dungeon.getPlayer().getPlayerState().toGameOver(dungeon.getPlayer());
    		dungeon.getPlayer().getPlayerState().toGameOver(dungeon.getPlayer());
    		dungeon.setPlayer(null);
    		dungeon.removeEntity(dungeon.getPlayer());
    	}else if (this.getX() == dungeon.getPlayer().getX() && this.getY() == dungeon.getPlayer().getY() && dungeon.getPlayer().getPlayerState().equals(new InvincibleState())) {
    		System.out.println("player destroy enemy!");
    		dungeon.removeEnemy(this);
    		
    		System.out.println("Dungeon has " + dungeon.getEnemies() + " enemies left!");
    		if (dungeon.getEnemies() == 0) {
    			dungeon.getPlayer().getPlayerState().toGameOver(dungeon.getPlayer());
    			dungeon.getPlayer().getPlayerState().toGameOver(dungeon.getPlayer());
    		} 
    	}
    }
    
	/**
	 * create map based on the dungeon loaded
	 * @param dungeon
	 * @return map of type int[][]
	 */
    private int[][] createMap(Dungeon dungeon) {
    	int[][] map = new int[dungeon.getWidth()][dungeon.getHeight()];
    	// init map to 0
    	for (int i = 0; i < dungeon.getWidth(); i++ ) {
    		for (int j = 0; j < dungeon.getHeight(); j++) {
    			map[i][j] = 0;
    		}
    	}
    	// set obstacles to 1
    	for (Obstacle o : dungeon.getListofObstacles()) {
    		map[o.getX()][o.getY()] = 1;
    	}
    	// set enemies to 2
    	for (Enemy e : dungeon.getListofEnemies()) {
    		map[e.getX()][e.getY()] = 2;
    	}
    	// set player to 3
    	map[dungeon.getPlayer().getX()][dungeon.getPlayer().getY()] = 3;
    	
		return map;
    }
    
    /**
     * display map created by createMap(Dungeon) ^
     * debugging purpose
     * @param dungeon
     */
    public void displayMap(Dungeon dungeon) {
    	int[][] map = createMap(dungeon);
    	System.out.println("----- MAP head -----");
    	for (int i = 0; i < dungeon.getHeight(); i++ ) {
    		for (int j = 0; j < dungeon.getWidth(); j++) {
    			System.out.print(map[j][i] + " ");
    		}
    		System.out.println();
    	}
    	System.out.println("----- MAP tail -----");
    }
    
    /**
     * @param dungeon
     * @param y
     * @return true if y > 0 && y < dungeon.getHeight()
     * else false
     */
    private boolean height(Dungeon dungeon, int y) {
    	return y > 0 && y < dungeon.getHeight();
    }
    
    /**
     * @param dungeon
     * @param x
     * @return true if x > 0 && x < dungeon.getWidth()
     * else false
     */
    private boolean width(Dungeon dungeon, int x) {
    	return x > 0 && x < dungeon.getWidth();
    }
    
    /**
     * @param dungeon
     * @param x
     * @param y
     * @return true if width(dungeon, x) && height(dungeon, y)
     * else false
     */
    private boolean inRange(Dungeon dungeon, int x, int y) {
    	return width(dungeon, x) && height(dungeon, y);
    }
    
    /**
     * @param map
     * @param x
     * @param y
     * @return true if map[x][y] == 0
     * else false
     */
    private boolean isPath(int[][] map, int x, int y) {
    	return map[x][y] == 0;
    }
    
    /**
     * @param map
     * @param x
     * @param y
     * @return true if map[x][y] == 9
     * else false
     */
    private boolean isVisited(int[][] map, int x, int y) {
    	return map[x][y] == 9;
    }
    
    /**
     * @param dungeon
     * @param map
     * @param x
     * @param y
     * @return true if inRange(dungeon, x, y) && isPath(map, x, y) && !isVisited(map, x, y)
     * else false
     */
    private boolean isValid(Dungeon dungeon, int[][] map, int x, int y) {
    	return inRange(dungeon, x, y) && isPath(map, x, y) && !isVisited(map, x, y);
    }
    
    /**
     * FAILED to implement
     * djikstra - shortest path algo to player
     * error : java.lang.StackOverflowError
     * @param dungeon
     * @param x
     * @param y
     * @return
     */
    private boolean dijkstra(Dungeon dungeon, int x, int y) {
		int[][] map = createMap(dungeon); 
		
		if (isValid(dungeon, map, x, y) || (this.getX() != x && this.getY() != y ) ) return false;
		
		if (dungeon.getPlayer().getX() == x && dungeon.getPlayer().getY() == y) {
			map[x][y] = -1;
			return true;
		} else map[x][y] = 9;
		
		// up
		if (dijkstra(dungeon, x, y - 1)) {
			map[x][y - 1] = -1;
			paths.add("UP");
			return true;
		}
		
		// down
		if (dijkstra(dungeon, x, y + 1)) {
			map[x][y + 1] = -1;
			paths.add("DOWN");
			return true;
		}
		
		// left
		if (dijkstra(dungeon, x - 1, y)) {
			map[x - 1][y] = -1;
			paths.add("LEFT");
			return true;
		}
		
		// right
		if (dijkstra(dungeon, x + 1, y)) {
			map[x + 1][y] = -1;
			paths.add("RIGHT");
			return true;
		}	
    	return false;
    }
    
//    public void enemyMove(Dungeon dungeon) {
//    	if (dijkstra(dungeon, this.getX(), this.getY())) {
//    		if (paths.isEmpty()) System.out.println("~ Dijkstra Failed ~");
//    		else {
//    			for (String s : paths) {
//    				if (s.equals("UP")) this.moveUp(dungeon);
//    				if (s.equals("DOWN")) this.moveDown(dungeon);
//    				if (s.equals("LEFT")) this.moveLeft(dungeon);
//    				if (s.equals("RIGHT")) this.moveRight(dungeon);
//    			}
//    		}
//    	}
//    }
    
    /**
     * Randomize enemy movement 
     * as player move, enemy move
     * @param dungeon
     */
    public void enemyMove(Dungeon dungeon) {
    	// 1 - up , 2 - down , 3 - left , 4 - right
    	if (dungeon.getPlayer() == null) {
    		System.out.println("Player DEAD");
    		return;
    	} else {
    		Random rand = new Random();
        	int random = (rand.nextInt((4 + 1) + 1) + 1);
        	if (random == 1) moveUp(dungeon);
        	if (random == 2) moveDown(dungeon);
        	if (random == 3) moveLeft(dungeon);
        	if (random == 4) moveRight(dungeon);
    	}
    }
}
