package package_ha01.util;

import org.sdmlib.models.pattern.PatternObject;
import package_ha01.BurgerBude;

public class BurgerBudePO extends PatternObject<BurgerBudePO, BurgerBude>
{

    public BurgerBudeSet allMatches()
   {
      this.setDoAllMatches(true);
      
      BurgerBudeSet matches = new BurgerBudeSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((BurgerBude) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public BurgerBudePO(){
      newInstance(null);
   }

   public BurgerBudePO(BurgerBude... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public BurgerBudePO(String modifier)
   {
      this.setModifier(modifier);
   }
}
