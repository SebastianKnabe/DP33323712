package package_ha01.util;

import org.sdmlib.models.pattern.PatternObject;
import package_ha01.Caterer;

public class CatererPO extends PatternObject<CatererPO, Caterer>
{

    public CatererSet allMatches()
   {
      this.setDoAllMatches(true);
      
      CatererSet matches = new CatererSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Caterer) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public CatererPO(){
      newInstance(null);
   }

   public CatererPO(Caterer... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public CatererPO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public void deliver(String addr, String foodNo)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Caterer) getCurrentMatch()).deliver(addr, foodNo);
      }
   }

}
