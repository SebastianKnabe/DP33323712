package package_ha06.commands;

import java.util.regex.Pattern;

import package_ha06.Editor;
import package_ha06.drawable.Drawable;
import package_ha06.drawable.Group;

public class GroupCommand implements Command
{
	public final static String COMMAND_NAME = "group";
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
		// TODO Auto-generated method stub
		String[] splitCommand = command.split(" ");
		if(splitCommand[0].equals(COMMAND_NAME)) {
			
			Group newGroup = new Group();
			newGroup.setName(splitCommand[1]);
			
			for(String name : splitCommand) {
				Drawable searchGroup = editor.rootGroup;
				String searchName = name;
				if(name.contains(".")) {
					String[] nameSplit = searchName.split(Pattern.quote("."));
					searchGroup = editor.rootGroup.searchDrawable(nameSplit[0]);
					searchName = nameSplit[1];
				}
				Drawable tmp = searchGroup.searchDrawable(searchName);
				if(tmp != null) {
					newGroup.addChild(tmp);
					
				}
			}
			
			newGroup.setParent(editor.rootGroup);
			editor.rootGroup.addChild(newGroup);
			editor.lastCommands.push(command);
		} else {
			if(nextCommand != null) {
				nextCommand.parseCommand(command);
			}
		}
	}
}
