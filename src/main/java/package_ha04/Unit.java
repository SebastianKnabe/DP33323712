package package_ha04;

import java.util.ArrayList;

import org.json.JSONException;

public class Unit
{
	int storyPoints = 0;
	String type;
	ArrayList<Unit> children = new ArrayList<Unit>();
	String id;
	
	//nur ein Listner da fuer die Hausaufgabe nicht mehr als einer benoetigt wird
	ChangeListener listener;
	
	public Unit(ChangeListener newListener) {
		id = "id: " + newListener.unitId;
		newListener.unitId++;
		listener = newListener;
		listener.subscribe(this);
	}
	
	public int walk() {
		int sumOfSP = this.storyPoints;
		
		for(Unit child : children) {
			sumOfSP = sumOfSP + child.walk();
		}
		
		return sumOfSP;
	}
	
	public Unit setStoryPoints(int newStoryPoints) {
		firePropertyChange("StoryPoints", this.storyPoints+"", newStoryPoints+"");
		this.storyPoints = newStoryPoints;
		return this;
	}
	
	public Unit setType(String newType) {
		firePropertyChange("Type", this.type, newType);
		this.type = newType;
		return this;
	}
	
	public Unit addUnit(Unit newUnit) {
		ArrayList<Unit> oldValue = new ArrayList<Unit>();
		oldValue.addAll(children);
		children.add(newUnit);
		firePropertyChange("childen", oldValue, children);
		listener.subscribe(newUnit);
		return this;
	}
	
	//TODO siehe Caterer von sdmlib erstellt fuer einen guten ChangeListener
    public void firePropertyChange(String attrName, Object oldValue, Object newValue) {
		try
		{
			listener.propertyChange(this, attrName, oldValue, newValue);
		} catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
