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
import package_ha01.PizzaShop;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;

public class PizzaShopSet extends SimpleSet<PizzaShop>
{
	public Class<?> getTypClass() {
		return PizzaShop.class;
	}

   public PizzaShopSet()
   {
      // empty
   }

   public PizzaShopSet(PizzaShop... objects)
   {
      for (PizzaShop obj : objects)
      {
         this.add(obj);
      }
   }

   public PizzaShopSet(Collection<PizzaShop> objects)
   {
      this.addAll(objects);
   }

   public static final PizzaShopSet EMPTY_SET = new PizzaShopSet().withFlag(PizzaShopSet.READONLY);


   public PizzaShopPO createPizzaShopPO()
   {
      return new PizzaShopPO(this.toArray(new PizzaShop[this.size()]));
   }


   public String getEntryType()
   {
      return "package_ha01.PizzaShop";
   }


   @Override
   public PizzaShopSet getNewList(boolean keyValue)
   {
      return new PizzaShopSet();
   }


   @SuppressWarnings("unchecked")
   public PizzaShopSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<PizzaShop>)value);
      }
      else if (value != null)
      {
         this.add((PizzaShop) value);
      }
      
      return this;
   }
   
   public PizzaShopSet without(PizzaShop value)
   {
      this.remove(value);
      return this;
   }

}
