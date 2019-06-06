package package_ha06;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;
import package_ha06.Editor;

public class PrototypeTest extends ApplicationTest
{
	Editor editor;
	private final String l1 = "line l1 100 100 100 200";
	private final String l2 = "line l2 100 200 200 100";
	private final String l3 = "line l3 200 100 200 200";
	private final String l4 = "line l4 200 200 100 200";
	private final String l5 = "line l5 100 200 150 250";
	private final String l6 = "line l6 150 250 200 200";
	private final String l7 = "line l7 200 200 100 100";
	private final String l8 = "line l8 100 100 200 100";
	
	private final String gRoof = "g1 l4 l5 l6";
	private final String gBottom = "g2 l1 l2 l3 l7 l8 g1.l4";
	private final String gHouse = "g1 g2";
	
	private final String clone1 = "gHouse2 gHouse 10 10";
	private final String clone2 = "gHouse3 ghouse2 10 10";
	
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
		/*
		clickOn(editor.commandLine);
    	write(wrongLine1);
    	clickOn(editor.okButton);

		clickOn(editor.commandLine);
    	write(wrongLine2);
    	clickOn(editor.okButton);
		
    	clickOn(editor.undoButton);
    	clickOn(editor.undoButton);
    	
    	clickOn(editor.doButton);
    	clickOn(editor.doButton);
    	
    	clickOn(editor.undoButton);
    	clickOn(editor.undoButton);
		*/
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
    	
	}
}
