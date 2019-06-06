package package_ha06.commands;

import package_ha06.drawable.Drawable;
import package_ha06.drawable.Line;

public class CloneCommand implements Command
{
	public final static String COMMAND_NAME = "clone";
	private Command nextCommand;
	
	public void setDrawable(Drawable draw)
	{
		// TODO Auto-generated method stub
		
	}

	public Drawable getDrawable()
	{
		// TODO Auto-generated method stub
		return null;
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
		String[] splitCommand = command.split(" ");
		if(splitCommand[0].equals(COMMAND_NAME)) {
			
		} else {
			if(nextCommand != null) {
				nextCommand.parseCommand(command);
			}
		}
		
	}

}
