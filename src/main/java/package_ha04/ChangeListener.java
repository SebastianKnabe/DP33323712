package package_ha04;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangeListener implements Listener
{
	HashMap<Object, String> objects = new HashMap<Object, String>();
	public int unitId = 0;
	public BufferedWriter pWriter = null;
	public FileWriter fWriter = null;
	
	@Override
	public void propertyChange(Unit obj, String attrName, Object oldValue, Object newValue) throws JSONException
	{	
		JSONObject propertyChange = new JSONObject();
		propertyChange.put("Timestamp",System.currentTimeMillis());
		propertyChange.put("ObjectId", obj.id);
		propertyChange.put("className", obj.getClass().getName());
		propertyChange.put("changedProperty", attrName);
		propertyChange.put("oldValue", oldValue);
		propertyChange.put("newValue", newValue);
		
		try
		{
			if(pWriter == null) {
				fWriter = new FileWriter("src/test/java/package_ha04/logFile.txt");
				pWriter = new BufferedWriter(fWriter);
			}
			pWriter.write(propertyChange.toString());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void subscribe(Unit newUnit)
	{
		objects.put(newUnit, newUnit.id);
		newUnit.listener = this;
		
	}

}
