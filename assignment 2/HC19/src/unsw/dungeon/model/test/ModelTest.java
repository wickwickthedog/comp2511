package unsw.dungeon.model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.model.*;

/**
 * junit testing
 * @author Harvey
 */
class ModelTest {
	
	@Test
	void playerInDungeonTest() {
		System.out.println("TEST - player in dungeon");
		Dungeon d = new Dungeon(3,3);
		Player p = new Player(d,0,0);
		d.setPlayer(p);
		// In dungeon
		assertEquals(d.getPlayer().getX(), p.getX());
		assertEquals(d.getPlayer().getY(), p.getY());
		System.out.println("---DONE in dungeon test---\n");
	}
	
	@Test
	void playerMovementTest() {
		System.out.println("TEST - player movement");
		Dungeon d = new Dungeon(4,4);
		Player p = new Player(d,0,0);
		d.setPlayer(p);
		// out of bound
		p.moveUp();
		assertEquals(p.getX(), 0);
		assertEquals(p.getY(), 0);
		// out of bound
		p.moveLeft();
		assertEquals(p.getX(), 0);
		assertEquals(p.getY(), 0);
		// move down
		p.moveDown();
		assertEquals(p.getX(), 0);
		assertEquals(p.getY(), 1);
		// move right
		p.moveRight();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		System.out.println("---DONE movement test---\n");
	}
	
	@Test
	void wallTest() {
		System.out.println("TEST - wall collision");
		Dungeon d = new Dungeon(4,4);
		Player p = new Player(d,1,1);
		d.setPlayer(p);
		Wall w = new Wall(1,0);
		d.addObstacle(w);
		// move to wall
		p.moveUp();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		System.out.println("---DONE wall test---\n");
	}
	
