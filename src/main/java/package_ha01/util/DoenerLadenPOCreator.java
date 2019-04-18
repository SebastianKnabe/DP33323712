package package_ha01.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import package_ha01.DoenerLaden;

public class DoenerLadenPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new DoenerLadenPO(new DoenerLaden[]{});
      } else {
          return new DoenerLadenPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return package_ha01.util.CreatorCreator.createIdMap(sessionID);
   }
}
