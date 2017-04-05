// StockQueries.java

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class StockQueries 
{
   private static final String URL = "jdbc:mysql://localhost/stockcheck";
   private static final String USERNAME = "root";
   private static final String PASSWORD = "1234";

   private Connection connection = null; // manages connection
   private PreparedStatement selectAllItems = null; 
   private PreparedStatement selectItemByName = null; 
   private PreparedStatement insertNewItem = null; 
   //private PreparedStatement deleteItem = null; 
   private PreparedStatement updateItem = null; 
   
    
   // constructor retrieves data ussing SQL 
   public StockQueries()
   {
      try 
      {
         connection = 
            DriverManager.getConnection( URL, USERNAME, PASSWORD );

         // create query that selects all entries in the stockcheck
         selectAllItems = 
            connection.prepareStatement( "SELECT * FROM Items" );
         
         // create query that selects entries with a specific last name
         selectItemByName = connection.prepareStatement( 
            "SELECT * FROM Items WHERE ItemName = ?" );
         
         // create insert that adds a new entry into the database
         insertNewItem = connection.prepareStatement( 
            "INSERT INTO Items " + 
            "( Item_Id, ItemName, Description, Location, Quantity, Price ) " + 
            "VALUES ( ?, ?, ?, ?, ?, ? )" );
         
         //create update that updates the record in the database
         updateItem = connection.prepareStatement(    
                    "UPDATE Items "
                  + "SET ItemName=?, Description=?, Location=?, Quantity=?, Price=? "
                  + "WHERE Item_Id=?" );
         
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         System.exit( 1 );
      } // end catch
   } // end StockQueries constructor
   
   // select all of the iteams in the database
   public List< Items > getAllItems()
   {
      List< Items > results = null;
      ResultSet resultSet = null;
      
      try 
      {
         // executeQuery returns ResultSet containing matching entries
         resultSet = selectAllItems.executeQuery(); 
         results = new ArrayList< Items >();
         
         while ( resultSet.next() )
         {
            results.add( new Items(
               resultSet.getInt( "Item_Id" ),
               resultSet.getString( "itemName" ),
               resultSet.getString( "description" ),
               resultSet.getString("location"),     
               resultSet.getString( "quantity" ),
               resultSet.getDouble( "price" ) ) );
         } // end while
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();         
      } // end catch
      finally
      {
         try 
         {
            resultSet.close();
         } // end try
         catch ( SQLException sqlException )
         {
            sqlException.printStackTrace();         
            close();
         } // end catch
      } // end finally
      
      return results;
   } // end method getAllItem
   
   //return item by name
   public List< Items > getItem( String itemName )
   {
      List< Items > results = null;
      ResultSet resultSet = null;

      try 
      {
         selectItemByName.setString( 1, itemName ); // specify description (color)

         // executeQuery returns ResultSet containing matching entries
         resultSet = selectItemByName.executeQuery(); 

         results = new ArrayList< Items >();

         while ( resultSet.next() )
         {
            results.add( new Items(
               resultSet.getInt( "Item_Id" ),
               resultSet.getString( "itemName" ),
               resultSet.getString( "description" ),
               resultSet.getString("location"),     
               resultSet.getString( "quantity" ),
               resultSet.getDouble( "price" ) ) );
         } // end while
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      } // end catch
      finally
      {
         try 
         {
            resultSet.close();
         } // end try
         catch ( SQLException sqlException )
         {
            sqlException.printStackTrace();         
            close();
         } // end catch
      } // end finally
      
      return results;
   } // end method getIteam
   
   // add an entry
   public int addIteam( 
      int id, String name, String description, String location, String quantity, double price )
   {
      int result = 0;
      
      // set parameters, then execute insertNewItem int id,
      try 
      {
         insertNewItem.setInt(1, id);
         insertNewItem.setString( 2, name );
         insertNewItem.setString( 3, description );
         insertNewItem.setString( 4, location );
         insertNewItem.setString( 5, quantity );
         insertNewItem.setDouble( 6, price );

         // insert the new entry; returns # of rows updated
         result = insertNewItem.executeUpdate(); 
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         close();
      } // end catch
      
      return result;
   } // end method addPerson
   
   public int updateItemRecord( int id,  String name, String description, String location, 
                                    String quantity, double price)
   { 
       int result = 0; 
      // set parameters, then execute insertNewItem int id,
      try 
      {
         
         updateItem.setString( 1, name );
         updateItem.setString( 2, description );
         updateItem.setString( 3, location );
         updateItem.setString( 4, quantity );
         updateItem.setDouble( 5, price );
         updateItem.setInt(6, id);

         // updateIteam entry; returns # of rows updated
        System.out.println(updateItem.toString());
         result = updateItem.executeUpdate(); 
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         close();
      } // end catch
      
      return result;
   }//end of updateItemRecord method
   // close the database connection
   public void close()
   {
      try 
      {
         connection.close();
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      } // end catch
   } // end method close
} // end interface StockQueries




 
 
 
 

 




 


 