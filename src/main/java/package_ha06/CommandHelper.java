package package_ha06;

import package_ha06.commands.CloneCommand;
import package_ha06.commands.GroupCommand;
import package_ha06.commands.LineCommand;
import package_ha06.commands.RedoCommand;
import package_ha06.commands.UndoCommand;

public class CommandHelper
{
	LineCommand line;
	GroupCommand group;
	CloneCommand clone;
	RedoCommand redo;
	UndoCommand undo;
	
	public void initCommands(Editor editor) {
		line = new LineCommand();
		group = new GroupCommand();
		clone = new CloneCommand();
		redo = new RedoCommand();
		undo = new UndoCommand();
		
		line.setEditor(editor);
		group.setEditor(editor);
		clone.setEditor(editor);
		redo.setEditor(editor);
		undo.setEditor(editor);
		
		line.setNextCommand(group);
		group.setNextCommand(clone);
		clone.setNextCommand(redo);
		redo.setNextCommand(undo);
	}
	
	public void parseCommand(String command) {
		line.parseCommand(command);
	}
}
