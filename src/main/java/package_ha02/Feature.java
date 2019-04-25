package package_ha02;

public class Feature extends Unit
{
	public Feature setStoryPoints(int newStoryPoints) {
		this.storyPoints = newStoryPoints;
		return this;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
