package package_ha06;

import java.util.ArrayList;
import java.util.Stack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import package_ha06.drawable.Group;
import package_ha06.drawable.Line;

public class Editor extends Application
{
	private final int SCREEN_HEIGHT = 600;
	private final int SCREEN_WIDHT = 600;
	
	public Stack<String> lastCommands = new Stack<String>();
	public Stack<String> doCommands = new Stack<String>();
	
	
	VBox vbox = new VBox();
	HBox buttonBox = new HBox();
	public Canvas drawBoard;
	public TextField commandLine = new TextField();
	public Button okButton = new Button("ok");
	public Group rootGroup;
	public CommandHelper commandHelper = new CommandHelper();
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception
	{
		// TODO Auto-generated method stub
		StackPane root = new StackPane();  
		root.getChildren().add(vbox);
		
		drawBoard = new Canvas(SCREEN_WIDHT - 100, SCREEN_HEIGHT - 100);
		vbox.getChildren().add(drawBoard);
		vbox.getChildren().add(buttonBox);
		
		buttonBox.getChildren().add(commandLine);
		buttonBox.getChildren().add(okButton);
		okButton.setOnAction( e -> {
			addComand();
		});
		
		commandHelper.initCommands(this);
		rootGroup = new Group();
		rootGroup.setName("root");
		
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("2D Vector Editor");        
        stage.setScene(scene);        
        stage.show();    
	}
	
	public void addComand() {
		String command = commandLine.getText();
		commandHelper.parseCommand(command);
		commandLine.clear();
		rootGroup.draw(drawBoard.getGraphicsContext2D());
	}
	
	public void clear() {
		drawBoard.getGraphicsContext2D().clearRect(0, 0, SCREEN_WIDHT, SCREEN_HEIGHT);
	}
}
