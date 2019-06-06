package package_ha06.commands;

import package_ha06.Editor;
import package_ha06.drawable.Drawable;
import package_ha06.drawable.Group;
import package_ha06.drawable.Line;

public class CloneCommand implements Command
{
	public final static String COMMAND_NAME = "clone";
	private Command nextCommand;
    private Editor editor;
	
	@Override
	public void setEditor(Editor editor)
	{
		this.editor = editor;
		
	}
	
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
			System.out.println("\n" + command);
			System.out.print(splitCommand[0] + " ");
			Group newGroup = new Group();
			newGroup.setName(splitCommand[1]);
			System.out.print(newGroup.getName() + " ");
			Drawable search = editor.rootGroup.searchDrawable(splitCommand[2]);
			if(search != null) {
				newGroup.addChild(search.clone());
				int x = Integer.parseInt(splitCommand[3]);
				int y = Integer.parseInt(splitCommand[4]);
				newGroup.offset(x, y);
			} else {
				System.out.print(splitCommand[2] + " ");
			}
			
			editor.rootGroup.getChildren().add(newGroup);
			editor.lastCommands.push(command);
		} else {
			if(nextCommand != null) {
				nextCommand.parseCommand(command);
			}
		}
		
	}

}
