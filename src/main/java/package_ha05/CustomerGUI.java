package package_ha05;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
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

public class CustomerGUI extends Application
{	
	TextField name;
	TextField currentLocation;
	TextField target;
	TextField date;
	
	Label labelName;
	Label labelLocation;
	Label labelTarget;
	Label labelDate;
	
	Button sendRequest;
	
	Label offer;
	
	Button acceptOffer;
	
	VBox vbox = new VBox();
	
	MqttClient sampleClient;
	
	boolean done = false;

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
		currentLocation = new TextField();
		target = new TextField();
		date = new TextField();
		
		labelName = new Label("name");
		labelLocation = new Label("current location");
		labelTarget = new Label("target");
		labelDate = new Label("date");
		
		HBox HboxName = new HBox(); 
		HboxName.getChildren().addAll(labelName, name);
		
		HBox HboxLocation = new HBox();
		HboxLocation.getChildren().addAll(labelLocation, currentLocation);
		
		HBox HboxTarget = new HBox();
		HboxTarget.getChildren().addAll(labelTarget, target);
		
		HBox HboxDate = new HBox();
		HboxDate.getChildren().addAll(labelDate, date);
		
		sendRequest = new Button("send Request");
		sendRequest.setId(".sendRequest");
		sendRequest.setOnAction(e -> {
			try
			{
				sendRequest();
			} catch (JSONException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		offer = new Label("Offers: \n none");
		
		acceptOffer = new Button("accept Offer");
		
		vbox.getChildren().addAll(HboxName, HboxLocation, HboxTarget, HboxDate, sendRequest, offer, acceptOffer);
	}
	
	private void sendRequest() throws JSONException {
		JSONObject request = new JSONObject();
		request.put("name", name.getText());
		request.put("location", currentLocation.getText());
		request.put("target", target.getText());
		request.put("time", date.getText());
		
		System.out.println(request.toString());
		initClient(request);
		
	}
	
	private void initClient(JSONObject json) throws JSONException {
		String topic        = "toTransporter";
        String content      = json.toString();
        int qos             = 2;
        String broker       = "tcp://iot.eclipse.org:1883";
        String clientId     = "CustomerGUI";
        String subscribeFilter = "to" + json.getString("name");
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");
            sampleClient.subscribe(subscribeFilter);
            System.out.println("subscribe to "+ subscribeFilter);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
	}
	
	public static void main(String[] parameters) {       
        launch(parameters);   
   } 

}
