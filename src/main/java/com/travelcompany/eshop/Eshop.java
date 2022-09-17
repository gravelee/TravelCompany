package com.travelcompany.eshop;

import com.travelcompany.eshop.domain.Itinerary;
import com.travelcompany.eshop.domain.PaymentType;
import com.travelcompany.eshop.service.TravelCompanyService;
import com.travelcompany.eshop.service.impl.TravelCompanyServiceImpl;
import com.travelcompany.eshop.utility.GeneralUtility;
import java.io.FileNotFoundException;
import java.math.BigDecimal;

/**
 *  The driver class of our project, here we start the application's logic.
 *  We have tests for our data within the .csv files.
 * 
 *  @author Grproth (gravelee)
 */
public class Eshop {
    
    public static void main( String[] args) throws FileNotFoundException {
        
        TravelCompanyService service = new TravelCompanyServiceImpl();
        
        service.loadCustomersData();
        service.loadItinerariesData();
        service.loadOrdersData();
        
        
        service.displayAllOrdersWithCosts();                // found 8
        
        
        service.displaySpecificItineraries( "ATH", "LON");  // found 1
        service.displaySpecificItineraries( "ATH", "SKG");  // not found
        service.displaySpecificItineraries( null, "LON");   // found 1
        service.displaySpecificItineraries( null, "SKG");   // not found
        service.displaySpecificItineraries( "ATH", null);   // found 9
        service.displaySpecificItineraries( "SKG", null);   // not found
        
        service.displaySpecificItineraries( "ATH", "MEX");  // found 1
        service.displaySpecificItineraries( null, "MEX");   // found 1
        service.displaySpecificItineraries( "ATH", "DUB");  // found 2
        service.displaySpecificItineraries( null, "DUB");   // found 2
        service.displaySpecificItineraries( "ATH", null);   // found 9
        service.displaySpecificItineraries( null, null);    // found 9
        
        // if you want to print the map that keep the info about the customers
        //  you need to uncomment lines 459-460 from file 
        //  TravelCompanyServiceImpl.java
        //  This helps for checking purposes.
        
        service.displayCustomers( 0, new BigDecimal("500.00")); // found 4
        service.displayCustomers( 0, new BigDecimal("350.00")); // found 5
        service.displayCustomers( 0, new BigDecimal("200.00")); // found 5
        
        service.displayCustomers( 1, new BigDecimal("500.00")); // found 4
        service.displayCustomers( 1, null);                     // found 5
        
        service.displayCustomers( 2, new BigDecimal("500.00")); // found 2
        service.displayCustomers( 2, null);                     // found 3
        
        service.displayCustomers( 3, new BigDecimal("750.00")); // not found
        service.displayCustomers( 3, new BigDecimal("500.00")); // not found
        service.displayCustomers( 3, null);                     // not found
        
        service.displayCustomers( 4, new BigDecimal("250.00")); // not found
        service.displayCustomers( 4, null);                     // not found
        
        service.displayAllCustomersWithoutOrders();             // found 4
        
        Itinerary itinerary = new Itinerary( 
            GeneralUtility.createNewId( null, 1_000),"SKG","ATH","28/11/2023",
            "16:45","Skypia Express",new BigDecimal("666.66"));
        
        service.createNewOrder(10L, itinerary.getId(), PaymentType.CREDIT_CARD);  
        // error customer/itinerrary not found
        service.createNewOrder( 9L, itinerary.getId(), PaymentType.CASH); 
        // error itinerrary not found
        service.createNewOrder( 7L, 10L, PaymentType.CREDIT_CARD);
        // error itinerrary not found
        service.createNewOrder( 4L, 1L, PaymentType.CASH);
        service.createNewOrder( 4L, 8L, PaymentType.CASH);
        service.createNewOrder( 7L, 9L, PaymentType.CREDIT_CARD);
        service.createNewOrder( 3L, 6L, PaymentType.CREDIT_CARD);
        service.createNewOrder( 3L, 7L, PaymentType.CASH);
        // the new orders will be saved with the disconted prices
        
        service.displayAllOrdersWithCosts();
        
        service.displayCustomers( 0, new BigDecimal("1000.00"));    // found 2
        service.displayCustomers( 0, new BigDecimal("750.00"));     // found 4
        service.displayCustomers( 0, new BigDecimal("600.00"));     // found 6
        service.displayCustomers( 0, null);                         // found 9
        
        service.displayCustomers( 1, new BigDecimal("1000.00"));    // found 2
        service.displayCustomers( 1, new BigDecimal("750.00"));     // found 4
        service.displayCustomers( 1, new BigDecimal("600.00"));     // found 6
        service.displayCustomers( 1, null);                         // found 6
        
        service.displayCustomers( 2, new BigDecimal("1000.00"));    // found 2
        service.displayCustomers( 2, new BigDecimal("750.00"));     // found 3
        service.displayCustomers( 2, new BigDecimal("600.00"));     // found 4
        service.displayCustomers( 2, null);                         // found 4
        
        service.displayCustomers( 3, new BigDecimal("1000.00"));    // found 2
        service.displayCustomers( 3, null);                         // found 2
        
        service.displayCustomers( 4, new BigDecimal("3000.00"));    // not found
        service.displayCustomers( 4, new BigDecimal("2000.00"));    // found 1
        service.displayCustomers( 4, new BigDecimal("1000.00"));    // found 1
        service.displayCustomers( 4, null);                         // found 1
        
        service.displayAllCustomersWithoutOrders();                 // found 3
        
        
        //service.saveCustomersData();
        //service.saveItinerariesData();
        //service.saveOrdersData();
    }
}
