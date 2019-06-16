package package_ha07.warehouse51;

import java.beans.PropertyChangeSupport;

import java.beans.PropertyChangeListener;

public class WarehouseOrder 
{

   public static final String PROPERTY_address = "address";

   private String address;

   public String getAddress()
   {
      return address;
   }

   public WarehouseOrder setAddress(String value)
   {
      if (value == null ? this.address != null : ! value.equals(this.address))
      {
         String oldValue = this.address;
         this.address = value;
         firePropertyChange("address", oldValue, value);
      }
      return this;
   }


   public static final String PROPERTY_id = "id";

   private String id;

   public String getId()
   {
      return id;
   }

   public WarehouseOrder setId(String value)
   {
      if (value == null ? this.id != null : ! value.equals(this.id))
      {
         String oldValue = this.id;
         this.id = value;
         firePropertyChange("id", oldValue, value);
      }
      return this;
   }


   public static final String PROPERTY_warehouse51 = "warehouse51";

   private Warehouse51 warehouse51 = null;

   public Warehouse51 getWarehouse51()
   {
      return this.warehouse51;
   }

   public WarehouseOrder setWarehouse51(Warehouse51 value)
   {
      if (this.warehouse51 != value)
      {
         Warehouse51 oldValue = this.warehouse51;
         if (this.warehouse51 != null)
         {
            this.warehouse51 = null;
            oldValue.withoutOrders(this);
         }
         this.warehouse51 = value;
         if (value != null)
         {
            value.withOrders(this);
         }
         firePropertyChange("warehouse51", oldValue, value);
      }
      return this;
   }



   public static final String PROPERTY_warehouseProduct = "warehouseProduct";

   private WarehouseProduct warehouseProduct = null;

   public WarehouseProduct getWarehouseProduct()
   {
      return this.warehouseProduct;
   }

   public WarehouseOrder setWarehouseProduct(WarehouseProduct value)
   {
      if (this.warehouseProduct != value)
      {
         WarehouseProduct oldValue = this.warehouseProduct;
         if (this.warehouseProduct != null)
         {
            this.warehouseProduct = null;
            oldValue.withoutOrders(this);
         }
         this.warehouseProduct = value;
         if (value != null)
         {
            value.withOrders(this);
         }
         firePropertyChange("warehouseProduct", oldValue, value);
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

      result.append(" ").append(this.getAddress());
      result.append(" ").append(this.getId());


      return result.substring(1);
   }

   public void removeYou()
   {
      this.setWarehouse51(null);
      this.setWarehouseProduct(null);

   }


}