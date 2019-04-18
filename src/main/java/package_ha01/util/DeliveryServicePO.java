package package_ha01.util;

import org.sdmlib.models.pattern.PatternObject;
import package_ha01.DeliveryService;
import package_ha01.util.CatererPO;
import package_ha01.Caterer;
import package_ha01.util.DeliveryServicePO;

public class DeliveryServicePO extends PatternObject<DeliveryServicePO, DeliveryService>
{

    public DeliveryServiceSet allMatches()
   {
      this.setDoAllMatches(true);
      
      DeliveryServiceSet matches = new DeliveryServiceSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((DeliveryService) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public DeliveryServicePO(){
      newInstance(null);
   }

   public DeliveryServicePO(DeliveryService... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public DeliveryServicePO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public void deliver(String addr, String foodNo)
   {
      if (this.getPattern().getHasMatch())
      {
          ((DeliveryService) getCurrentMatch()).deliver(addr, foodNo);
      }
   }

   public CatererPO createSubcontractorPO()
   {
      CatererPO result = new CatererPO(new Caterer[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(DeliveryService.PROPERTY_SUBCONTRACTOR, result);
      
      return result;
   }

   public CatererPO createSubcontractorPO(String modifier)
   {
      CatererPO result = new CatererPO(new Caterer[]{});
      
      result.setModifier(modifier);
      super.hasLink(DeliveryService.PROPERTY_SUBCONTRACTOR, result);
      
      return result;
   }

   public DeliveryServicePO createSubcontractorLink(CatererPO tgt)
   {
      return hasLinkConstraint(tgt, DeliveryService.PROPERTY_SUBCONTRACTOR);
   }

   public DeliveryServicePO createSubcontractorLink(CatererPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, DeliveryService.PROPERTY_SUBCONTRACTOR, modifier);
   }

   public Caterer getSubcontractor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((DeliveryService) this.getCurrentMatch()).getSubcontractor();
      }
      return null;
   }

}
