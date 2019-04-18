package package_ha01.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import package_ha01.AsiaLaden;

public class AsiaLadenPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new AsiaLadenPO(new AsiaLaden[]{});
      } else {
          return new AsiaLadenPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return package_ha01.util.CreatorCreator.createIdMap(sessionID);
   }
}
