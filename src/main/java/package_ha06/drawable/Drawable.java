package package_ha06.drawable;

import javafx.scene.canvas.GraphicsContext;

public interface Drawable
{
	void draw(GraphicsContext graphicsContext2D);
	
	Drawable getParent();
	
	void setParent(Drawable drawable);
	
	public String getName();
	
	public Drawable searchDrawable(String name);
	
	public Drawable clone();
	
	public void offset(int x, int y);
	
}


