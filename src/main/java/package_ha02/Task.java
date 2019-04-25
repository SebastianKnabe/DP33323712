package package_ha02;

public class Task extends Unit
{
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
