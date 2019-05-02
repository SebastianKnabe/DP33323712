package package_ha03;

public class StoreHandler implements Handler
{

	Assembler miniAssembler;

	@Override
	public boolean readLine(String line)
	{
		if (line.contains("Store"))
		{
			String[] lines = line.split("Store");
			String name = lines[1].replaceAll(" ", "");
			int value = miniAssembler.stack.pop();
			miniAssembler.symTab.put(name, value);
			return true;
		}
		return false;
	}

	@Override
	public void setAssembler(Assembler miniAssembler)
	{
		this.miniAssembler = miniAssembler;
		miniAssembler.handlers.add(this);
	}

}
