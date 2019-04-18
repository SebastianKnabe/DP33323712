package package_ha01;

import org.junit.Test;

public class Hausaufgabe_01
{
	@Test
	public void testDeliveryService() {
		DeliveryService delivery = new DeliveryService();
		PizzaShop pizza = new PizzaShop();
		BurgerBude burger = new BurgerBude();
		DoenerLaden doener = new DoenerLaden();
		String address = "WilliAlle73";
		String foodNo = "M4";
		
		delivery.setSubcontractor(pizza);
		delivery.deliver(foodNo, address);
		
		delivery.setSubcontractor(burger);
		delivery.deliver(foodNo, address);
		
		delivery.setSubcontractor(doener);
		delivery.deliver(foodNo, address);

	}
}
