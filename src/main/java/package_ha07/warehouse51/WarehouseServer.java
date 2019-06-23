package package_ha07.warehouse51;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.fulib.yaml.Yamler;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import package_ha07.shop24.ShopBuilder;

public class WarehouseServer {
	public static WarehouseBuilder builder;
	private static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	public static HttpServer server;
	
	public static void main(String[] args) {
		server = null;
		try {
			server = HttpServer.create(new InetSocketAddress(6789), 0);
			HttpContext context = server.createContext("/getShopEvents");
			context.setHandler( x -> handleRequest(x));
			
			HttpContext proxyContext = server.createContext("/warehouseOrder");
			proxyContext.setHandler( x -> handleWarehouseProxyRequest(x));
			
			server.start();
			
			builder = new WarehouseBuilder();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static void handleWarehouseProxyRequest(HttpExchange x)
	{
		String events = getBody(x);
		
		writeAnswer(x, "OK");
		
		ArrayList<LinkedHashMap<String, String>> eventList = new Yamler().decodeList(events);
		
		executor.execute( () -> builder.applyEvents(eventList));
	}
	
	private static void retrieveNewEventsFromShop()
	{
		// TODO Auto-generated method stub
		
	}

	private static void writeAnswer(HttpExchange exchange, String response)
	{
		try {
			byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
			exchange.sendResponseHeaders(200, bytes.length);
			OutputStream os = exchange.getResponseBody();
			os.write(bytes);
			os.close();
		} catch (IOException e) {
			Logger.getGlobal().info("could not answer");
		}
		
	}

	private static void handleRequest(HttpExchange exchange) throws IOException
	{
		String body = getBody(exchange);
		System.out.println("Warehouse Server: got " + body);
		
		URI requestURI = exchange.getRequestURI();
		InputStream requestBody = exchange.getRequestBody();
		BufferedReader buf = new BufferedReader(new InputStreamReader(requestBody, StandardCharsets.UTF_8));
		StringBuilder text = new StringBuilder();
		
		while(true) {
			String line = buf.readLine();
			if(line == null) {
				break;
			}
			text.append(line).append("\n");
		}
		
		String yaml = text.toString();
		ArrayList<LinkedHashMap<String, String>> list = new Yamler().decodeList(yaml);
		builder.applyEvents(list);
		
		String response = builder.theShop.getEventSource();
		exchange.sendResponseHeaders(200, response.getBytes().length);
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
	
	public static void sendRequest(String urlString, String yaml) {
		
		try {
			URL url = new URL(urlString);
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
			executor.schedule(() -> sendRequest(urlString, yaml), 60, TimeUnit.SECONDS);
		}
	}
	
	private static String getBody(HttpExchange exchange)
	{
		URI requestURI = exchange.getRequestURI();
		InputStream requestBody = exchange.getRequestBody();
		BufferedReader buf = new BufferedReader(new InputStreamReader(requestBody, StandardCharsets.UTF_8));
		StringBuilder text = new StringBuilder();

		while (true)
		{
			String line = null;
			try
			{
				line = buf.readLine();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (line == null)
			{
				break;
			}
			text.append(line).append("\n");
		}

		String yaml = text.toString();
		return yaml;
	}
}
