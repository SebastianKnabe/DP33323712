package package_ha05;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// HA05: 07/10
// -3 Test fehlgeschlagen. Benutze Platform.runLater um zwei views gelichzeitig zu starten
public class ProxyTest extends ApplicationTest
{
	private Button button;
	CustomerGUI customer;
	TransporterGUI transporter;

	@Override
    public void start(Stage stage) {
		customer = new CustomerGUI();
		transporter = new TransporterGUI();
		try
		{
			customer.start(stage);
			//transporter.start(stage);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    public void testCustomerGUI() {
        // when:
    	clickOn(customer.name);
    	write("Carla");
    	
    	clickOn(customer.currentLocation);
    	write("Wilhelmshoeher Allee 73, Kassel");
    	
    	clickOn(customer.target);
    	write("DEZ, Kassel");
    	
    	clickOn(customer.date);
    	write("12:00");
    	
    	assertEquals(customer.name.getText(), "Carla");
    	assertEquals(customer.currentLocation.getText(), "Wilhelmshoeher Allee 73, Kassel");
    	assertEquals(customer.target.getText(), "DEZ, Kassel");
    	assertEquals(customer.date.getText(), "12:00");
    	
        clickOn("send Request");
        
        assertTrue(customer.done);
    }
    
    @Test
    public void testTransporterGUI() {
    	clickOn(transporter.name);
    	write("Thea");
    	
    	clickOn(transporter.price);
    	write("12 Euro");
    	
    	clickOn(transporter.date);
    	write("12:07");
    	
    	assertEquals(transporter.name.getText(), "Thea");
    	assertEquals(transporter.price.getText(), "12 Euro");
    	assertEquals(transporter.date.getText(), "12:07");
    	
    	clickOn(transporter.sendOffer);
    	
    }
}
