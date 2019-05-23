package package_ha05;

import org.json.JSONException;
import org.json.JSONObject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TransporterGUI extends Application
{	
	TextField name;
	TextField price;
	TextField date;
	
	Label labelName;
	Label labelPrice;
	Label labelDate;
	
	Button sendOffer;
	
	Label offer;
	
	VBox vbox = new VBox();

	@Override
	public void start(Stage stage) throws Exception
	{
		// TODO Auto-generated method stub
		StackPane root = new StackPane();  
		initVbox();
		root.getChildren().add(vbox);
        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Hello World Example");        
        stage.setScene(scene);        
        stage.show();    
	}
	
	private void initVbox( ) {
		name = new TextField();
		price = new TextField();
		date = new TextField();
		
		labelName = new Label("name");
		labelPrice = new Label("price");
		labelDate = new Label("date");
		
		HBox HboxName = new HBox(); 
		HboxName.getChildren().addAll(labelName, name);
		
		HBox HboxLocation = new HBox();
		HboxLocation.getChildren().addAll(labelPrice, price);
		
		HBox HboxDate = new HBox();
		HboxDate.getChildren().addAll(labelDate, date);
		
		sendOffer = new Button("send Offer");
		sendOffer.setOnAction(e -> {
			try
			{
				sendRequest();
			} catch (JSONException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		offer = new Label("Requests: \n none");
		
		
		vbox.getChildren().addAll(HboxName, HboxLocation, HboxDate, offer, sendOffer);
	}
	
	private void sendRequest() throws JSONException {
		JSONObject request = new JSONObject();
		request.put("name", name.getText());
		request.put("price", price.getText());
		request.put("time", date.getText());
		
		System.out.println(request.toString());
		
	}
	
	public static void main(String[] parameters) {       
        launch(parameters);   
   }
}