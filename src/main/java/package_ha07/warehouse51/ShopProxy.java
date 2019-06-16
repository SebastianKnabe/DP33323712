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
import java.util.LinkedHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.fulib.yaml.EventSource;

public class ShopProxy
{
	public static final String ADD_PRODUCT_TO_SHOP = "addProductToShop";
	public static final String PRODUCT = "product";
	public static final String NUMBER_OF_ITEMS = "numberOfItems";
	private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	
	public void addProductToShop(String bookingId, String productName, int numberOfItems) {
		LinkedHashMap<String, String> event = new LinkedHashMap<String, String>();
		event.put(EventSource.EVENT_TYPE, ADD_PRODUCT_TO_SHOP);
		event.put(EventSource.EVENT_KEY, bookingId);;
		event.put(PRODUCT, productName);
		event.put(NUMBER_OF_ITEMS, "" + numberOfItems);
		
		String yaml = EventSource.encodeYaml(event);
		
		sendRequest(yaml);
	}
	
	private void sendRequest(String yaml) {
		
		try {
			URL url = new URL("http://localhost:5678/do");
			URLConnection con = url.openConnection();
			HttpURLConnection http = (HttpURLConnection) con;
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			
			byte[] out = yaml.getBytes(StandardCharsets.UTF_8);
			int length = out.length;
			
			http.setFixedLengthStreamingMode(length);
			http.setRequestProperty("Content-Type", "application/yaml; charset=UTF-8");
			http.connect();
			try(OutputStream os = http.getOutputStream()){
				os.write(out);
			}
			
			InputStream inputStream = http.getInputStream();
			BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
			String line = buf.readLine();
			
			buf.close();
		} catch (Exception e) {
			executor.schedule(() -> sendRequest(yaml), 60, TimeUnit.SECONDS);
		}
		
	}
}
