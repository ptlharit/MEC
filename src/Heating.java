
public class Heating {
	private Mode status;
	private int heatingLevel;
	
	///Enum to keep track of Heating status.
	public enum Mode{
		ON,OFF;
	}
	
	///Getter to return the status of the Heating
	public Mode getStatus(){
		return status;
	}
	///Getter to return heating level
	public int getHeatingLevel(){
		return this.heatingLevel;
	}
	
	///Heating class constructor - initially heating status is OFF
	public Heating(){
		status = Mode.OFF;
		this.heatingLevel = 0;
		System.out.println("status is: "+status+", heating level is: "+this.heatingLevel);
	}	

	public void increaseHeat(){
		if(this.heatingLevel == 0 && this.status == Mode.OFF){
			this.heatingLevel++;
			this.status = Mode.ON;
			System.out.println("heater turned on");
		} else if (this.heatingLevel < 5){
			this.heatingLevel++;			
		} else {
			System.out.println("Heating level is max.");
		}
	}
	
	public void decreaseHeat(){
		if(this.heatingLevel == 1 && this.status == Mode.ON){
			this.heatingLevel--;
			this.status = Mode.OFF;
			System.out.println("heater turned off");
		} else if (this.heatingLevel <= 5 && this.heatingLevel > 0){
			this.heatingLevel--;
		} else {
			System.out.println("heating is off");
		}		
	}

	public void setHeat(int level){
		if(level == 0){
			this.heatingLevel = level;
			this.status = Mode.OFF;
		} else if (level > 0 && level <= 5){
			this.heatingLevel = level;
			this.status = Mode.ON;
		} else {
			System.out.println("input a level between 0 and 5");
		}		
	}
	
}
