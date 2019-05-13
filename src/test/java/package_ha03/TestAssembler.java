package package_ha03;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// HA03: 10/10
public class TestAssembler
{
	@Test
	public void testMiniAssembler()
	{

		String code = "Ldc 6   \n"
					 +"Ldc 7   \n"
					 +"Mult    \n"
					 +"Store x \n"
					 +"Ld x    \n"
					 +"Print   \n";


		// init Assembler
		Assembler miniAssembler = new Assembler();
		new LdcHandler().setAssembler(miniAssembler);
		new MultHandler().setAssembler(miniAssembler);
		new StoreHandler().setAssembler(miniAssembler);
		new LdHandler().setAssembler(miniAssembler);
		new PrintHandler().setAssembler(miniAssembler);

		miniAssembler.readCode(code);

		assertEquals(42, miniAssembler.ergebnis);

	}
}
