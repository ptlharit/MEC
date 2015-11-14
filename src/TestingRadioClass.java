/*
 * Name: Nisarg Patel
 * McMaster Engineering Competition 2015
 * Purpose: This is module which performs automate tests to the Radio Class and ensures correct behaviour
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class TestingRadioClass {

	/// This method tests the Radio Constructor
	@Test
	public void testRadio() {
		Radio car_radio_test = new Radio();
		assertEquals(car_radio_test.getCurrent_radio_mode().toString(), "OFF"); // Radio should be OFF
		assertEquals(99.9,car_radio_test.getCurrent_radio_frequency(),0.0000000); // Radio frequency should be the default 99.9
	}

	/// This method tests the getCurrent_radio_mode() method in the Radio Class
	@Test
	public void testGetCurrent_radio_mode() {
		Radio car_radio_test = new Radio();
		assertEquals(car_radio_test.getCurrent_radio_mode().toString(),"OFF"); // Radio should be OFF
	}

	/// This method tests the TurnON() method in the Radio Class
	@Test
	public void testTurnON() {
		Radio car_radio_test = new Radio();
		assertEquals(car_radio_test.getCurrent_radio_mode().toString(),"OFF"); // Radio should be OFF
		car_radio_test.TurnON();
		assertEquals(car_radio_test.getCurrent_radio_mode().toString(),"ON"); // Radio should be ON
	}

	/// This method tests the TurnOFF() method in the Radio Class
	@Test
	public void testTurnOFF() {
		Radio car_radio_test = new Radio();
		assertEquals(car_radio_test.getCurrent_radio_mode().toString(),"OFF"); // Radio should be OFF
		car_radio_test.TurnON();
		assertEquals(car_radio_test.getCurrent_radio_mode().toString(),"ON"); // Radio should be ON
		car_radio_test.TurnOFF();
		assertEquals(car_radio_test.getCurrent_radio_mode().toString(),"OFF"); // Radio should be OFF
	}

	/// This method tests the getCurrent_radio_frequency() method in the Radio Class
	@Test
	public void testGetCurrent_radio_frequency() {
		Radio car_radio_test = new Radio();
		assertEquals(99.9,car_radio_test.getCurrent_radio_frequency(),0.0000000); // Radio frequency should be the default 99.9
		car_radio_test.setCurrent_radio_frequency(110);
		assertEquals(99.9,car_radio_test.getCurrent_radio_frequency(),0.0000000); // Radio frequency should be the default 99.9
	}

	/// This method tests the setCurrent_radio_frequency(double setfrequency) method in the Radio Class
	@Test
	public void testSetCurrent_radio_frequency() {
		Radio car_radio_test = new Radio();
		assertEquals(99.9,car_radio_test.getCurrent_radio_frequency(),0.0000000); // Radio frequency should be the default 99.9
		assertEquals(car_radio_test.getCurrent_radio_mode().toString(),"OFF"); // Radio should be OFF
		car_radio_test.setCurrent_radio_frequency(95.3);
		assertEquals(95.3,car_radio_test.getCurrent_radio_frequency(),0.0000000); // Radio frequency should be 95.3
		assertEquals(car_radio_test.getCurrent_radio_mode().toString(),"ON"); // Radio should be ON
		car_radio_test.setCurrent_radio_frequency(109);
		assertEquals(95.3,car_radio_test.getCurrent_radio_frequency(),0.0000000); // Radio frequency should be 95.3
		car_radio_test.setCurrent_radio_frequency(87);
		assertEquals(95.3,car_radio_test.getCurrent_radio_frequency(),0.0000000); // Radio frequency should be 95.3
	}
}