package package_ha01;

import org.junit.Test;

// HA1: 06/10
// - 1 Das ist kein Test es fehlen Assertion. Naechstes mal ziehe ich mehr Punkte ab
// -2 Kein sdmlib nutzen
// -1 Carterer muss abstract oder interface

public class Hausaufgabe_01
{
	@Test
	public void testDeliveryService() {
		DeliveryService delivery = new DeliveryService();
		PizzaShop pizza = new PizzaShop();
		BurgerBude burger = new BurgerBude();
		DoenerLaden doener = new DoenerLaden();
		AsiaLaden asia = new AsiaLaden();
		String address = "WilliAlle73";
		String foodNo = "M4";
		
		delivery.setSubcontractor(pizza);
		delivery.deliver(address, foodNo);
		
		delivery.setSubcontractor(burger);
		delivery.deliver(address, foodNo);
		
		delivery.setSubcontractor(doener);
		delivery.deliver(address, foodNo);

		delivery.setSubcontractor(asia);
		delivery.deliver(address, foodNo);
		//neue Codezeilen: 6
		//SDMLIB: 2
		//Test: 3
		//AsiaLaden: 1
	}
}
