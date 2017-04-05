// Project 1 Stock Checker

import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List; 
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import java.awt.Color;

public class StockDisplay extends JFrame
{
   private Items currentEntry;
   private StockQueries stockQueries;
   private List< Items > results;   
   private int numberOfEntries = 0;
   private int currentEntryIndex;
 
   private JLabel quantityLabel;
   private JTextField quantityTextField;
   
   private JLabel ItemNameLabel;
   private JTextField ItemNameTextField;
   
   private JLabel idLabel;
   private JTextField idTextField;
   
   private JTextField indexTextField;
   
   private JLabel descriptionLabel;
   private JTextField descriptionTextField;
   
   private JLabel locationLabel;
   private JTextField locationTextField;
   
   private JTextField maxTextField;
   private JButton nextButton;
   private JLabel ofLabel;
   
   private JLabel priceLabel;
   private JTextField priceTextField;
   
   private JButton previousButton;
   private JButton queryButton;//find by...
   private JLabel queryLabel;
   private JPanel queryPanel;
   private JPanel navigatePanel;
   private JPanel displayPanel;
   private JTextField queryTextField;
   private JButton browseButton;
   private JButton insertButton;
   private JButton updateButton;
   private JButton resetButton;
   private JButton quitButton;
   
   
   // no-argument constructor
   public StockDisplay()
   {
      super( "Stock" ); 
      
      // establish database connection and set up PreparedStatements
      stockQueries = new StockQueries(); 
      
      // create GUI
      navigatePanel = new JPanel();
      previousButton = new JButton();
      indexTextField = new JTextField( 2 );
      ofLabel = new JLabel();
      maxTextField = new JTextField( 2 );
      nextButton = new JButton();
      displayPanel = new JPanel();
      
      idLabel = new JLabel();
      idTextField = new JTextField( 10 );
      
      ItemNameLabel = new JLabel();
      ItemNameTextField = new JTextField( 10 );
      
      descriptionLabel = new JLabel();
      descriptionTextField = new JTextField( 10 );
      
      locationLabel = new JLabel("Location");
      locationTextField = new JTextField( 10 );
      
      quantityLabel = new JLabel();
      quantityTextField = new JTextField( 10 );
      
      priceLabel = new JLabel();
      priceTextField = new JTextField( 10 );
      
      queryPanel = new JPanel();
      queryLabel = new JLabel();
      
      queryTextField = new JTextField( 10 );
      queryButton = new JButton();
      
      browseButton = new JButton();
      insertButton = new JButton();
      updateButton = new JButton("Update Record");
      resetButton = new JButton();
      quitButton = new JButton();

      setLayout( new FlowLayout( FlowLayout.CENTER, 10, 10 ) );//<<<<<<<<<<<<
      setSize( 400, 400 );
      setResizable( false );
      
      Random rnd = new Random(); //generates a random color
        int red = rnd.nextInt(200);
        int green = rnd.nextInt(220); 
        int blue = rnd.nextInt(240);
      //colors ganna be diferent(random) every time
        Color theColor = new Color (red, green, blue);
        this.getContentPane().setBackground(theColor);
        
      

      navigatePanel.setLayout(
         new BoxLayout( navigatePanel, BoxLayout.X_AXIS ) );

      previousButton.setText( "Previous" );
      previousButton.setEnabled( false );
      previousButton.addActionListener(
         new ActionListener()
         {
            public void actionPerformed( ActionEvent evt )
            {
               previousButtonActionPerformed( evt );
            } // end method actionPerformed
         } // end anonymous inner class
      ); // end call to addActionListener

      navigatePanel.add( previousButton );
      navigatePanel.add( Box.createHorizontalStrut( 10 ) );

      indexTextField.setHorizontalAlignment(
         JTextField.CENTER );
      indexTextField.addActionListener(
         new ActionListener()
         {
            public void actionPerformed( ActionEvent evt )
            {
               indexTextFieldActionPerformed( evt );
            } // end method actionPerformed
         } // end anonymous inner class
      ); // end call to addActionListener

      navigatePanel.add( indexTextField );
      navigatePanel.add( Box.createHorizontalStrut( 10 ) );

      ofLabel.setText( "of" );
      navigatePanel.add( ofLabel );
      navigatePanel.add( Box.createHorizontalStrut( 10 ) );

      maxTextField.setHorizontalAlignment(
         JTextField.CENTER );
      maxTextField.setEditable( false );
      navigatePanel.add( maxTextField );
      navigatePanel.add( Box.createHorizontalStrut( 10 ) );
      navigatePanel.setBackground(Color.orange);

      nextButton.setText( "Next" );
      nextButton.setEnabled( false );
      nextButton.addActionListener(
         new ActionListener()
         {
            public void actionPerformed( ActionEvent evt )
            {
               nextButtonActionPerformed( evt );
            } // end method actionPerformed
         } // end anonymous inner class
      ); // end call to addActionListener

      navigatePanel.add( nextButton );
      add( navigatePanel );//<<<<<<<<<<<End of Navigation Panel
      
      //Display panel
      displayPanel.setLayout( new GridLayout( 6, 2, 4, 4 ) );
      displayPanel.setBackground(Color.orange);

      idLabel.setText( " ID:" );
      displayPanel.add( idLabel );

      idTextField.setEditable( true ); ////<--------
      displayPanel.add( idTextField );

      ItemNameLabel.setText( "Iteam Name:" );
      displayPanel.add( ItemNameLabel );
      displayPanel.add( ItemNameTextField );

      descriptionLabel.setText( "Description:" );
      displayPanel.add( descriptionLabel );
      displayPanel.add( descriptionTextField );
      
      displayPanel.add( locationLabel );
      displayPanel.add( locationTextField );

      quantityLabel.setText( "Quantity:" );
      displayPanel.add( quantityLabel );
      displayPanel.add( quantityTextField );

      priceLabel.setText( "Price per iteam :" );
      displayPanel.add( priceLabel );
      displayPanel.add( priceTextField );
      add( displayPanel );
      //END of Display panel

      //Query Panel (Find by ...)
      queryPanel.setLayout( 
         new BoxLayout( queryPanel, BoxLayout.X_AXIS) );
      queryPanel.setBackground(Color.orange);

      queryPanel.setBorder( BorderFactory.createTitledBorder(
         "Find an item by name" ) );
      queryLabel.setText( "Name:" );
      queryPanel.add( Box.createHorizontalStrut( 5 ) );
      queryPanel.add( queryLabel );
      queryPanel.add( Box.createHorizontalStrut( 10 ) );
      queryPanel.add( queryTextField );
      queryPanel.add( Box.createHorizontalStrut( 10 ) );

      queryButton.setText( "Find" );
      queryButton.addActionListener(
         new ActionListener()
         {
            public void actionPerformed( ActionEvent evt )
            {
               queryButtonActionPerformed( evt );
            } // end method actionPerformed
         } // end anonymous inner class
      ); // end call to addActionListener

      queryPanel.add( queryButton );
      queryPanel.add( Box.createHorizontalStrut( 5 ) );
      add( queryPanel );
      
      browseButton.setText( "Browse All Entries" );
      browseButton.addActionListener(
         new ActionListener()
         {
            public void actionPerformed( ActionEvent evt )
            {
               browseButtonActionPerformed( evt );
            } // end method actionPerformed
         } // end anonymous inner class
      ); // end call to addActionListener

      add( browseButton );

      insertButton.setText( "Insert New Entry" );
      insertButton.addActionListener(
         new ActionListener()
         {
            public void actionPerformed( ActionEvent evt )
            {
               insertButtonActionPerformed( evt );
               
            } // end method actionPerformed
         } // end anonymous inner class
      ); // end call to addActionListener

	   add( insertButton );
           
           updateButton.addActionListener(
                   new ActionListener()
                   {
                       public void actionPerformed( ActionEvent evt )
                       {
                            updateButtomActionPerformed(evt);
                       }//end of actionPerformed
                   }//end of anonymous class
                   
                   );//end of call to actionListener
                   add(updateButton);

      //resets a data when button is clicked
      resetButton.setText( "Reset" );
      resetButton.addActionListener(
         new ActionListener()
         {
            public void actionPerformed( ActionEvent evt )
            {
              if (evt.getSource() == quitButton)
                    System.exit(0);
              else if (evt.getSource()== resetButton)
              {
                  idTextField.setText("");
                  ItemNameTextField.setText("");
                  descriptionTextField.setText("");
                  locationTextField.setText("");
                  quantityTextField.setText("");
                  priceTextField.setText("");
              } 
               
            } // end method actionPerformed
         } // end anonymous inner class
      ); // end call to reset ActionListener
	   add( resetButton );
           
        //quits aplication when button is clicked   
        quitButton.setText("Quit");
        quitButton.addActionListener(
                 new ActionListener()
         {
            public void actionPerformed( ActionEvent evt )
            {
              if (evt.getSource() == quitButton)
                    System.exit(0);
            } // end method actionPerformed
         } // end anonymous inner class
                );// end call to quitActionListene
           add(quitButton);
           
      addWindowListener
      ( 
         new WindowAdapter() 
         {  
            public void windowClosing( WindowEvent evt )
            {
               stockQueries.close(); //close database connection
               System.exit( 0 );
            } // end method windowClosing
         } // end anonymous inner class
      ); // end call to addWindowListener
	
      setVisible( true );
   } // end no-argument constructor

