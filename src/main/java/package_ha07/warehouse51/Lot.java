package package_ha07.warehouse51;

import java.beans.PropertyChangeSupport;

import java.beans.PropertyChangeListener;

public class Lot 
{

   public static final String PROPERTY_id = "id";

   private String id;

   public String getId()
   {
      return id;
   }

   public Lot setId(String value)
   {
      if (value == null ? this.id != null : ! value.equals(this.id))
      {
         String oldValue = this.id;
         this.id = value;
         firePropertyChange("id", oldValue, value);
      }
      return this;
   }


   public static final String PROPERTY_lotSize = "lotSize";

   private double lotSize;

   public double getLotSize()
   {
      return lotSize;
   }

   public Lot setLotSize(double value)
   {
      if (value != this.lotSize)
      {
         double oldValue = this.lotSize;
         this.lotSize = value;
         firePropertyChange("lotSize", oldValue, value);
      }
      return this;
   }


   public static final String PROPERTY_warehouseProduct = "warehouseProduct";

   private WarehouseProduct warehouseProduct = null;

   public WarehouseProduct getWarehouseProduct()
   {
      return this.warehouseProduct;
   }

   public Lot setWarehouseProduct(WarehouseProduct value)
   {
      if (this.warehouseProduct != value)
      {
         WarehouseProduct oldValue = this.warehouseProduct;
         if (this.warehouseProduct != null)
         {
            this.warehouseProduct = null;
            oldValue.withoutLots(this);
         }
         this.warehouseProduct = value;
         if (value != null)
         {
            value.withLots(this);
         }
         firePropertyChange("warehouseProduct", oldValue, value);
      }
      return this;
   }



   public static final String PROPERTY_place = "place";

   private PalettePlace place = null;

   public PalettePlace getPlace()
   {
      return this.place;
   }

   public Lot setPlace(PalettePlace value)
   {
      if (this.place != value)
      {
         PalettePlace oldValue = this.place;
         if (this.place != null)
         {
            this.place = null;
            oldValue.setLot(null);
         }
         this.place = value;
         if (value != null)
         {
            value.setLot(this);
         }
         firePropertyChange("place", oldValue, value);
      }
      return this;
   }



   protected PropertyChangeSupport listeners = null;

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null)
      {
         listeners.firePropertyChange(propertyName, oldValue, newValue);
         return true;
      }
      return false;
   }

   public boolean addPropertyChangeListener(PropertyChangeListener listener)
   {
      if (listeners == null)
      {
         listeners = new PropertyChangeSupport(this);
      }
      listeners.addPropertyChangeListener(listener);
      return true;
   }

   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
   {
      if (listeners == null)
      {
         listeners = new PropertyChangeSupport(this);
      }
      listeners.addPropertyChangeListener(propertyName, listener);
      return true;
   }

   public boolean removePropertyChangeListener(PropertyChangeListener listener)
   {
      if (listeners != null)
      {
         listeners.removePropertyChangeListener(listener);
      }
      return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener)
   {
      if (listeners != null)
      {
         listeners.removePropertyChangeListener(propertyName, listener);
      }
      return true;
   }

   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();

      result.append(" ").append(this.getId());


      return result.substring(1);
   }

   public void removeYou()
   {
      this.setWarehouseProduct(null);
      this.setPlace(null);

   }


}