/**
 *
 */
package unsw.automata;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Conway's Game of Life on a 10x10 grid.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLife {
	
//	private boolean[][] grid = new boolean[10][10];
	private BooleanProperty[][] grid;// = new BooleanProperty[10][10];
	private final Boolean alive = Boolean.valueOf(true);
	private final Boolean dead = Boolean.valueOf(false);
	
    public GameOfLife() {
        // DONE At the start all cells are dead
//    	int count = 0;
    	grid = new BooleanProperty[10][10];
    	for (int i = 0; i < 10; i++) {
    		for (int j = 0; j < 10; j++) {
    			grid[i][j] = new SimpleBooleanProperty();
    			ensureDead(i,j);
//    			count ++;
//    			System.out.println(grid[i][j]);
    		}
    	}
//    	System.out.println(count);
    }

    public void ensureAlive(int x, int y) {
        // DONE Set the cell at (x,y) as alive
//    	grid[x][y] = true;
//    	grid[x][y].set(new Boolean(true));
    	grid[x][y].setValue(alive);
    }

    public void ensureDead(int x, int y) {
        // DONE Set the cell at (x,y) as dead
//    	grid[x][y] = false;
//    	grid[x][y].set(new Boolean(false));
    	grid[x][y].setValue(dead);
    }

    public boolean isAlive(int x, int y) {
        // DONE Return true if the cell is alive
//    	if (grid[x][y]) return true;
    	if (grid[x][y].get()) return true;
    	else return false;
    }

	// DONE corner case
    private int checkEdge(int x, int y) {
    	int aliveNeighbours = 0;
    	if (x == 0 && y == 0) {
    		if (isAlive(0,1)) aliveNeighbours ++;
    		if (isAlive(1,1)) aliveNeighbours ++;
    		if (isAlive(1,0)) aliveNeighbours ++;
    		if (isAlive(9,0)) aliveNeighbours ++;
    		if (isAlive(9,1)) aliveNeighbours ++;
    		if (isAlive(0,9)) aliveNeighbours ++;
    		if (isAlive(1,9)) aliveNeighbours ++;
    		if (isAlive(9,9)) aliveNeighbours ++;
    	} else if (x == 9 && y == 9) {
    		if (isAlive(8,9)) aliveNeighbours ++;
    		if (isAlive(8,8)) aliveNeighbours ++;
    		if (isAlive(9,8)) aliveNeighbours ++;
    		if (isAlive(9,0)) aliveNeighbours ++;
    		if (isAlive(8,0)) aliveNeighbours ++;
    		if (isAlive(0,8)) aliveNeighbours ++;
    		if (isAlive(0,9)) aliveNeighbours ++;
    		if (isAlive(0,0)) aliveNeighbours ++;
    	} else if (x == 9 && y == 0) {
    		if (isAlive(8,0)) aliveNeighbours ++;
    		if (isAlive(8,1)) aliveNeighbours ++;
    		if (isAlive(9,1)) aliveNeighbours ++;
    		if (isAlive(9,9)) aliveNeighbours ++;
    		if (isAlive(8,9)) aliveNeighbours ++;
    		if (isAlive(0,0)) aliveNeighbours ++;
    		if (isAlive(0,1)) aliveNeighbours ++;
    		if (isAlive(0,9)) aliveNeighbours ++;
    	} else if (x == 0 && y == 9) {
    		if (isAlive(0,8)) aliveNeighbours ++;
    		if (isAlive(1,8)) aliveNeighbours ++;
    		if (isAlive(1,9)) aliveNeighbours ++;
    		if (isAlive(0,0)) aliveNeighbours ++;
    		if (isAlive(1,0)) aliveNeighbours ++;
    		if (isAlive(9,9)) aliveNeighbours ++;
    		if (isAlive(9,8)) aliveNeighbours ++;
    		if (isAlive(9,0)) aliveNeighbours ++;
    	}
		return aliveNeighbours;
    }
    
    // DONE side case 
    private int checkSide(int x, int y) {
    	int aliveNeighbours = 0;
    	if (x == 0 && (y > 0 && y < 9)) {
    		if (isAlive(x, y - 1)) aliveNeighbours ++; // up
    		if (isAlive(x + 1, y)) aliveNeighbours ++; // right
    		if (isAlive(9, y)) aliveNeighbours ++; // left
    		if (isAlive(x, y + 1)) aliveNeighbours ++; // down
    		if (isAlive(x + 1, y - 1)) aliveNeighbours ++; // up-right
    		if (isAlive(9, y - 1)) aliveNeighbours ++; // up-left
    		if (isAlive(x + 1, y + 1)) aliveNeighbours ++; // down-right
    		if (isAlive(9, y + 1)) aliveNeighbours ++; // down-left
    	} else if (x == 9 && (y > 0 && y < 9)) {
    		if (isAlive(x, y - 1)) aliveNeighbours ++; // up
    		if (isAlive(0, y)) aliveNeighbours ++; // right
    		if (isAlive(x - 1, y)) aliveNeighbours ++; // left
    		if (isAlive(x, y + 1)) aliveNeighbours ++; // down
    		if (isAlive(0, y - 1)) aliveNeighbours ++; // up-right
    		if (isAlive(x - 1, y - 1)) aliveNeighbours ++; // up-left
    		if (isAlive(0, y + 1)) aliveNeighbours ++; // down-right
    		if (isAlive(x - 1, y + 1)) aliveNeighbours ++; // down-left
    	} else if (y == 0 && (x > 0 && x < 9)) {
    		if (isAlive(x, 9)) aliveNeighbours ++; // up
    		if (isAlive(x + 1, y)) aliveNeighbours ++; // right
    		if (isAlive(x - 1, y)) aliveNeighbours ++; // left
    		if (isAlive(x, y + 1)) aliveNeighbours ++; // down
    		if (isAlive(x + 1, 9)) aliveNeighbours ++; // up-right
    		if (isAlive(x - 1, 9)) aliveNeighbours ++; // up-left
    		if (isAlive(x + 1, y + 1)) aliveNeighbours ++; // down-right
    		if (isAlive(x - 1, y + 1)) aliveNeighbours ++; // down-left
    	} else if (y == 9 && (x > 0 && x < 9)) {
    		if (isAlive(x, y - 1)) aliveNeighbours ++; // up
    		if (isAlive(x + 1, y)) aliveNeighbours ++; // right
    		if (isAlive(x - 1, y)) aliveNeighbours ++; // left
    		if (isAlive(x, 0)) aliveNeighbours ++; // down
    		if (isAlive(x + 1, y - 1)) aliveNeighbours ++; // up-right
    		if (isAlive(x - 1, y - 1)) aliveNeighbours ++; // up-left
    		if (isAlive(x + 1, 0)) aliveNeighbours ++; // down-right
    		if (isAlive(x - 1, 0)) aliveNeighbours ++; // down-left
    	} 
    	return aliveNeighbours;
    }
    
    // DONE normal case 
    private int checkNormal(int x, int y) {
    	int aliveNeighbours = 0;
    	if ((x > 0 && x < 9) && (y > 0 && y < 9)) {
    		if (isAlive(x, y - 1)) aliveNeighbours ++; // up
    		if (isAlive(x + 1, y)) aliveNeighbours ++; // right
    		if (isAlive(x - 1, y)) aliveNeighbours ++; // left
    		if (isAlive(x, y + 1)) aliveNeighbours ++; // down
    		if (isAlive(x + 1, y - 1)) aliveNeighbours ++; // up-right
    		if (isAlive(x - 1, y - 1)) aliveNeighbours ++; // up-left
    		if (isAlive(x + 1, y + 1)) aliveNeighbours ++; // down-right
    		if (isAlive(x - 1, y + 1)) aliveNeighbours ++; // down-left
    	}
    	return aliveNeighbours;
    }
    
    public BooleanProperty cellProperty(int x, int y) {
		return grid[x][y];
    }
    
    public void tick() {
        // DONE Transition the game to the next generation.
    	boolean[][] future = new boolean[10][10];
    	for (int i = 0; i < 10; i++) {
    		for (int j = 0; j < 10; j++) {
    			if (isAlive(i,j)) future[i][j] = true;
    			else future[i][j] = false;
    		}
    	}
    	
    	for (int i = 0; i< 10; i++) {
    		for (int j = 0; j < 10; j++) {
    			int aliveNeighbour = 0;
    			// DONE normal case 
    			if (aliveNeighbour == 0) aliveNeighbour = checkNormal(i,j);
    			// DONE side case 
    			if (aliveNeighbour == 0) aliveNeighbour = checkSide(i,j);
    			// DONE corner case
    			if (aliveNeighbour == 0) aliveNeighbour = checkEdge(i,j);
    			
//    			if (i == 8 && j == 6) System.out.println("Alive := " + aliveNeighbour);
    			
    			if (isAlive(i,j) && aliveNeighbour < 2) {
    				future[i][j] = false;
//    				ensureDead(i,j);
    			}
    			else if (isAlive(i,j) && aliveNeighbour > 3) {
    				future[i][j] = false;
//    				ensureDead(i,j);
    			}
    			else if (!isAlive(i,j) && aliveNeighbour == 3) {
    				future[i][j] = true;
//    				ensureAlive(i,j);
    			}
//    			else future[i][j] = grid[i][j];
    			else future[i][j] = grid[i][j].get();
//    			else if (isAlive(i,j)) ensureAlive(i,j);
//    			else ensureDead(i,j);	
    		}
    	}
    	
    	// update grid
    	for (int i = 0; i < 10; i++) {
    		for (int j = 0; j < 10; j++) {
    			if (future[i][j] == true) ensureAlive(i,j);
    			else ensureDead(i,j);	
    		}
    	}
    }

}
