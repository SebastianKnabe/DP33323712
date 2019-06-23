package package_ha07.warehouse51;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.fulib.yaml.EventFiler;
import org.fulib.yaml.EventSource;
import org.fulib.yaml.Yamler;

public class ShopProxy
{
	public static final String ADD_PRODUCT_TO_SHOP = "addProductToShop";
	public static final String PRODUCT = "product";
	public static final String NUMBER_OF_ITEMS = "numberOfItems";
	private EventSource eventSource;
	private ScheduledExecutorService executor;
	
	public ShopProxy() {
		eventSource = new EventSource();
		executor = Executors.newSingleThreadScheduledExecutor();
		
		EventFiler eventFiler = new EventFiler(eventSource)
				.setHistoryFileName("database/ShopProxy.yaml");
		
		String yaml = eventFiler.loadHistory();
		if(yaml != null) {
			ArrayList<LinkedHashMap<String, String>> eventList = new Yamler().decodeList(yaml);
			eventSource.append(yaml);
		}
		
		eventFiler.storeHistory();
		
		eventFiler.startEventLogging();
	}
	
	public void addProductToShop(String bookingId, String productName, int numberOfItems) {
		LinkedHashMap<String, String> event = new LinkedHashMap<String, String>();
		event.put(EventSource.EVENT_TYPE, ADD_PRODUCT_TO_SHOP);
		event.put(EventSource.EVENT_KEY, bookingId);;
		event.put(PRODUCT, productName);
		event.put(NUMBER_OF_ITEMS, "" + numberOfItems);
		
		eventSource.append(event);
		
		String yaml = "ping: p1";
		
		WarehouseServer.sendRequest("http://localhost:5678/ping", yaml);
	}

	public String getEventSource()
	{
		String yaml = eventSource.encodeYaml();
		return yaml;
	}
}
