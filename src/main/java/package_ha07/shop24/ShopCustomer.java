package package_ha07.shop24;

import java.beans.PropertyChangeSupport;

import java.beans.PropertyChangeListener;

public class ShopCustomer 
{

   public static final String PROPERTY_name = "name";

   private String name;

   public String getName()
   {
      return name;
   }

   public ShopCustomer setName(String value)
   {
      if (value == null ? this.name != null : ! value.equals(this.name))
      {
         String oldValue = this.name;
         this.name = value;
         firePropertyChange("name", oldValue, value);
      }
      return this;
   }


   public static final String PROPERTY_address = "address";

   private String address;

   public String getAddress()
   {
      return address;
   }

   public ShopCustomer setAddress(String value)
   {
      if (value == null ? this.address != null : ! value.equals(this.address))
      {
         String oldValue = this.address;
         this.address = value;
         firePropertyChange("address", oldValue, value);
      }
      return this;
   }


   public static final String PROPERTY_shop24 = "shop24";

   private Shop24 shop24 = null;

   public Shop24 getShop24()
   {
      return this.shop24;
   }

   public ShopCustomer setShop24(Shop24 value)
   {
      if (this.shop24 != value)
      {
         Shop24 oldValue = this.shop24;
         if (this.shop24 != null)
         {
            this.shop24 = null;
            oldValue.withoutCustomers(this);
         }
         this.shop24 = value;
         if (value != null)
         {
            value.withCustomers(this);
         }
         firePropertyChange("shop24", oldValue, value);
      }
      return this;
   }



   public static final java.util.ArrayList<ShopOrder> EMPTY_orders = new java.util.ArrayList<ShopOrder>()
   { @Override public boolean add(ShopOrder value){ throw new UnsupportedOperationException("No direct add! Use xy.withOrders(obj)"); }};


   public static final String PROPERTY_orders = "orders";

   private java.util.ArrayList<ShopOrder> orders = null;

   public java.util.ArrayList<ShopOrder> getOrders()
   {
      if (this.orders == null)
      {
         return EMPTY_orders;
      }

      return this.orders;
   }

   public ShopCustomer withOrders(Object... value)
   {
      if(value==null) return this;
      for (Object item : value)
      {
         if (item == null) continue;
         if (item instanceof java.util.Collection)
         {
            for (Object i : (java.util.Collection) item)
            {
               this.withOrders(i);
            }
         }
         else if (item instanceof ShopOrder)
         {
            if (this.orders == null)
            {
               this.orders = new java.util.ArrayList<ShopOrder>();
            }
            if ( ! this.orders.contains(item))
            {
               this.orders.add((ShopOrder)item);
               ((ShopOrder)item).setShopCustomer(this);
               firePropertyChange("orders", null, item);
            }
         }
         else throw new IllegalArgumentException();
      }
      return this;
   }



   public ShopCustomer withoutOrders(Object... value)
   {
      if (this.orders == null || value==null) return this;
      for (Object item : value)
      {
         if (item == null) continue;
         if (item instanceof java.util.Collection)
         {
            for (Object i : (java.util.Collection) item)
            {
               this.withoutOrders(i);
            }
         }
         else if (item instanceof ShopOrder)
         {
            if (this.orders.contains(item))
            {
               this.orders.remove((ShopOrder)item);
               ((ShopOrder)item).setShopCustomer(null);
               firePropertyChange("orders", item, null);
            }
         }
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

      result.append(" ").append(this.getName());
      result.append(" ").append(this.getAddress());


      return result.substring(1);
   }

   public void removeYou()
   {
      this.setShop24(null);

      this.withoutOrders(this.getOrders().clone());


   }


}