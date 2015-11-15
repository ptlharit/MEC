/// This module monitors the Fuel Level in the Car. Note: it could be Fuel Level for Gas Cars and Charge Level for Electric Cars
class FuelMonitor {
	private int currentFuel;
	private Status status;
	private boolean needFuel;
	
	public enum Status{FULL,NEARLY_FULL, HALF, NEARLY_EMPTY, CRITICAL, EMPTY}
	
	//Constructor 
	public FuelMonitor(int currentFuel){
		this.currentFuel = currentFuel;
		updateStatus();
		checkFuel();
	}
	
	///Method used to update changes in the fuel amount
	public void updateFuel(int fuelAmt){
		this.currentFuel = fuelAmt;
		updateStatus();
		checkFuel();
	}
	
	///Method that gets the value of currentFuel
	public int getCurrentFuel(){
		return this.currentFuel;
	}
	
	///Method that gets the value of Status
	public Status getStatus(){
		return this.status;
	}
	
	///Method that updates the status based on the current fuel in the system
	private void updateStatus(){
		if (this.currentFuel >= 95)
			this.status = Status.FULL;
		else if (this.currentFuel > 50 && this.currentFuel <= 95)
			this.status = Status.NEARLY_FULL;
		else if (this.currentFuel > 15 && this.currentFuel <= 50)
			this.status = Status.HALF;
		else if (this.currentFuel <= 15 && this.currentFuel > 5)
			this.status = Status.NEARLY_EMPTY;
		else if (this.currentFuel > 2 && this.currentFuel <= 5)
			this.status = Status.CRITICAL;
		else if (this.currentFuel <= 2) 
			this.status = Status.EMPTY;
	}
	
	///Method that checks if the fuel needs to be filled
	private void checkFuel(){
		 this.needFuel = ((this.status == Status.NEARLY_EMPTY || this.status == Status.CRITICAL || this.status == Status.EMPTY) ? true: false);
		 if (needFuel) alert(status); //alert method place holder
	}
	
	///This method sets the alert status for the user to know
	public String alert(Status status){
		String msg = "";
		switch (status){
			case NEARLY_EMPTY: msg = "Fuel is low, consider refilling"; break;
			case CRITICAL: msg = "Fuel extremely low, refill immediately"; break;
			case EMPTY: msg = "Empty"; break;
			default:break;
		}
		return msg;
	}
}
