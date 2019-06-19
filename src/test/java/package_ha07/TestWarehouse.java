package package_ha07;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import package_ha07.shop24.ShopServer;
import package_ha07.warehouse51.Warehouse51;
import package_ha07.warehouse51.WarehouseBuilder;
import package_ha07.warehouse51.WarehouseServer;

// HA07: 10/10
public class TestWarehouse
{
	@Test
	public void testWarehouse() throws InterruptedException {
		try {
			Files.delete(Paths.get("database/Warehouse.yaml"));
			Files.delete(Paths.get("database/Shop.yaml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//WarehouseServer.main(null);
		ShopServer.main(null);
		
		WarehouseBuilder builder = new WarehouseBuilder();//WarehouseServer.builder;
		builder.addLotToStock("lot1", "Shoe 42, size 8", 50);
		builder.addLotToStock("lot2", "Shoe 42, size 8", 50);
		builder.addLotToStock("lot3", "Shoe 42, size 9", 50);
		
		Thread.sleep(100);
		
		ShopServer.builder.addCustomer("Alice", "Wonderland 1");
		ShopServer.builder.orderProduct("o1", "Shoe 42, size 8", "Alice");
		
		Thread.sleep(100);
		
		assertNotNull(builder.getFromProducts("Shoes42size8"));
		assertNotNull(builder.getFromProducts("Shoes42size9"));
		assertNotNull(ShopServer.builder.getFromProducts("Shoes42size8"));
		assertNotNull(ShopServer.builder.getFromProducts("Shoes42size9"));
	}
}