	@Test
	void exitTest() {
		System.out.println("TEST - exit");
		Dungeon d = new Dungeon(4,4);
		Player p = new Player(d,1,1);
		d.setPlayer(p);
		Exit e = new Exit(1,0);
		d.addObstacle(e);
		// goto exit
		p.moveUp();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 0);
		// Game over state can't move
		p.moveUp();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 0);
		p.moveLeft();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 0);
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 0);
		p.moveRight();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 0);
		System.out.println("---DONE exit test---\n");
	}
	
	@Test
	void boulderTest() {
		System.out.println("TEST - boulder collision");
		Dungeon d = new Dungeon(4,4);
		Player p = new Player(d,1,1);
		d.setPlayer(p);
		Wall w = new Wall(1,4);
		d.addObstacle(w);
		Boulder b = new Boulder(1,2);
		d.addObstacle(b);
		// push boulder down
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		// check boulder position
		assertEquals(b.getX(), 1);
		assertEquals(b.getY(), 3);
		// push down again
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		// check boulder position
		assertEquals(b.getX(), 1);
		assertEquals(b.getY(), 3);
		System.out.println("---DONE boulder test---\n");
	}
	
	@Test
	void floorSwitchTest() {
		System.out.println("TEST - floor switch test");
		Dungeon d = new Dungeon(4,4);
		Player p = new Player(d,1,1);
		d.setPlayer(p);
		Boulder b = new Boulder(1,2);
		d.addObstacle(b);
		FloorSwitch s = new FloorSwitch(1,3);
		d.addObstacle(s);
		
		// push boulder down
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		// check if boulder on switch
		assertEquals(b.getX(), s.getX());
		assertEquals(b.getY(), s.getY());
		// Game over state can't move
		p.moveUp();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		p.moveLeft();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		p.moveRight();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		System.out.println("---DONE floor switch test---\n");
	}
	
	@Test
	void swordTest() {
		System.out.println("TEST - sword test");
		Dungeon d = new Dungeon(4,4);
		Player p = new Player(d,1,1);
		d.setPlayer(p);
		Sword s = new Sword(1,2);
		d.addItem(s);
		Enemy e = new Enemy(1,3);
		d.addEnemy(e);
		// pick up sword
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		// check inventory
		assertFalse(p.getInventory().isEmpty());
		p.useItem();
		// enemy is destroyed
		assertTrue(d.getEnemies() == 0);
		// game over can't move
		p.moveDown(); 
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		System.out.println("---DONE sword test---\n");
	}
	
	@Test
	void bombTest() {
		System.out.println("TEST - bomb test");
		Dungeon d = new Dungeon(4,4);
		Player p = new Player(d,1,1);
		d.setPlayer(p);
		Bomb b = new Bomb(1,2);
		d.addItem(b);
		Enemy e = new Enemy(1,3);
		d.addEnemy(e);
		Boulder w = new Boulder(0,2);
		d.addObstacle(w);
		// pick up bomb
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		// check inventory
		assertFalse(p.getInventory().isEmpty());
		p.useItem();
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// enemy is destroyed
		assertTrue(d.getEnemy(1, 3) == null);
		// boulder is destroyed
		assertTrue(d.getObstacle(0, 2) == null);
		// game over can't move
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		System.out.println("---DONE bomb test---\n");
	}
	
	@Test
	void treasureTest() {
		System.out.println("TEST - treasure test");
		Dungeon d = new Dungeon(4,5);
		Player p = new Player(d,1,1);
		d.setPlayer(p);
		Treasure t = new Treasure(1,2);
		d.addItem(t);
		t = new Treasure(1,3);
		d.addItem(t);
		t = new Treasure(1,4);
		d.addItem(t);
		// collect treasure
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		// check inventory
		assertTrue(p.getInventory().size() == 1);
		// collect treasure
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 3);
		// check inventory
		assertTrue(p.getInventory().size() == 2);
		// collect treasure
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 4);
		// game over can't move
		p.moveUp();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 4);
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 4);
		p.moveLeft();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 4);
		p.moveRight();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 4);
		System.out.println("---DONE treasure test---\n");
	}
	
	@Test
	void enemyTest() {
		System.out.println("TEST - enemy collision");
		Dungeon d = new Dungeon(4,4);
		Player p = new Player(d,1,1);
		d.setPlayer(p);
		Enemy e = new Enemy(1,0);
		d.addEnemy(e);
		// enemy move to player position
		e.moveDown(d);
		// player dies
		p.moveUp();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		p.moveLeft();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		p.moveRight();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		System.out.println("---DONE enemy test---\n");
	}
	
	@Test
	void potionTest() {
		System.out.println("TEST - invincibility potion test");
		Dungeon d = new Dungeon(4,6);
		Player p = new Player(d,1,5);
		d.setPlayer(p);
		InvincibilityPotion i = new InvincibilityPotion(1,4);
		d.addItem(i);
		Enemy e = new Enemy(1,1);
		d.addEnemy(e);
		e = new Enemy(1,0);
		d.addEnemy(e);
		p.moveUp();
		assertFalse(p.getInventory().isEmpty());
		p.moveUp();
		p.moveUp();
		// before using potion state
		assertTrue(p.getPlayerState().equals(new VulnerableState()));
		p.useItem();
		// after using potion state
		assertTrue(p.getPlayerState().equals(new InvincibleState()));
		p.moveUp(); // kill enemy in invicibility state
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// after potion wear out state
		assertTrue(p.getPlayerState().equals(new VulnerableState()));
		assertTrue(d.getEnemies() == 1);
		System.out.println("---DONE invincibility potion test---\n");
	}
	
	@Test
	void potionWearOutTest() {
		System.out.println("TEST - invincibility potion wear out test");
		Dungeon d = new Dungeon(4,6);
		Player p = new Player(d,1,5);
		d.setPlayer(p);
		InvincibilityPotion i = new InvincibilityPotion(1,4);
		d.addItem(i);
		Enemy e = new Enemy(1,1);
		d.addEnemy(e);
		e = new Enemy(1,0);
		d.addEnemy(e);
		p.moveUp();
		assertFalse(p.getInventory().isEmpty());
		p.moveUp();
		p.moveUp();
		// before using potion state
		assertTrue(p.getPlayerState().equals(new VulnerableState()));
		p.useItem();
		// after using potion state
		assertTrue(p.getPlayerState().equals(new InvincibleState()));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// after potion wear out state
		assertTrue(p.getPlayerState().equals(new VulnerableState()));
		e.moveDown(d); // enemy kill player in vulnerable state
		assertTrue(d.getEnemies() == 2);
		System.out.println("---DONE invincibility potion wear out test---\n");
	}
	
	@Test
	void correctKeyTest() {
		System.out.println("TEST - correct key test");
		Dungeon d = new Dungeon(4,6);
		Player p = new Player(d,1,5);
		d.setPlayer(p);
		Key k = new Key(1,4, 1);
		d.addItem(k);
		k = new Key(1,3, 2);
		d.addItem(k);
		Door r = new Door(1,2, 1);
		d.addObstacle(r);
		p.moveUp();
		assertFalse(p.getInventory().isEmpty());
		p.moveUp();
		assertTrue(p.getInventory().size() == 1);
		// locked door can't pass thru
		p.moveUp();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 3);
		// use key (correct)
		p.useItem();
		// door is unlocked
		p.moveUp();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		System.out.println("---DONE correct key test---\n");
	}
	
	@Test
	void wrongKeyTest() {
		System.out.println("TEST - wrong key test");
		Dungeon d = new Dungeon(4,6);
		Player p = new Player(d,1,5);
		d.setPlayer(p);
		Key k = new Key(1,4, 2);
		d.addItem(k);
		k = new Key(1,3, 3);
		d.addItem(k);
		Door r = new Door(1,2, 4);
		d.addObstacle(r);
		p.moveUp();
		assertFalse(p.getInventory().isEmpty());
		p.moveUp();
		assertTrue(p.getInventory().size() == 1);
		// locked door can't pass thru
		p.moveUp();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 3);
		// use key (wrong)
		p.useItem();
		// door is still locked
		p.moveUp();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 3);
		System.out.println("---DONE wrong key test---\n");
	}
}
