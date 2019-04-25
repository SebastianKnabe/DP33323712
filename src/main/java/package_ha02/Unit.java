package package_ha02;

import java.util.ArrayList;

public class Unit
{
	int storyPoints = 0;
	String type;
	ArrayList<Unit> children = new ArrayList<Unit>();
	
	public int walk() {
		int sumOfSP = this.storyPoints;
		
		for(Unit child : children) {
			sumOfSP = sumOfSP + child.walk();
		}
		
		return sumOfSP;
	}
	
	public Unit setStoryPoints(int newStoryPoints) {
		this.storyPoints = newStoryPoints;
		return this;
	}
	
	public Unit setType(String newType) {
		this.type = newType;
		return this;
	}
	
	public Unit addUnit(Unit newUnit) {
		children.add(newUnit);
		return this;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
