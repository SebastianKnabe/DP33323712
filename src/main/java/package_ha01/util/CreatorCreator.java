package package_ha01.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new BurgerBudeCreator());
      jsonIdMap.with(new BurgerBudePOCreator());
      jsonIdMap.with(new CatererCreator());
      jsonIdMap.with(new CatererPOCreator());
      jsonIdMap.with(new DeliveryServiceCreator());
      jsonIdMap.with(new DeliveryServicePOCreator());
      jsonIdMap.with(new DoenerLadenCreator());
      jsonIdMap.with(new DoenerLadenPOCreator());
      jsonIdMap.with(new PizzaShopCreator());
      jsonIdMap.with(new PizzaShopPOCreator());
      return jsonIdMap;
   }
}
