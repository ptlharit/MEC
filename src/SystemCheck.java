/*
 * Name: Nisarg Patel
 * McMaster Engineering Competition 2015
 * Purpose: This is a SystemCheck class for the system design.
 */

/// This module allows the user to perform a System Check on the Car
class SystemCheck {

	private String car_make; // Variable that holds the car make
	private static double recommended_tire_pressure = 30; // 30 PSI is the recommended Tire Pressure
	private double[] tire_pressure; // {FrontLeft, FrontRight, BackLeft, BackRight}
	private double kilometres_after_last_oil_change;
	
	// Result Variables
	protected Boolean [] Tire_Pressure_Check_Results;
	protected Boolean Oil_Change_Result;
	
	/// Public Constructor. Takes the car make and the tire pressure readings from all the tires as double
	public SystemCheck(String carmake, double[] tirepressure, double distance_after_oil_change)
	{
		this.setCar_make(carmake);
		this.tire_pressure = tirepressure;
		this.kilometres_after_last_oil_change = distance_after_oil_change;
		
		this.Tire_Pressure_Check_Results = Tire_Pressure_Check(); // Perform Tire Pressure Check
		this.Oil_Change_Result = Oil_Change_Check();
	}

	/// Returns the car make
	private String getCar_make() {
		return car_make;
	}

	/// Sets the car make
	private void setCar_make(String car) {
		this.car_make = car;
	}
	
	/// Returns a Boolean Array that contains the Tire_Pressure_Check Results. Returns false if the Tire Pressure in the tire is less then the 75% of the recommended value.
	public Boolean[] Tire_Pressure_Check()
	{
		Boolean[] Results = new Boolean[4];
		for (int i = 0; i < tire_pressure.length; i++)
		{
			if(tire_pressure[i] <= (recommended_tire_pressure*0.75))
			{
				Results[i] = false; // Tire Pressure is low
			}
			else
			{
				Results[i] = true; // Tire Pressure is Ok
			}
		}
		System.out.println("Front Left Tire Pressure Check: " + Results[0]);
		System.out.println("Front Right Tire Pressure Check: " + Results[1]);
		System.out.println("Back Left Tire Pressure Check: " + Results[2]);
		System.out.println("Back Right Tire Pressure Check: " + Results[3]);
		return Results; // Return the results from the Tire_Pressure_Check of all the tires
	}
	
	/// Returns True or False if the Car Requires Oil Change or not. On, average car should get their Oil Change every 5,000 km.
	public Boolean Oil_Change_Check()
	{
		if (this.kilometres_after_last_oil_change >= 5000.0) // Car Requires oil Change
		{
			System.out.println("Your Car Requires an Oil Change");
			return false;
		}
		else // Car Does not Require Oil Change
		{
			System.out.println("Your Car Does Not Require an Oil Change, but it will in " + (5000-this.kilometres_after_last_oil_change) + " kms.");
			return true;
		}
	}
	
	/// Returns the number of Kilometres left for the next oil change
	public double getKMToNextOilChange() { return 5000-this.kilometres_after_last_oil_change; }
}