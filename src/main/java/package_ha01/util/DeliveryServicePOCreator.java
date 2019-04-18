package package_ha01.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import package_ha01.DeliveryService;

public class DeliveryServicePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new DeliveryServicePO(new DeliveryService[]{});
      } else {
          return new DeliveryServicePO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return package_ha01.util.CreatorCreator.createIdMap(sessionID);
   }
}
