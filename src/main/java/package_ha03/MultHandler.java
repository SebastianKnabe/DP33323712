package package_ha03;

public class MultHandler implements Handler
{
	Assembler miniAssembler;

	@Override
	public boolean readLine(String line)
	{
		if (line.contains("Mult"))
		{
			int x1 = miniAssembler.stack.pop();
			int x2 = miniAssembler.stack.pop();
			miniAssembler.stack.push(x1 * x2);
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
