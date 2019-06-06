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
import package_ha06.commands.LineCommand;
import package_ha06.drawable.Line;

public class Editor extends Application
{
	private final int SCREEN_HEIGHT = 600;
	private final int SCREEN_WIDHT = 600;
	
	Stack<String> lastCommands = new Stack<String>();
	Stack<String> doCommands = new Stack<String>();
	
	
	VBox vbox = new VBox();
	HBox buttonBox = new HBox();
	private Canvas drawBoard;
	private Label commands = new Label("");
	public TextField commandLine = new TextField();
	public Button okButton = new Button("ok");
	public Button doButton = new Button("do");
	public Button undoButton = new Button("undo");
	
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
		vbox.getChildren().add(commands);
		vbox.getChildren().add(commandLine);
		vbox.getChildren().add(buttonBox);
		
		buttonBox.getChildren().add(okButton);
		buttonBox.getChildren().add(doButton);
		buttonBox.getChildren().add(undoButton);
		
		okButton.setOnAction( e -> {
			addComand();
		});
		undoButton.setOnAction(e -> {
			undo();
		});
		doButton.setOnAction(e -> { 
			doCommands();
		});
		
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("2D Vector Editor");        
        stage.setScene(scene);        
        stage.show();    
	}
	
	public void addComand() {
		String command = commandLine.getText(); 
				lastCommands.push(command);
		LineCommand lineCommand = new LineCommand();
		lineCommand.parseCommand(command);
		updateLabel();
		commandLine.clear();
		
		if(lineCommand.getDrawable() != null) {
			lineCommand.getDrawable().draw(drawBoard.getGraphicsContext2D());
		}
		
		
	}
	
	public void updateLabel() {
		String commands = "";
		for(Object command : lastCommands.toArray()) {
			commands = commands + "\n" + command.toString();
		}
		this.commands.setText(commands);
	}
	
	public void undo() {
		String undo = lastCommands.pop();
		updateLabel();
		doCommands.push(undo);
	}
	
	public void doCommands() {
		String doString = doCommands.pop();
		updateLabel();
		lastCommands.push(doString);
	}
}
