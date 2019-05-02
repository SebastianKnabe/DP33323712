package package_ha03;

public class LdHandler implements Handler
{
	Assembler miniAssembler;

	@Override
	public boolean readLine(String line)
	{
		if (line.contains("Ld"))
		{
			String[] lines = line.split("Ld");
			String name = lines[1].replaceAll(" ", "");
			int value = miniAssembler.symTab.get(name);
			miniAssembler.stack.push(value);
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