   // handles call when previousButton is clicked
   private void previousButtonActionPerformed( ActionEvent evt )
   {
      currentEntryIndex--;
      
      if ( currentEntryIndex < 0 )
         currentEntryIndex = numberOfEntries - 1;
      
      indexTextField.setText( "" + ( currentEntryIndex + 1 ) );
      indexTextFieldActionPerformed( evt );  
   } // end method previousButtonActionPerformed

   // handles call when nextButton is clicked
   private void nextButtonActionPerformed( ActionEvent evt ) 
   {
      currentEntryIndex++;
      
      if ( currentEntryIndex >= numberOfEntries )
         currentEntryIndex = 0;
      
      indexTextField.setText( "" + ( currentEntryIndex + 1 ) );
      indexTextFieldActionPerformed( evt );
   } // end method nextButtonActionPerformed

   // handles call when Find Button is clicked
   private void queryButtonActionPerformed( ActionEvent evt )
   {
      results = 
         stockQueries.getItem( queryTextField.getText() );
      numberOfEntries = results.size();
      
      if ( numberOfEntries != 0 )
      {
         currentEntryIndex = 0;
         currentEntry = results.get( currentEntryIndex );
         idTextField.setText("" + currentEntry.getItemID() );
         ItemNameTextField.setText( currentEntry.getItemName() );
         descriptionTextField.setText( currentEntry.getDescription() );
         locationTextField.setText( currentEntry.getLocation() );
         quantityTextField.setText( currentEntry.getQuantity() );
         priceTextField.setText(""+ currentEntry.getPrice() );
         maxTextField.setText( "" + numberOfEntries );
         indexTextField.setText( "" + ( currentEntryIndex + 1 ) );
         nextButton.setEnabled( true );
         previousButton.setEnabled( true );
      } // end if
      else
         browseButtonActionPerformed( evt );
   } // end method queryButtonActionPerformed

