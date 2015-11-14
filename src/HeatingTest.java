import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;



public class HeatingTest {
	private static Heating heater;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		heater = new Heating();
	}

	@Test
	public void increaseHeatTest() {
		assertEquals(heater.getStatus(), Heating.Mode.OFF);
		heater.increaseHeat();
		assertEquals(heater.getStatus(), Heating.Mode.ON);
		assertEquals(heater.getHeatingLevel(), 1);
		heater.increaseHeat();
		assertEquals(heater.getHeatingLevel(), 2);
		heater.increaseHeat();
		heater.increaseHeat();
		heater.increaseHeat();
		heater.increaseHeat();
		assertEquals(heater.getHeatingLevel(), 5);
	}
	
	@Test
	public void decreaseHeatTest() {
		for(int i=0; i<5; i++)
			heater.increaseHeat();
		heater.decreaseHeat();
		assertEquals(Heating.Mode.ON, heater.getStatus());
		assertEquals(4,heater.getHeatingLevel());
		heater.decreaseHeat();
		heater.decreaseHeat();
		heater.decreaseHeat();
		heater.decreaseHeat();
		heater.decreaseHeat();
		assertEquals(heater.getStatus(), Heating.Mode.OFF);
		assertEquals(heater.getHeatingLevel(), 0);
	}
	
	@Test
	public void setHeatTest() {
		heater.setHeat(0);
		assertEquals(heater.getStatus(), Heating.Mode.OFF);
		heater.setHeat(4);
		assertEquals(heater.getStatus(), Heating.Mode.ON);
		assertEquals(heater.getHeatingLevel(),4);
		heater.setHeat(0);
		assertEquals(heater.getStatus(), Heating.Mode.OFF);
		assertEquals(heater.getHeatingLevel(), 0);
	}

}
