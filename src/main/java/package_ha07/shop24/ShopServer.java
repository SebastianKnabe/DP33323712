package package_ha07.shop24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.fulib.yaml.Yamler;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class ShopServer
{
	public static ShopBuilder builder;
	
	public static void main(String[] args) {
		HttpServer server = null;
		try {
			server = HttpServer.create(new InetSocketAddress(5678), 0);
			HttpContext context = server.createContext("/do");
			context.setHandler( x -> handleRequest(x));
			
			HttpContext proxyContext = server.createContext("/warehouseProxy");
			proxyContext.setHandler( x -> handleWarehouseProxyRequest(x));
			
			server.start();
			
			builder = new ShopBuilder();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static Object handleWarehouseProxyRequest(HttpExchange x)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private static void handleRequest(HttpExchange exchange) throws IOException
	{
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
		
		String response = "OK" + requestURI;
		exchange.sendResponseHeaders(200, response.getBytes().length);
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

}
