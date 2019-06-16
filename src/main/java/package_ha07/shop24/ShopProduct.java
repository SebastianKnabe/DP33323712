package package_ha07.shop24;

import java.beans.PropertyChangeSupport;

import java.beans.PropertyChangeListener;

public class ShopProduct  
{

	public double getInStock()
   {
      return inStock;
   }

	public ShopProduct setInStock(double value)
   {
      if (value != this.inStock)
      {
         double oldValue = this.inStock;
         this.inStock = value;
         firePropertyChange("inStock", oldValue, value);
      }
      return this;
   }

	public String getName()
   {
      return name;
   }

   public static final String PROPERTY_id = "id";

   private String id;

   public String getId()
   {
      return id;
   }

   public ShopProduct setId(String value)
   {
      if (value == null ? this.id != null : ! value.equals(this.id))
      {
         String oldValue = this.id;
         this.id = value;
         firePropertyChange("id", oldValue, value);
      }
      return this;
   }


   public static final String PROPERTY_inStock = "inStock";

   private double inStock;

   public static final String PROPERTY_name = "name";

   private String name;

   public ShopProduct setName(String value)
   {
      if (value == null ? this.name != null : ! value.equals(this.name))
      {
         String oldValue = this.name;
         this.name = value;
         firePropertyChange("name", oldValue, value);
      }
      return this;
   }


   public static final String PROPERTY_price = "price";

   private double price;

   public double getPrice()
   {
      return price;
   }

   public ShopProduct setPrice(double value)
   {
      if (value != this.price)
      {
         double oldValue = this.price;
         this.price = value;
         firePropertyChange("price", oldValue, value);
      }
      return this;
   }


   public static final String PROPERTY_shop24 = "shop24";

   private Shop24 shop24 = null;

   public Shop24 getShop24()
   {
      return this.shop24;
   }

   public ShopProduct setShop24(Shop24 value)
   {
      if (this.shop24 != value)
      {
         Shop24 oldValue = this.shop24;
         if (this.shop24 != null)
         {
            this.shop24 = null;
            oldValue.withoutProducts(this);
         }
         this.shop24 = value;
         if (value != null)
         {
            value.withProducts(this);
         }
         firePropertyChange("shop24", oldValue, value);
      }
      return this;
   }



   public static final String PROPERTY_order = "order";

   private ShopOrder order = null;

   public ShopOrder getOrder()
   {
      return this.order;
   }

   public ShopProduct setOrder(ShopOrder value)
   {
      if (this.order != value)
      {
         ShopOrder oldValue = this.order;
         if (this.order != null)
         {
            this.order = null;
            oldValue.setShopProduct(null);
         }
         this.order = value;
         if (value != null)
         {
            value.setShopProduct(this);
         }
         firePropertyChange("order", oldValue, value);
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
      result.append(" ").append(this.getName());


      return result.substring(1);
   }

   public void removeYou()
   {
      this.setShop24(null);
      this.setOrder(null);

   }

}
