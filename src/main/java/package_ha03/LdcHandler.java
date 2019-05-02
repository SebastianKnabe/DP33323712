package package_ha03;

public class LdcHandler implements Handler
{

	Assembler miniAssembler;

	@Override
	public boolean readLine(String line)
	{
		if (line.contains("Ldc"))
		{
			String[] lines = line.split("Ldc");
			int ldcInt = Integer.parseInt(lines[1].replaceAll(" ", ""));
			miniAssembler.stack.push(ldcInt);
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
