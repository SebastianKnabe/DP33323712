package package_ha06.commands;

import package_ha06.Editor;
import package_ha06.drawable.Drawable;
import package_ha06.drawable.Line;

public class LineCommand implements Command
{
	public final static String COMMAND_NAME = "line";
	
	private Editor editor;
	private Command nextCommand;
	
	public void parseCommand(String command) {
		String[] splitCommand = command.split(" ");
		if(splitCommand[0].equals(COMMAND_NAME) && splitCommand.length == 6) {
			Line newLine = new Line();
			newLine.setName(splitCommand[1]);
			newLine.setX1(Integer.parseInt(splitCommand[2]));
			newLine.setY1(Integer.parseInt(splitCommand[3]));
			newLine.setX2(Integer.parseInt(splitCommand[4]));
			newLine.setY2(Integer.parseInt(splitCommand[5]));
			editor.rootGroup.getChildren().add(newLine);
			editor.lastCommands.push(command);
		} else {
			if(nextCommand != null) {
				nextCommand.parseCommand(command);
			}
		}
	}

	@Override
	public void setNextCommand(Command command)
	{
		nextCommand = command;
	}

	@Override
	public Command getNextCommand()
	{
		// TODO Auto-generated method stub
		return nextCommand;
	}
	
	@Override
	public void setEditor(Editor editor)
	{
		this.editor = editor;
	}
}
