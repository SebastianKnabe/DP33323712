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
import package_ha01.Caterer;
import de.uniks.networkparser.interfaces.Condition;
import package_ha01.BurgerBude;
import package_ha01.util.BurgerBudeSet;
import package_ha01.DoenerLaden;
import package_ha01.util.DoenerLadenSet;
import package_ha01.PizzaShop;
import package_ha01.util.PizzaShopSet;
import java.util.Collection;
import package_ha01.AsiaLaden;
import package_ha01.util.AsiaLadenSet;

public class CatererSet extends SimpleSet<Caterer>
{
	public Class<?> getTypClass() {
		return Caterer.class;
	}

   public CatererSet()
   {
      // empty
   }

   public CatererSet(Caterer... objects)
   {
      for (Caterer obj : objects)
      {
         this.add(obj);
      }
   }

   public CatererSet(Collection<Caterer> objects)
   {
      this.addAll(objects);
   }

   public static final CatererSet EMPTY_SET = new CatererSet().withFlag(CatererSet.READONLY);


   public CatererPO createCatererPO()
   {
      return new CatererPO(this.toArray(new Caterer[this.size()]));
   }


   public String getEntryType()
   {
      return "package_ha01.Caterer";
   }


   @Override
   public CatererSet getNewList(boolean keyValue)
   {
      return new CatererSet();
   }


   public BurgerBudeSet instanceOfBurgerBude()
   {
      BurgerBudeSet result = new BurgerBudeSet();
      
      for(Object obj : this)
      {
         if (obj instanceof BurgerBude)
         {
            result.with(obj);
         }
      }
      
      return result;
   }

   public DoenerLadenSet instanceOfDoenerLaden()
   {
      DoenerLadenSet result = new DoenerLadenSet();
      
      for(Object obj : this)
      {
         if (obj instanceof DoenerLaden)
         {
            result.with(obj);
         }
      }
      
      return result;
   }

   public PizzaShopSet instanceOfPizzaShop()
   {
      PizzaShopSet result = new PizzaShopSet();
      
      for(Object obj : this)
      {
         if (obj instanceof PizzaShop)
         {
            result.with(obj);
         }
      }
      
      return result;
   }

   @SuppressWarnings("unchecked")
   public CatererSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Caterer>)value);
      }
      else if (value != null)
      {
         this.add((Caterer) value);
      }
      
      return this;
   }
   
   public CatererSet without(Caterer value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public CatererSet deliver(String addr, String foodNo)
   {
      for (Caterer obj : this)
      {
         obj.deliver(addr, foodNo);
      }
      return this;
   }



   public AsiaLadenSet instanceOfAsiaLaden()
   {
      AsiaLadenSet result = new AsiaLadenSet();
      
      for(Object obj : this)
      {
         if (obj instanceof AsiaLaden)
         {
            result.with(obj);
         }
      }
      
      return result;
   }}