   // handles call when a new value is entered in indexTextField
   private void indexTextFieldActionPerformed( ActionEvent evt )
   {
      currentEntryIndex = 
         ( Integer.parseInt( indexTextField.getText() ) - 1 );
      
      if ( numberOfEntries != 0 && currentEntryIndex < numberOfEntries )
      {
         currentEntry = results.get( currentEntryIndex );
         idTextField.setText("" + currentEntry.getItemID() );
         ItemNameTextField.setText( currentEntry.getItemName() );
         descriptionTextField.setText( currentEntry.getDescription() );
         locationTextField.setText( currentEntry.getLocation() );
         quantityTextField.setText( currentEntry.getQuantity() );
         priceTextField.setText( ""+currentEntry.getPrice() );
         maxTextField.setText( "" + numberOfEntries );
         indexTextField.setText( "" + ( currentEntryIndex + 1 ) );
      } // end if
    } // end method indexTextFieldActionPerformed

   // handles call when browseButton is clicked
   private void browseButtonActionPerformed( ActionEvent evt )
   {
      try
      {
         results = stockQueries.getAllItems();
         numberOfEntries = results.size();
      
         if ( numberOfEntries != 0 )
         {
            currentEntryIndex = 0;
            currentEntry = results.get( currentEntryIndex );
            idTextField.setText("" + currentEntry.getItemID() );
            ItemNameTextField.setText("" + currentEntry.getItemName() );
            descriptionTextField.setText("" + currentEntry.getDescription() );
            locationTextField.setText( "" + currentEntry.getLocation() );
            quantityTextField.setText("" + currentEntry.getQuantity() );
            priceTextField.setText( ""+currentEntry.getPrice() );
            maxTextField.setText( "" + numberOfEntries );
            indexTextField.setText( "" + ( currentEntryIndex + 1 ) );
            nextButton.setEnabled( true );
            previousButton.setEnabled( true );
         } // end if
      } // end try
      catch ( Exception e )
      {
         e.printStackTrace();
      } // end catch
   } // end method browseButtonActionPerformed

   // handles call when insertButton is clicked
   private void insertButtonActionPerformed( ActionEvent evt ) 
   {
      int result = stockQueries.addIteam( 
                                        Integer.parseInt(idTextField.getText()),
                                        ItemNameTextField.getText(),
                                        descriptionTextField.getText(),
                                        locationTextField.getText(),
                                        quantityTextField.getText(),
                                        Double.parseDouble(priceTextField.getText())
                                        );
      
      if ( result == 1 )
         JOptionPane.showMessageDialog( this, "Item added!",
            "Item added", JOptionPane.PLAIN_MESSAGE );
      else
         JOptionPane.showMessageDialog( this, "Item not added!",
            "Error", JOptionPane.PLAIN_MESSAGE );
          
      browseButtonActionPerformed( evt );
   } // end method insertButtonActionPerformed
   
    // handles call when updateButton is clicked
  private  void updateButtomActionPerformed( ActionEvent evt )
  {
      int result = stockQueries.updateItemRecord  (
                                        Integer.parseInt(idTextField.getText()),
                                        ItemNameTextField.getText(),
                                        descriptionTextField.getText(),
                                        locationTextField.getText(),
                                        quantityTextField.getText(),
                                        Double.parseDouble(priceTextField.getText())
                                     );
      if ( result == 1 )
         JOptionPane.showMessageDialog( this, "Item updated!",
            "Item updated", JOptionPane.PLAIN_MESSAGE );
      else
         JOptionPane.showMessageDialog( this, "Item not updated!",
            "Error", JOptionPane.PLAIN_MESSAGE );
          
      browseButtonActionPerformed( evt );
  }//end of updateButtomActionPerformed
 
  //closes the aplication
   public static void main( String args[] )
   {
      new StockDisplay();
   } // end method main
} // end class AddressBookDisplay




 
 
 

 

 




 


 