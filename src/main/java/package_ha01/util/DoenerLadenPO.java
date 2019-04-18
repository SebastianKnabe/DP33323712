package package_ha01.util;

import org.sdmlib.models.pattern.PatternObject;
import package_ha01.DoenerLaden;

public class DoenerLadenPO extends PatternObject<DoenerLadenPO, DoenerLaden>
{

    public DoenerLadenSet allMatches()
   {
      this.setDoAllMatches(true);
      
      DoenerLadenSet matches = new DoenerLadenSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((DoenerLaden) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public DoenerLadenPO(){
      newInstance(null);
   }

   public DoenerLadenPO(DoenerLaden... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public DoenerLadenPO(String modifier)
   {
      this.setModifier(modifier);
   }
}
