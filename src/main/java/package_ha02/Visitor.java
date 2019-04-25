package package_ha02;

public class Visitor
{	
	public void visit(Unit unit) {
		visitKids(unit);
	}
	public void visit(Task task) {
		visitKids(task);
	}
	
    public void visit(Feature feature) {
    	visitKids(feature);
    }
    
    public void visitKids(Unit unit) {
    	for(Unit child : unit.children) {
			child.accept(this);
		}
    }
}
