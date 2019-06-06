package package_ha06.commands;

import package_ha06.drawable.Drawable;
import package_ha06.drawable.Line;

public class LineCommand implements Command
{
	public final static String COMMAND_NAME = "line";
	
	private Line ownLine;
	private Command nextCommand;
	
	public void parseCommand(String command) {
		String[] splitCommand = command.split(" ");
		if(splitCommand[0].equals(COMMAND_NAME) && splitCommand.length == 6) {
			ownLine = new Line();
			ownLine.setX1(Integer.parseInt(splitCommand[2]));
			ownLine.setY1(Integer.parseInt(splitCommand[3]));
			ownLine.setX2(Integer.parseInt(splitCommand[4]));
			ownLine.setY2(Integer.parseInt(splitCommand[5]));
		} else {
			if(nextCommand != null) {
				nextCommand.parseCommand(command);
			}
		}
	}
	
	public void setDrawable(Drawable draw)
	{
		// TODO Auto-generated method stub
		
	}

	public Drawable getDrawable()
	{
		// TODO Auto-generated method stub
		return ownLine;
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
}
