package package_ha02;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestCompsitePattern
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
		
		Unit Feat1 = new Unit().setStoryPoints(8).setType(FEATURE);
		Unit Feat2 = new Unit().setStoryPoints(8).setType(FEATURE);
		Unit Feat3 = new Unit().setStoryPoints(8).setType(FEATURE);
		
		Unit Feat4 = new Unit().setStoryPoints(8).setType(FEATURE);
		Unit Feat5 = new Unit().setStoryPoints(8).setType(FEATURE);
		Unit Feat6 = new Unit().setStoryPoints(8).setType(FEATURE);
		
		Unit Feat7 = new Unit().setStoryPoints(8).setType(FEATURE);
		Unit Feat8 = new Unit().setStoryPoints(8).setType(FEATURE);
		Unit Feat9 = new Unit().setStoryPoints(8).setType(FEATURE);
		
		Unit Feat10 = new Unit().setStoryPoints(8).setType(FEATURE);
		Unit Feat11 = new Unit().setStoryPoints(8).setType(FEATURE);
		Unit Feat12 = new Unit().setStoryPoints(8).setType(FEATURE);
		
		Unit Feat13 = new Unit().setStoryPoints(8).setType(FEATURE);
		Unit Feat14 = new Unit().setStoryPoints(8).setType(FEATURE);
		Unit Feat15 = new Unit().setStoryPoints(8).setType(FEATURE);
		
		Unit Feat16 = new Unit().setStoryPoints(8).setType(FEATURE);
		Unit Feat17 = new Unit().setStoryPoints(8).setType(FEATURE);
		Unit Feat18 = new Unit().setStoryPoints(8).setType(FEATURE);
		
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
		int sumOfSP = fulibProjekt.walk();
		assertEquals(sumOfSP, 144);
		
		//Aufgabenteil d
		Unit Epic = new Unit().setType(EPIC).addUnit(WT1819).addUnit(ST19);
		fulibProjekt.children.clear();
		fulibProjekt.addUnit(Epic);
		
		sumOfSP = fulibProjekt.walk();
		assertEquals(sumOfSP, 144);
		
		//Aufgabenteil e
		Unit task1 = new Unit().setType(TASK);
		Unit task2 = new Unit().setType(TASK);
		Unit task3 = new Unit().setType(TASK);
		Unit task4 = new Unit().setType(TASK);
		
		Feat18.addUnit(task1).addUnit(task2).addUnit(task3).addUnit(task4);
		
		sumOfSP = fulibProjekt.walk();
		assertEquals(sumOfSP, 144);
		
		//Aufgabenteil f
		Unit docu = new Unit().setType(TASK);
		Epic.addUnit(docu);
		
		sumOfSP = fulibProjekt.walk();
		assertEquals(sumOfSP, 144);
	}
}
