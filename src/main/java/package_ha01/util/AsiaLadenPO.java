package package_ha01.util;

import org.sdmlib.models.pattern.PatternObject;
import package_ha01.AsiaLaden;

public class AsiaLadenPO extends PatternObject<AsiaLadenPO, AsiaLaden>
{

    public AsiaLadenSet allMatches()
   {
      this.setDoAllMatches(true);
      
      AsiaLadenSet matches = new AsiaLadenSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((AsiaLaden) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public AsiaLadenPO(){
      newInstance(null);
   }

   public AsiaLadenPO(AsiaLaden... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public AsiaLadenPO(String modifier)
   {
      this.setModifier(modifier);
   }
}
