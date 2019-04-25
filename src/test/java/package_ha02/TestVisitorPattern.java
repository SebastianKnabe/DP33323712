package package_ha02;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestVisitorPattern
{
	private final String PROJEKT = "Projekt";
	private final String RELEASE = "Release";
	private final String SPRINT = "Sprint";
	private final String FEATURE = "Feature";
	private final String EPIC = "Epic";
	private final String TASK = "Task";
	
	@Test
	public void testCompositePattern() {
		// Aufgabenteil b
		Unit fulibProjekt = new Unit().setType(PROJEKT);
		
		Unit WT1819 = new Unit().setType(RELEASE);
		Unit ST19 = new Unit().setType(RELEASE);
		
		Unit SprintWT1 = new Unit().setType(SPRINT);
		Unit SprintWT2 = new Unit().setType(SPRINT);
		Unit SprintWT3 = new Unit().setType(SPRINT);
		
		Unit SprintST1 = new Unit().setType(SPRINT);
		Unit SprintST2 = new Unit().setType(SPRINT);
		Unit SprintST3 = new Unit().setType(SPRINT);
		
		Feature Feat1 = new Feature().setStoryPoints(8);
		Feature Feat2 = new Feature().setStoryPoints(8);
		Feature Feat3 = new Feature().setStoryPoints(8);
		
		Feature Feat4 = new Feature().setStoryPoints(8);
		Feature Feat5 = new Feature().setStoryPoints(8);
		Feature Feat6 = new Feature().setStoryPoints(8);
		
		Feature Feat7 = new Feature().setStoryPoints(8);
		Feature Feat8 = new Feature().setStoryPoints(8);
		Feature Feat9 = new Feature().setStoryPoints(8);
		
		Feature Feat10 = new Feature().setStoryPoints(8);
		Feature Feat11 = new Feature().setStoryPoints(8);
		Feature Feat12 = new Feature().setStoryPoints(8);
		
		Feature Feat13 = new Feature().setStoryPoints(8);
		Feature Feat14 = new Feature().setStoryPoints(8);
		Feature Feat15 = new Feature().setStoryPoints(8);
		
		Feature Feat16 = new Feature().setStoryPoints(8);
		Feature Feat17 = new Feature().setStoryPoints(8);
		Feature Feat18 = new Feature().setStoryPoints(8);
		
		fulibProjekt.addUnit(WT1819).addUnit(ST19);
		
		WT1819.addUnit(SprintWT1).addUnit(SprintWT2).addUnit(SprintWT3);
		ST19.addUnit(SprintST1).addUnit(SprintST2).addUnit(SprintST3);
		
		SprintWT1.addUnit(Feat1).addUnit(Feat2).addUnit(Feat3);
		SprintWT2.addUnit(Feat4).addUnit(Feat5).addUnit(Feat6);
		SprintWT3.addUnit(Feat7).addUnit(Feat8).addUnit(Feat9);
		
		SprintST1.addUnit(Feat10).addUnit(Feat11).addUnit(Feat12);
		SprintST2.addUnit(Feat13).addUnit(Feat14).addUnit(Feat15);
		SprintST3.addUnit(Feat16).addUnit(Feat17).addUnit(Feat18);
		
		//Aufgabenteil c
		StoryPointCounter SPC = new StoryPointCounter();
		fulibProjekt.accept(SPC);
		assertEquals(SPC.sumOfSP, 144);
		
		//Aufgabenteil d
		Unit Epic = new Unit().setType(EPIC).addUnit(WT1819).addUnit(ST19);
		fulibProjekt.children.clear();
		fulibProjekt.addUnit(Epic);
		
		SPC.sumOfSP = 0;
		fulibProjekt.accept(SPC);
		assertEquals(SPC.sumOfSP, 144);
		
		//Aufgabenteil e
		Unit task1 = new Unit().setType(TASK);
		Unit task2 = new Unit().setType(TASK);
		Unit task3 = new Unit().setType(TASK);
		Unit task4 = new Unit().setType(TASK);
		
		Feat18.addUnit(task1).addUnit(task2).addUnit(task3).addUnit(task4);
		
		SPC.sumOfSP = 0;
		fulibProjekt.accept(SPC);
		assertEquals(SPC.sumOfSP, 144);
		
		//Aufgabenteil f
		Unit docu = new Unit().setType(TASK);
		Epic.addUnit(docu);
		
		SPC.sumOfSP = 0;
		fulibProjekt.accept(SPC);
		assertEquals(SPC.sumOfSP, 144);
	}
}
