package package_ha04;

import org.json.JSONException;

public interface Listener
{
	public void propertyChange(Unit obj,String attrName ,Object oldValue, Object newValue )throws JSONException;
	
	public void subscribe(Unit newUnit);
}
