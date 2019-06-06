package package_ha06.commands;

import package_ha06.Editor;
import package_ha06.drawable.Drawable;

public interface Command
{	
	void setNextCommand(Command command);
	
	Command getNextCommand();
	
	void parseCommand(String command);
	
	void setEditor(Editor editor);
}
