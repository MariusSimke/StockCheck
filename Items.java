// Items

public class Items
{
   private int itemID;
   private String itemName;
   private String description;
   private String location;
   private String quantity;
   private double itemPrice;
   

   // no-argument constructor
   public Items()
   {
   } // end no-argument Items constructor

   // constructor
   public Items( int id, String item_name, String description, String itemLocation, 
      String quantity, double price )
   {
      setItemID( id );
      setItemName( item_name );
      setDescription( description );
      setLocation(itemLocation);
      setQuantity( quantity );
      setPrice( price );
   } // end five-argument Items constructor 

   // sets the Item_ID
   public void setItemID( int id )
   {
      itemID = id;
   } // end method itemID

   // returns the itemID 
   public int getItemID()
   {
      return itemID;
   } // end method getItemID
   
   // sets the ItemName
   public void setItemName( String item )
   {
      itemName = item;
   } // end method setItemName

   // returns the  Itemname 
   public String getItemName()
   {
      return itemName;
   } // end method getItemName
   
   // sets the Description
   public void setDescription( String itemDescription )
   {
      description = itemDescription;
   } // end method setDescription

   // returns the Description
   public String getDescription()
   {
      return description;
   } // end method getDescription
   
   //set the location
    public void setLocation( String itemLocation )
   {
      location = itemLocation;
   } // end method setLocation
    
   // returns the location
   public String getLocation()
   {
      return location;
   } // end method getLocation

   // sets the quantity
   public void setQuantity( String itemQuantity )
   {
      quantity = itemQuantity;
   } // end method quantity

   // returns the quantity
   public String getQuantity()
   {
      return quantity;
   } // end method quantity
   
   // sets the Price
   public void setPrice( double price )
   {
      itemPrice = price;
   } // end method setPrice

   // returns the price
   public double getPrice()
   {
      return itemPrice;
   } // end method getPrice
} // end class Items




 
 
 
 

 




 


 