package package_ha06.drawable;

import java.util.ArrayList;
import java.util.HashSet;

import javafx.scene.canvas.GraphicsContext;

public class Group implements Drawable
{
	private ArrayList<Drawable> children = new ArrayList<Drawable>();
	Drawable parent;
	private String name;

	@Override
	public void draw(GraphicsContext graphicsContext2D)
	{
		// TODO Auto-generated method stub
		if(getChildren() != null) {
			for(Drawable child : getChildren()) {
				child.draw(graphicsContext2D);
			}
		}
		
	}

	@Override
	public Drawable getParent()
	{
		// TODO Auto-generated method stub
		return parent;
	}

	@Override
	public void setParent(Drawable drawable)
	{
		parent = drawable;
	}

	public ArrayList<Drawable> getChildren()
	{
		return children;
	}

	public void setChildren(ArrayList<Drawable> children)
	{
		this.children = children;
	}
	
	public void addChild(Drawable draw) {
		this.children.add(draw);
		draw.setParent(this);
	}

	public Drawable clone()
	{
		Group group = new Group();
		group.setParent(this.parent);
		group.setName(this.getName() + "clone");
		if (children == null) return group;
		for (Drawable drawable : children) {
			group.addChild((drawable.clone()));
		}
		return group;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public Drawable searchDrawable(String name)
	{
		if(this.name.equals(name)) {
			return this;
		}
		
		Drawable tmp = null;
		if(children != null ) {
			for(Drawable draw : children) {
				Drawable tmp2 = draw.searchDrawable(name);
				if(tmp2 != null) {
					tmp = tmp2;
				}
			}
		}
		
		return tmp;
	}
	
	public void offset(int x, int y) {
		if(children != null ) {
			for(Drawable draw : children) {
				draw.offset(x, y);
			}
		}
	}
}
