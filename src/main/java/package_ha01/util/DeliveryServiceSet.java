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
import package_ha01.DeliveryService;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import package_ha01.util.CatererSet;
import package_ha01.Caterer;

public class DeliveryServiceSet extends SimpleSet<DeliveryService>
{
	public Class<?> getTypClass() {
		return DeliveryService.class;
	}

   public DeliveryServiceSet()
   {
      // empty
   }

   public DeliveryServiceSet(DeliveryService... objects)
   {
      for (DeliveryService obj : objects)
      {
         this.add(obj);
      }
   }

   public DeliveryServiceSet(Collection<DeliveryService> objects)
   {
      this.addAll(objects);
   }

   public static final DeliveryServiceSet EMPTY_SET = new DeliveryServiceSet().withFlag(DeliveryServiceSet.READONLY);


   public DeliveryServicePO createDeliveryServicePO()
   {
      return new DeliveryServicePO(this.toArray(new DeliveryService[this.size()]));
   }


   public String getEntryType()
   {
      return "package_ha01.DeliveryService";
   }


   @Override
   public DeliveryServiceSet getNewList(boolean keyValue)
   {
      return new DeliveryServiceSet();
   }


   @SuppressWarnings("unchecked")
   public DeliveryServiceSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<DeliveryService>)value);
      }
      else if (value != null)
      {
         this.add((DeliveryService) value);
      }
      
      return this;
   }
   
   public DeliveryServiceSet without(DeliveryService value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public DeliveryServiceSet deliver(String addr, String foodNo)
   {
      for (DeliveryService obj : this)
      {
         obj.deliver(addr, foodNo);
      }
      return this;
   }

   /**
    * Loop through the current set of DeliveryService objects and collect a set of the Caterer objects reached via subcontractor. 
    * 
    * @return Set of Caterer objects reachable via subcontractor
    */
   public CatererSet getSubcontractor()
   {
      CatererSet result = new CatererSet();
      
      for (DeliveryService obj : this)
      {
         result.with(obj.getSubcontractor());
      }
      
      return result;
   }

   /**
    * Loop through the current set of DeliveryService objects and collect all contained objects with reference subcontractor pointing to the object passed as parameter. 
    * 
    * @param value The object required as subcontractor neighbor of the collected results. 
    * 
    * @return Set of Caterer objects referring to value via subcontractor
    */
   public DeliveryServiceSet filterSubcontractor(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      DeliveryServiceSet answer = new DeliveryServiceSet();
      
      for (DeliveryService obj : this)
      {
         if (neighbors.contains(obj.getSubcontractor()) || (neighbors.isEmpty() && obj.getSubcontractor() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the DeliveryService object passed as parameter to the Subcontractor attribute of each of it. 
    * 
    * @param value value    * @return The original set of ModelType objects now with the new neighbor attached to their Subcontractor attributes.
    */
   public DeliveryServiceSet withSubcontractor(Caterer value)
   {
      for (DeliveryService obj : this)
      {
         obj.withSubcontractor(value);
      }
      
      return this;
   }

}
