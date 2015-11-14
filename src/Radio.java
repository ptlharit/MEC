/*
 * Name: Nisarg Patel
 * McMaster Engineering Competition 2015
 * Purpose: This is a Radio class for the system design.
 */

/// This module allows the user to Turn a Car's Radio or Audio System ON/OFF, and change the Radio Frequency
public class Radio 
{
	// Global variables are PRIVATE for Information Protection and to increase program integrity
	private Mode current_radio_mode;
	private double current_radio_frequency;

	/// Radio can be ON or OFF.
	protected enum Mode {
		/// Radio is Powered ON and its operational
		ON,
		
		/// Radio is Powered OFF and its non operational
		OFF
	}

	/// Public Constructor. Initializes the Radio of the Car and all the variables. Note: Default Car Radio Mode is ON and the Default Radio Frequency is 99.9 FM.
	public Radio()
	{
		System.out.println("Radio System Initialized");
		setCurrent_radio_mode(Mode.OFF); // Initialize the Car's Radio
		this.current_radio_frequency = 99.9; // Default frequency
	}

	/// Returns ON/OFF by examining the status of the Car's Radio
<<<<<<< HEAD
	public String getCurrent_radio_mode() {

		return (current_radio_mode == Mode.ON) ? "ON" : "OFF";
=======
	public Mode getCurrent_radio_mode() {
		return this.current_radio_mode;
>>>>>>> f6da9132c58629abd4f1f1b491988ebc754096c9
	}

	/// Sets the Car's Radio Mode to be ON or OFF, as specified by the final_mode value
	private void setCurrent_radio_mode(Mode final_mode) {
		this.current_radio_mode = final_mode;
	}

	/// This function turns ON the Radio in the car
	public void TurnON()
	{
<<<<<<< HEAD
		setCurrent_radio_mode(Mode.ON);
		System.out.println("Your Car Radio is " + getCurrent_radio_mode());
=======
		setCurrent_radio_mode(Mode.ON); // Turn ON the radio
		System.out.println("Your Car Radio is " + getCurrent_radio_mode().toString());
>>>>>>> f6da9132c58629abd4f1f1b491988ebc754096c9
	}

	/// This function turns OFF the Radio in the car
	public void TurnOFF()
	{
<<<<<<< HEAD
		setCurrent_radio_mode(Mode.OFF);
		System.out.println("Your Car Radio is " + getCurrent_radio_mode());
=======
		setCurrent_radio_mode(Mode.OFF); // Turn OFF the radio
		System.out.println("Your Car Radio is " + getCurrent_radio_mode().toString());
>>>>>>> f6da9132c58629abd4f1f1b491988ebc754096c9
	}

	/// Returns the Car's Current Radio Frequency
	public double getCurrent_radio_frequency()
	{
		System.out.println("Your Car Radio frequency is " + this.current_radio_frequency + " FM");
		return this.current_radio_frequency;
	}

	/// Sets the Car's Radio Frequency to setfrequency value. Note: The value of the setfrequency must be a double and has to be between 88 and 108
	public void setCurrent_radio_frequency(double setfrequency)
	{
		if (setfrequency < 88 || setfrequency > 108) // Out of range Frequency
		{
			System.out.println("The given set frequency is not valid");
			System.out.println("Please enter a FM frequency between 88 MHz to 108 MHz");
		}
		else // Frequency is in the range, so the system should change it
		{
			if (getCurrent_radio_mode() == "ON") // Radio is ON
			{
				this.current_radio_frequency = setfrequency;	
			}
			else // Radio is OFF. So, turn on the Radio for the user and set the frequency
			{
				System.out.println("Your Car Radio System is currently OFF");
				System.out.println("The System is turning your Car Radio System ON for you");
				TurnON(); // Turn ON the Car Radio System for the user
				this.current_radio_frequency = setfrequency;
			}
		}
	}
}