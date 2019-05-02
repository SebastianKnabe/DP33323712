package package_ha03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Assembler
{
	public ArrayList<Handler> handlers = new ArrayList<Handler>();
	public Stack<Integer> stack = new Stack<Integer>();
	public HashMap<String, Integer> symTab = new HashMap<String, Integer>();
	public int ergebnis;

	public boolean readLine(String line)
	{
		boolean success = false;
		for (Handler h : handlers)
		{
			success = h.readLine(line);
			if (success)
			{
				return true;
			}
		}
		return success;
	}

	public void readCode(String code)
	{
		String[] lines = code.split("\n");
		for (int i = 0; i < lines.length; i++)
		{
			readLine(lines[i]);
		}
	}

}
