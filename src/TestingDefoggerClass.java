/*
 * Name: Nisarg Patel
 * McMaster Engineering Competition 2015
 * Purpose: This module performs automated tests to the Defogger Class and ensures correct behaviour
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class TestingDefoggerClass {

	/// This method tests the Defogger Class Constructor
	@Test
	public void testDefogger() {
		Defogger car_defogger_test = new Defogger();
		assertEquals(car_defogger_test.getCurrent_defogger_mode().toString(), "OFF"); // Defogger System should be OFF upon initialization
	}
	
	/// This method tests the getCurrent_defogger_mode() method in the Defogger Class
	@Test
	public void testGetCurrent_defogger_mode() {
		Defogger car_defogger_test = new Defogger();
		assertEquals(car_defogger_test.getCurrent_defogger_mode().toString(), "OFF"); // Defogger System should be OFF upon initialization
		car_defogger_test.TurnON();
		assertEquals(car_defogger_test.getCurrent_defogger_mode().toString(), "ON"); // Defogger System should be ON
	}

	/// This method tests the TurnON() method in the Defogger Class
	@Test
	public void testTurnON() {
		Defogger car_defogger_test = new Defogger();
		car_defogger_test.TurnON();
		assertEquals(car_defogger_test.getCurrent_defogger_mode().toString(), "ON"); // Defogger System should be ON as you have changed the state from OFF to ON
	}

	/// This method tests the TurnOFF() method in the Defogger Class
	@Test
	public void testTurnOFF() {
		Defogger car_defogger_test = new Defogger();
		car_defogger_test.TurnON();
		assertEquals(car_defogger_test.getCurrent_defogger_mode().toString(), "ON"); // Defogger System should be ON as you have changed the state from OFF to ON
		car_defogger_test.TurnOFF();
		assertEquals(car_defogger_test.getCurrent_defogger_mode().toString(), "OFF"); // Defogger System should be OFF as you have changed the state from ON to OFF
	}
}