package package_ha01.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import package_ha01.BurgerBude;

public class BurgerBudePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new BurgerBudePO(new BurgerBude[]{});
      } else {
          return new BurgerBudePO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return package_ha01.util.CreatorCreator.createIdMap(sessionID);
   }
}
