/// This module monitors the Electric Charge of an Electric Car
class ElectricFuelMonitor extends FuelMonitor {
	
	/// Charge Level of the Electric Car
	private int currentCharge;
	
	/// Constructor
	public ElectricFuelMonitor(int currentCharge){
		super(currentCharge);
	}
}
