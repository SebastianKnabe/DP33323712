package package_ha07;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.fulib.tables.ObjectTable;
import org.junit.Test;

import package_ha07.shop24.Shop24;
import package_ha07.shop24.ShopProduct;
import package_ha07.shop24.ShopServer;
import package_ha07.warehouse51.Lot;
import package_ha07.warehouse51.Warehouse51;
import package_ha07.warehouse51.WarehouseBuilder;
import package_ha07.warehouse51.WarehouseProduct;
import package_ha07.warehouse51.WarehouseServer;

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
		
		WarehouseServer.main(null);
		ShopServer.main(null);
		
		WarehouseBuilder builder = WarehouseServer.builder;
		builder.addLotToStock("lot1", "Shoe 42, size 8", 50);
		builder.addLotToStock("lot2", "Shoe 42, size 8", 50);
		builder.addLotToStock("lot3", "Shoe 42, size 9", 50);
		
		Thread.sleep(100);
		
		ShopServer.builder.addCustomer("Alice", "Wonderland 1");
		ShopServer.builder.orderProduct("o1", "Shoe 42, size 8", "Alice");
		
		Thread.sleep(100);
		
		assertNotNull(builder.getFromProducts("Shoe42size8"));
		assertNotNull(builder.getFromProducts("Shoe42size9"));
		assertNotNull(ShopServer.builder.getFromProducts("Shoe42size8"));
		assertNotNull(ShopServer.builder.getFromProducts("Shoe42size9"));
		
		ShopProduct shoe42size8 = ShopServer.builder.getFromProducts("Shoes42size8");
		assertNotNull(shoe42size8);
		
		printWarehouseAndShopProducts();
	}
	
	private void printWarehouseAndShopProducts() {
		if(WarehouseServer.builder != null) {
			Warehouse51 theWarehouse = WarehouseServer.builder.theWareHouse;
			ObjectTable wareHouseTable = new ObjectTable("Warehouse", theWarehouse);
			ObjectTable productTable = wareHouseTable.expandLink("Product", Warehouse51.PROPERTY_products);
			ObjectTable lotTable = productTable.expandLink("Lot", WarehouseProduct.PROPERTY_lots);
			lotTable.expandDouble("Size", Lot.PROPERTY_lotSize);
			lotTable.expandLink("Place", Lot.PROPERTY_place);
			wareHouseTable.dropColumns("Warehouse");
			System.out.println("Warehouse");
			System.out.println(wareHouseTable);
		}
		
		if(ShopServer.builder != null) {
			Shop24 shop = ShopServer.builder.theShop;
			ObjectTable shopTable = new ObjectTable("Shop", shop);
			ObjectTable productTable = shopTable.expandLink("Product", Shop24.PROPERTY_products);
			productTable.expandLink("inStock", ShopProduct.PROPERTY_inStock);
			shopTable.dropColumns("Shop");
			System.out.println("Shop");
			System.out.println(shopTable);
		}
	}
}
