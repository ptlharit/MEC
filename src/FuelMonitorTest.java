import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class FuelMonitorTest {
	private static FuelMonitor fuelMonitor;
	private static GasFuelMonitor gFM;
	private static ElectricFuelMonitor eFM;
	private static String msg1,msg2,msg3;
	
	@BeforeClass
	public static void setup(){
		fuelMonitor = new FuelMonitor(96);
		eFM = new ElectricFuelMonitor(79);
		gFM = new GasFuelMonitor(67);
		msg1 = "Fuel is low, consider refilling";
		msg2 = "Fuel extremely low, refill immediately"; 
		msg3 = "Empty";
	}
	
	@Test
	public void testFuelStatus() {
		fuelMonitor.updateFuel(50);
		assertEquals(FuelMonitor.Status.HALF, fuelMonitor.getStatus());
		fuelMonitor.updateFuel(15);
		assertEquals(FuelMonitor.Status.NEARLY_EMPTY, fuelMonitor.getStatus());
		fuelMonitor.updateFuel(0);
		assertEquals(FuelMonitor.Status.EMPTY, fuelMonitor.getStatus());
		fuelMonitor.updateFuel(100);
		assertEquals(FuelMonitor.Status.FULL, fuelMonitor.getStatus());
		fuelMonitor.updateFuel(78);
		assertEquals(FuelMonitor.Status.NEARLY_FULL, fuelMonitor.getStatus());
		fuelMonitor.updateFuel(10);
		assertEquals(FuelMonitor.Status.NEARLY_EMPTY, fuelMonitor.getStatus());
	}
	
	@Test
	public void testFuelAmount(){
		fuelMonitor.updateFuel(50);
		assertEquals(50, fuelMonitor.getCurrentFuel());
		fuelMonitor.updateFuel(15);
		assertEquals(15, fuelMonitor.getCurrentFuel());
		fuelMonitor.updateFuel(0);
		assertEquals(0, fuelMonitor.getCurrentFuel());
		fuelMonitor.updateFuel(100);
		assertEquals(100, fuelMonitor.getCurrentFuel());
		fuelMonitor.updateFuel(78);
		assertEquals(78, fuelMonitor.getCurrentFuel());
		fuelMonitor.updateFuel(10);
		assertEquals(10, fuelMonitor.getCurrentFuel());
	}
	
	@Test
	public void testAlertMsgNearlyEmpty(){
		fuelMonitor.updateFuel(15);
		assertEquals(msg1, fuelMonitor.alert(fuelMonitor.getStatus()));
	}
	
	@Test
	public void testAlertMsgCritical(){
		fuelMonitor.updateFuel(5);
		assertEquals(msg2, fuelMonitor.alert(fuelMonitor.getStatus()));
	}
	
	@Test
	public void testAlertMsgEmpty(){
		fuelMonitor.updateFuel(0);
		assertEquals(msg3, fuelMonitor.alert(fuelMonitor.getStatus()));
	}
	
	@Test
	public void testNoAlertMsg(){
		fuelMonitor.updateFuel(75);
		assertFalse(fuelMonitor.alert(fuelMonitor.getStatus()) == msg1);
		assertFalse(fuelMonitor.alert(fuelMonitor.getStatus()) == msg2);
		assertFalse(fuelMonitor.alert(fuelMonitor.getStatus()) == msg3);
	}
}
