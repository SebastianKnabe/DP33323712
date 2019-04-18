package package_ha01.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import package_ha01.Caterer;

public class CatererPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new CatererPO(new Caterer[]{});
      } else {
          return new CatererPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return package_ha01.util.CreatorCreator.createIdMap(sessionID);
   }
}
