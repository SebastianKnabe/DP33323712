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
import package_ha01.DoenerLaden;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;

public class DoenerLadenSet extends SimpleSet<DoenerLaden>
{
	public Class<?> getTypClass() {
		return DoenerLaden.class;
	}

   public DoenerLadenSet()
   {
      // empty
   }

   public DoenerLadenSet(DoenerLaden... objects)
   {
      for (DoenerLaden obj : objects)
      {
         this.add(obj);
      }
   }

   public DoenerLadenSet(Collection<DoenerLaden> objects)
   {
      this.addAll(objects);
   }

   public static final DoenerLadenSet EMPTY_SET = new DoenerLadenSet().withFlag(DoenerLadenSet.READONLY);


   public DoenerLadenPO createDoenerLadenPO()
   {
      return new DoenerLadenPO(this.toArray(new DoenerLaden[this.size()]));
   }


   public String getEntryType()
   {
      return "package_ha01.DoenerLaden";
   }


   @Override
   public DoenerLadenSet getNewList(boolean keyValue)
   {
      return new DoenerLadenSet();
   }


   @SuppressWarnings("unchecked")
   public DoenerLadenSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<DoenerLaden>)value);
      }
      else if (value != null)
      {
         this.add((DoenerLaden) value);
      }
      
      return this;
   }
   
   public DoenerLadenSet without(DoenerLaden value)
   {
      this.remove(value);
      return this;
   }

}
