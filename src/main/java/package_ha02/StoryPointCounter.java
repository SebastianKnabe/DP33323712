package package_ha02;

public class StoryPointCounter extends Visitor
{
	int sumOfSP = 0;
	
	public void visit(Task task) {
		sumOfSP = sumOfSP + task.storyPoints;
		visitKids(task);
	}
	
    public void visit(Feature feature) {
    	sumOfSP = sumOfSP + feature.storyPoints;
    	visitKids(feature);
    }

}
