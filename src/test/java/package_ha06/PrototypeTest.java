package package_ha06;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;
import package_ha06.Editor;

public class PrototypeTest extends ApplicationTest
{
	Editor editor;
	private final String l1 = "line l1 100 100 100 200";
	private final String l7 = "line l7 100 200 200 100";
	private final String l3 = "line l3 200 100 200 200";
	private final String l8 = "line l8 200 200 100 200";
	private final String l5 = "line l5 100 100 150 50";
	private final String l6 = "line l6 150 50 200 100";
	private final String l2 = "line l2 200 200 100 100";
	private final String l4 = "line l4 100 100 200 100";
	
	private final String gRoof = "group g1 l4 l5 l6";
	private final String gBottom = "group g2 l1 l2 l3 l7 l8 g1.l4";
	private final String gHouse = "group gHouse g1 g2";
	
	private final String clone1 = "clone gHouse2 gHouse 100 100";
	private final String clone2 = "clone gHouse3 gHouse2 100 100";
	
	private final String wrongLine1 = "line l1 100 100 420 210";
	private final String wrongLine2 = "line l2 210 420 150 230";
	
	private final String redo = "redo";
	private final String undo = "undo";
	
	@Override
    public void start(Stage stage) {
		editor = new Editor();
		try
		{
			editor.start(stage);
			//transporter.start(stage);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@Test
	public void testPrototype() {
		
		clickOn(editor.commandLine);
    	write(wrongLine1);
    	clickOn(editor.okButton);
    	
    	assertEquals(1, editor.rootGroup.getChildren().size());

		clickOn(editor.commandLine);
    	write(wrongLine2);
    	clickOn(editor.okButton);
    	
    	assertEquals(2, editor.rootGroup.getChildren().size());
		
    	clickOn(editor.commandLine);
    	write(undo);
    	clickOn(editor.okButton);
    	
    	assertEquals(1, editor.rootGroup.getChildren().size());
		
    	clickOn(editor.commandLine);
    	write(undo);
    	clickOn(editor.okButton);
    	
    	assertEquals(0, editor.rootGroup.getChildren().size());
    	
    	clickOn(editor.commandLine);
    	write(redo);
    	clickOn(editor.okButton);
    	
    	assertEquals(1, editor.rootGroup.getChildren().size());
		
    	clickOn(editor.commandLine);
    	write(redo);
    	clickOn(editor.okButton);
    	
    	assertEquals(2, editor.rootGroup.getChildren().size());
    	
    	clickOn(editor.commandLine);
    	write(undo);
    	clickOn(editor.okButton);
    	
    	assertEquals(1, editor.rootGroup.getChildren().size());
		
    	clickOn(editor.commandLine);
    	write(undo);
    	clickOn(editor.okButton);
    	
    	assertEquals(0, editor.rootGroup.getChildren().size());
		
    	clickOn(editor.commandLine);
    	write(l1);
    	clickOn(editor.okButton);
		
    	clickOn(editor.commandLine);
    	write(l2);
    	clickOn(editor.okButton);

    	clickOn(editor.commandLine);
    	write(l3);
    	clickOn(editor.okButton);
    	
    	clickOn(editor.commandLine);
    	write(l4);
    	clickOn(editor.okButton);
    	
    	clickOn(editor.commandLine);
    	write(l5);
    	clickOn(editor.okButton);
    	
    	clickOn(editor.commandLine);
    	write(l6);
    	clickOn(editor.okButton);

    	clickOn(editor.commandLine);
    	write(l7);
    	clickOn(editor.okButton);
    	
    	clickOn(editor.commandLine);
    	write(l8);
    	clickOn(editor.okButton);    
    	
    	assertEquals(8, editor.rootGroup.getChildren().size());
    	
    	clickOn(editor.commandLine);
    	write(gRoof);
    	clickOn(editor.okButton);    	
    	
    	clickOn(editor.commandLine);
    	write(gBottom);
    	clickOn(editor.okButton);    	
    	
    	clickOn(editor.commandLine);
    	write(gHouse);
    	clickOn(editor.okButton);   
    	
    	assertEquals(11, editor.rootGroup.getChildren().size());
    	
    	clickOn(editor.commandLine);
    	write(clone1);
    	clickOn(editor.okButton);    	
    	
    	clickOn(editor.commandLine);
    	write(clone2);
    	clickOn(editor.okButton);
    	
    	assertEquals(13, editor.rootGroup.getChildren().size());
    	
    	clickOn(editor.commandLine);
    	write("Jobs Done!");
    	clickOn(editor.okButton); 
	}
}
