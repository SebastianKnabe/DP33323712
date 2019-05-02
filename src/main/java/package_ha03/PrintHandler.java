package package_ha03;

public class PrintHandler implements Handler
{

	Assembler miniAssembler;

	@Override
	public boolean readLine(String line)
	{
		if (line.contains("Print"))
		{
			miniAssembler.ergebnis = miniAssembler.stack.pop();
			System.out.println(miniAssembler.ergebnis);
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
