package package_ha01.util;

import org.sdmlib.models.pattern.PatternObject;
import package_ha01.PizzaShop;

public class PizzaShopPO extends PatternObject<PizzaShopPO, PizzaShop>
{

    public PizzaShopSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PizzaShopSet matches = new PizzaShopSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((PizzaShop) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PizzaShopPO(){
      newInstance(null);
   }

   public PizzaShopPO(PizzaShop... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public PizzaShopPO(String modifier)
   {
      this.setModifier(modifier);
   }
}
