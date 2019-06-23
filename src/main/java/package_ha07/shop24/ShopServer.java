package package_ha07.shop24;

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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.fulib.yaml.Yamler;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class ShopServer
{
	public static ShopBuilder builder;
	private static ScheduledExecutorService executor;
	private static ExecutorService threadPool;
	private static String lastKnownWarehouseEvents = "0";

	public static void main(String[] args)
	{
		HttpServer server = null;
		try
		{
			builder = new ShopBuilder();

			executor = Executors.newSingleThreadScheduledExecutor();
			threadPool = Executors.newCachedThreadPool();

			server = HttpServer.create(new InetSocketAddress(5678), 0);
			server.setExecutor(executor);

			HttpContext context = server.createContext("/ping");
			context.setHandler(x -> handleWarehousePing(x));

			HttpContext proxyContext = server.createContext("/getWarehouseEvents");
			proxyContext.setHandler(x -> builder.theWarehouse.getWarehouseEvents(x));

			server.start();

			retrieveNewEventsFromWarehouse();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void handleWarehousePing(HttpExchange exchange)
	{
		String body = getBody(exchange);
		System.out.println("Shop Server: got " + body);
		writeAnswer(exchange, "OK");
		
		retrieveNewEventsFromWarehouse();
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

	private static void retrieveNewEventsFromWarehouse()
	{
		String warehouseEvents = sendRequest("http://localhost:6789/getShopEvents", "lastKnown: " + lastKnownWarehouseEvents );

		ArrayList<LinkedHashMap<String, String>> eventList = new Yamler().decodeList(warehouseEvents);
		
		executor.execute( () -> builder.applyEvents(eventList));

	}

	private static Object handleWarehouseProxyRequest(HttpExchange x)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private static void handleWarehouse(HttpExchange exchange) throws IOException
	{
		URI requestURI = exchange.getRequestURI();
		InputStream requestBody = exchange.getRequestBody();
		BufferedReader buf = new BufferedReader(new InputStreamReader(requestBody, StandardCharsets.UTF_8));
		StringBuilder text = new StringBuilder();

		while (true)
		{
			String line = buf.readLine();
			if (line == null)
			{
				break;
			}
			text.append(line).append("\n");
		}

		String yaml = text.toString();
		ArrayList<LinkedHashMap<String, String>> list = new Yamler().decodeList(yaml);
		builder.applyEvents(list);

		String response = "OK" + requestURI;
		exchange.sendResponseHeaders(200, response.getBytes().length);
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

	public static String sendRequest(String urlString, String yaml)
	{
		try
		{
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
			try (OutputStream os = http.getOutputStream())
			{
				os.write(out);
			}

			InputStream inputStream = http.getInputStream();
			BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
			StringBuilder text = new StringBuilder();
			
			while(true) {
				String line = buf.readLine();
				if(line == null) {
					break;
				}
				text.append(line);
			}

			buf.close();
			String response = text.toString();
			return response;
		} catch (Exception e)
		{
			executor.schedule(() -> sendRequest(urlString, yaml), 60, TimeUnit.SECONDS);
		}
		return null;
	}

}
