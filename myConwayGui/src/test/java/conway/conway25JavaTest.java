package conway;

import org.junit.After;
//import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import conway.devices.ConwayInputMock;

//By default, JUnit comes with a bundled copy of hamcrest-core

public class conway25JavaTest {
	private static Life life;
	private static LifeController cc;
	private static ConwayInputMock cim;

	@BeforeClass
	public static void setup() {
		System.out.println("setup");
    	//configureTheSystem
        life = new Life( 3,3 );
        cc   = new LifeController(life);   
        cim = new ConwayInputMock(cc,life);		
	}
	
	@After
	public void down() {
		System.out.println("down");
	}
	
	@Test
	public void testCtor() {
		System.out.println("Life constructor test");
		assertEquals(life.getRowsNum(), 3);
		assertEquals(life.getColsNum(), 3);
	}
	
	@Test
	public void testLastEpoch() {
		System.out.println("ok test1");
		cim.simulateUserControl();

		assertTrue(life.getCellState(0, 0) == 0);
		assertTrue(life.getCellState(0, 1) == 1);
		assertTrue(life.getCellState(0, 2) == 0);
		assertTrue(life.getCellState(1, 0) == 0);
		assertTrue(life.getCellState(1, 1) == 1);
		assertTrue(life.getCellState(1, 2) == 0);
		assertTrue(life.getCellState(2, 0) == 0);
		assertTrue(life.getCellState(2, 1) == 1);
		assertTrue(life.getCellState(2, 2) == 0);
	}
}

//Con gradlew test, controllare - logs/apptest.log - build/reports/tests/test/index.html

