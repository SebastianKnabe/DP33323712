package package_ha07.warehouse51;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.fulib.yaml.EventFiler;
import org.fulib.yaml.EventSource;
import org.fulib.yaml.Yamler;

public class WarehouseBuilder
{

	private static final String ADD_LOT_TO_STOCK = "addLotToStock";
	private static final String LOT_ID = "lotId";
	private static final String PRODUCT = "product";
	private static final String SIZE = "size";
	private static final String ORDER_PRODUCT = "orderProduct";
	private static final String ADDRESS = "address";
	public Warehouse51 theWareHouse;
	private ShopProxy theShop;
	private EventSource eventSource;
	
	public WarehouseBuilder() {
		theWareHouse = new Warehouse51();
		theShop = new ShopProxy();
		eventSource = new EventSource();
		EventFiler eventFiler = new EventFiler(eventSource)
				.setHistoryFileName("database/Warehouse.yaml");
		
		for(int i = 23; i < 28; i++) {
			PalettePlace palette = new PalettePlace()
					.setColumn(i)
					.setRow(42)
					.setId(String.format("place%dx%d", 42, i))
					.setWarehouse51(theWareHouse);
		}
		
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
			if(ADD_LOT_TO_STOCK.equals(map.get(EventSource.EVENT_TYPE))) {
				int size = Integer.valueOf(map.get(SIZE));
				addLotToStock(map.get(LOT_ID), map.get(PRODUCT), size);
			}
			else if(ORDER_PRODUCT.equals(map.get(EventSource.EVENT_TYPE))) {
				orderProduct(map.get(EventSource.EVENT_KEY), map.get(PRODUCT), map.get(ADDRESS));
			}
		}
		
	}

	private void orderProduct(String orderId, String productName, String address)
	{
		WarehouseOrder order = getFromOrders(orderId);
		
		if(order.getWarehouseProduct() == null) {
			//new order
			WarehouseProduct product = getFromProducts(productName);
			order.setWarehouseProduct(product)
					.setAddress(address);
			
			LinkedHashMap<String, String> event = new LinkedHashMap<String, String>();
			event.put(EventSource.EVENT_TYPE, ORDER_PRODUCT);
			event.put(EventSource.EVENT_KEY, orderId);
			event.put(PRODUCT, product.getName());
			event.put(ADDRESS, address);
			eventSource.append(event);
			
			Lot lot = product.getLots().get(0);
			double lotSize = lot.getLotSize();
		}
	}

	private WarehouseOrder getFromOrders(String orderId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Lot addLotToStock(String lotId, String productName, int size)
	{
		Lot result = getOrCreate(lotId);
		double oldSize = result.getLotSize();
		
		WarehouseProduct product = getFromProducts(productName);
		
		result.setWarehouseProduct(product).setLotSize(size);
		
		//add Place
		if(result.getPlace() == null) {
			for(PalettePlace place : theWareHouse.getPlaces()) {
				if(place.getLot() == null) {
					place.setLot(result);
					break;
				}
			}
		}
		
		LinkedHashMap<String, String> event = new LinkedHashMap<String, String>();
		event.put(EventSource.EVENT_TYPE, ADD_LOT_TO_STOCK);
		event.put(EventSource.EVENT_KEY, lotId);
		event.put(LOT_ID, lotId);
		event.put(PRODUCT, productName);
		event.put(SIZE, "" + size);
		eventSource.append(event);
		
		if(oldSize == 0.0) {
			theShop.addProductToShop(lotId, productName, size);
		}
		
		return result;
	}

	public WarehouseProduct getFromProducts(String productName)
	{
		String productId = productName.replace("\\W", "");
		
		for(WarehouseProduct product : theWareHouse.getProducts()) {
			if(product.getId().equals(productId)) {
				product.setName(productName);
				return product;
			}
		}
		
		WarehouseProduct result = new WarehouseProduct() 
				.setId(productId)
				.setName(productName)
				.setWarehouse51(theWareHouse);
		
		return result;
	}

	private Lot getOrCreate(String lotId)
	{
		for(WarehouseProduct product : theWareHouse.getProducts()) {
			for(Lot lot : product.getLots()) {
				if(lot.getId().equals(lotId)) {
					return lot;
				}
			}
		}
		
		Lot result = new Lot().setId(lotId);
		
		return result;
	}

}
