package package_ha06.commands;

import package_ha06.Editor;
import package_ha06.drawable.Drawable;

public class UndoCommand implements Command
{
	public final static String COMMAND_NAME = "undo";
	private Command nextCommand;
    private Editor editor;
	
	@Override
	public void setEditor(Editor editor)
	{
		this.editor = editor;
		
	}
	
	
	@Override
	public void setNextCommand(Command command)
	{
		// TODO Auto-generated method stub
		nextCommand = command;
		
	}

	@Override
	public Command getNextCommand()
	{
		// TODO Auto-generated method stub
		return nextCommand;
	}

	@Override
	public void parseCommand(String command)
	{
		// TODO Auto-generated method stub
		String[] splitCommand = command.split(" ");
		if(splitCommand[0].equals(COMMAND_NAME)) {
			String lastCommand = editor.lastCommands.pop();
			editor.doCommands.push(lastCommand);
			Drawable draw = editor.rootGroup.searchDrawable(lastCommand.split(" ")[1]);
			editor.rootGroup.getChildren().remove(draw);
			editor.clear();
		} else {
			if(nextCommand != null) {
				nextCommand.parseCommand(command);
			}
		}
		
	}

}
