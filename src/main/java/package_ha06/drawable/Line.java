package package_ha06.drawable;

import javafx.scene.canvas.GraphicsContext;

public class Line implements Drawable
{	
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private String name;
	private Drawable parent;
	
	public void draw(GraphicsContext gc) {
		gc.setLineWidth(3);
		gc.strokeLine(this.getX1(), this.getY1(), this.getX2(), this.getY2());
	}

	public int getX1()
	{
		return x1;
	}

	public void setX1(int x1)
	{
		this.x1 = x1;
	}

	public int getX2()
	{
		return x2;
	}

	public void setX2(int x2)
	{
		this.x2 = x2;
	}

	public int getY1()
	{
		return y1;
	}

	public void setY1(int y1)
	{
		this.y1 = y1;
	}

	public int getY2()
	{
		return y2;
	}

	public void setY2(int y2)
	{
		this.y2 = y2;
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
		// TODO Auto-generated method stub
		parent = drawable;
	}

	public Drawable clone()
	{
		Line line = new Line();
		line.setParent(this.parent);
		line.setName(getName() + "clone");
		line.setX1(this.getX1());
		line.setY1(this.getY1());
		line.setX2(this.getX2());
		line.setY2(this.getY2());
		return line;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public void offset(int x, int y) {
		x1 = x + x1;
		x2 = x + x2;
		y1 = y + y1;
		y2 = y + y2;
	}
	
	@Override
	public Drawable searchDrawable(String name)
	{
		if(this.name.equals(name)) {
			return this;
		}
		
		return null;
	}
	
	
}
