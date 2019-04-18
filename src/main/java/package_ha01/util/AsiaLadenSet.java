/*
   Copyright (c) 2019 Basti
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package package_ha01.util;

import de.uniks.networkparser.list.SimpleSet;
import package_ha01.AsiaLaden;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;

public class AsiaLadenSet extends SimpleSet<AsiaLaden>
{
	public Class<?> getTypClass() {
		return AsiaLaden.class;
	}

   public AsiaLadenSet()
   {
      // empty
   }

   public AsiaLadenSet(AsiaLaden... objects)
   {
      for (AsiaLaden obj : objects)
      {
         this.add(obj);
      }
   }

   public AsiaLadenSet(Collection<AsiaLaden> objects)
   {
      this.addAll(objects);
   }

   public static final AsiaLadenSet EMPTY_SET = new AsiaLadenSet().withFlag(AsiaLadenSet.READONLY);


   public AsiaLadenPO createAsiaLadenPO()
   {
      return new AsiaLadenPO(this.toArray(new AsiaLaden[this.size()]));
   }


   public String getEntryType()
   {
      return "package_ha01.AsiaLaden";
   }


   @Override
   public AsiaLadenSet getNewList(boolean keyValue)
   {
      return new AsiaLadenSet();
   }


   @SuppressWarnings("unchecked")
   public AsiaLadenSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<AsiaLaden>)value);
      }
      else if (value != null)
      {
         this.add((AsiaLaden) value);
      }
      
      return this;
   }
   
   public AsiaLadenSet without(AsiaLaden value)
   {
      this.remove(value);
      return this;
   }

}
