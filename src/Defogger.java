/*
 * Name: Nisarg Patel
 * McMaster Engineering Competition 2015
 * Purpose: This is a Defogger class for the system design.
 */

/// This module allows the user to Turn a Car's Defogger ON/OFF
public class Defogger {

	private Mode car_defogger_mode; // private variable to track the mode of the defogger system

	/// Defogger System can be ON or OFF.
	protected enum Mode
	{
		/// Defogger System is Powered ON and its operating
		ON,

		/// Defogger System is Powered OFF and its not operating
		OFF
	}

	/// Public Constructor. Initializes the Defogger System of the Car and all the variables. Note: Default Car Defogger System Mode is OFF.
	public Defogger()
	{
		System.out.println("Defogger System Initialized");
		setCurrent_defogger_mode(Mode.OFF); // Initialize the Car's Defogger System and set the mode to OFF
	}

	/// Returns ON/OFF by examining the status of the Car's Defogger System
	public Mode getCurrent_defogger_mode()
	{
		return this.car_defogger_mode;
	}

	/// Sets the Car's Defogger System Mode to be ON or OFF, as specified by the final_mode value
	private void setCurrent_defogger_mode(Mode final_mode)
	{
		this.car_defogger_mode = final_mode;
	}

	/// This function turns ON the Defogger in the car
	public void TurnON()
	{
		setCurrent_defogger_mode(Mode.ON); // Turn ON the defogger
		System.out.println("Your Car Defogger is " + getCurrent_defogger_mode().toString());
	}

	/// This function turns OFF the Defogger in the car
	public void TurnOFF()
	{
		setCurrent_defogger_mode(Mode.OFF); // Turn OFF the defogger
		System.out.println("Your Car Defogger is " + getCurrent_defogger_mode().toString());
	}
}