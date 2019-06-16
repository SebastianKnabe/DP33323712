package package_ha07.shop24;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.fulib.yaml.EventFiler;
import org.fulib.yaml.EventSource;
import org.fulib.yaml.Yamler;

import package_ha07.warehouse51.PalettePlace;
import package_ha07.warehouse51.WarehouseProduct;

public class ShopBuilder
{
	public static final String ADD_PRODUCT_TO_SHOP = "addProductToShop";
	public static final String ADD_LOT_TO_STOCK = "add lot to stock";
	public static final String NUMBER_OF_ITEMS = "numberOfItems";
	public static final String PRODUCT = "product";
	public static final String ADD_CUSTOMER = "product";
	public static final String NAME = "name";
	public static final String ADDRESS = "address";
	private EventSource eventSource;
	private Shop24 theShop;
	
	public ShopBuilder() {
		theShop = new Shop24();
		
		eventSource = new EventSource();
		EventFiler eventFiler = new EventFiler(eventSource)
				.setHistoryFileName("database/Shop.yaml");
		
		String yaml = eventFiler.loadHistory();
		if(yaml != null) {
			ArrayList<LinkedHashMap<String, String>> list = new Yamler().decodeList(yaml);
			this.applyEvents(list);
		}
		eventFiler.startEventLogging();
	}

	public void applyEvents(ArrayList<LinkedHashMap<String, String>> list)
	{
		for(LinkedHashMap<String, String> map : list) {
			if(ADD_PRODUCT_TO_SHOP.equals(map.get(EventSource.EVENT_TYPE))) {
				String txt = map.get(NUMBER_OF_ITEMS);
				int num = Integer.valueOf(txt);
				addProductToShop(map.get(EventSource.EVENT_KEY), map.get(PRODUCT), num);
			} else if(ADD_CUSTOMER.equals(map.get(EventSource.EVENT_TYPE))) {
				addCustomer(map.get(NAME), map.get(ADDRESS));
				
			}
		}
		
	}

	public void addCustomer(String string, String string2)
	{
		// TODO Auto-generated method stub
		
	}

	private void addProductToShop(String bookingId, String productName, int numberOfItems)
	{
		LinkedHashMap<String, String> event = eventSource.getEvent(bookingId);
		if(event != null) {
			return;
		}
		
		String productId = productName.replaceAll("\\W", "");
		ShopProduct product = getFromProducts(productName);
		
		double inStock = product.getInStock();
		product.setInStock(inStock + numberOfItems);
		
		event= new LinkedHashMap<String, String>();
		event.put(EventSource.EVENT_TYPE, ADD_PRODUCT_TO_SHOP);
		event.put(EventSource.EVENT_KEY, bookingId);
		event.put(PRODUCT, product.getName());
		event.put(NUMBER_OF_ITEMS, "" + numberOfItems);
		eventSource.append(event);
		
	}

	public ShopProduct getFromProducts(String productName)
	{
		String productId = productName.replace("\\W", "");
		
		for(ShopProduct product : theShop.getProducts()) {
			if(product.getId().equals(productId)) {
				product.setName(productName);
				return product;
			}
		}
		
		ShopProduct result = new ShopProduct() 
				.setId(productId)
				.setName(productName)
				.setShop24(theShop);
		
		return result;
	}

	public void orderProduct(String string, String string2, String string3)
	{
		// TODO Auto-generated method stub
		
	}

}
