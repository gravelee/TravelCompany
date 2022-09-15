package com.travelcompany.eshop;

import com.travelcompany.eshop.domain.Itinerary;
import com.travelcompany.eshop.domain.PaymentType;
import com.travelcompany.eshop.service.TravelCompanyService;
import com.travelcompany.eshop.service.impl.TravelCompanyServiceImpl;
import com.travelcompany.eshop.utility.GeneralUtility;
import java.io.FileNotFoundException;
import java.math.BigDecimal;


/*
    Addons
    
    Add the functionality to add new purchases with timestamps to the file system.
    Add max number of sits to the itineraries.
    Write the collections to csv files.

    make the order id has some composure and not be random.
        
    Create a README.txt file to explain the process to run the program.
*/


/**
 *
 * @author Grproth
 */
public class Eshop {
    
    public static void main(String[] args) throws FileNotFoundException {
        
        TravelCompanyService service = new TravelCompanyServiceImpl();
        
        service.loadCustomersData();
        service.loadItinerariesData();
        service.loadOrdersData();
    
        
        service.displayAllOrdersWithCosts();                // found 8
        
        
        service.displaySpecificItineraries( "ATH", "LON");  // found one
        service.displaySpecificItineraries( "ATH", "SKG");  // not found
        service.displaySpecificItineraries( null, "LON");   // found one
        service.displaySpecificItineraries( null, "SKG");   // not found
        service.displaySpecificItineraries( "ATH", null);   // found 8
        service.displaySpecificItineraries( "SKG", null);   // not found
        
        service.displaySpecificItineraries( "ATH", "MEX");  // found one
        service.displaySpecificItineraries( null, "MEX");   // found one
        service.displaySpecificItineraries( "ATH", "DUB");  // found two
        service.displaySpecificItineraries( null, "DUB");   // found two
        service.displaySpecificItineraries( "ATH", null);   // found 8
        service.displaySpecificItineraries( null, null);    // found 8
        
        
        service.displayCustomers( 0, new BigDecimal("500.00")); // found 2
        service.displayCustomers( 0, new BigDecimal("350.00")); // found 3
        service.displayCustomers( 0, new BigDecimal("300.00")); // found 6
        service.displayCustomers( 0, new BigDecimal("250.00")); // found 6
        service.displayCustomers( 0, new BigDecimal("200.00")); // found 8
        
        service.displayCustomers( 1, new BigDecimal("350.00")); // found 3
        service.displayCustomers( 1, null);                     // found 5
        
        service.displayCustomers( 2, new BigDecimal("350.00")); // not found
        service.displayCustomers( 2, new BigDecimal("300.00")); // found 2
        service.displayCustomers( 2, new BigDecimal("200.00")); // found 3
        service.displayCustomers( 2, null);                     // found 3
        
        service.displayCustomers( 3, new BigDecimal("250.00")); // not found
        service.displayCustomers( 3, new BigDecimal("200.00")); // not found
        service.displayCustomers( 3, null);                     // not found
        
        service.displayCustomers( 4, new BigDecimal("250.00")); // not found
        service.displayCustomers( 4, null);                     // not found
        
        service.displayAllCustomersWithoutOrders();             // found 4
        
        Itinerary itinerary = new Itinerary( 
                GeneralUtility.createNewId( null, 1_000),"SKG","ATH","28/11/2023",
                "16:45","Skypia Express",new BigDecimal("666.66"));
        /*
        service.createNewOrder( 10L, itinerary.getId(), PaymentType.CREDIT_CARD);  
        // error customer/itinerrary not found
        service.createNewOrder( 9L, itinerary.getId(), PaymentType.CASH); 
        // error itinerrary not found
        service.createNewOrder( 7L, 10L, PaymentType.CREDIT_CARD);
        // error itinerrary not found*/
        service.createNewOrder( 4L, 1L, PaymentType.CASH);          // done! =300 euros
        service.createNewOrder( 7L, 9L, PaymentType.CREDIT_CARD);   // done! ~650 euros
        service.createNewOrder( 3L, 6L, PaymentType.CREDIT_CARD);   // done! ~300 euros
        service.createNewOrder( 3L, 7L, PaymentType.CASH);          // done! ~320 euros
        // the new order will be saved with the disconted price
        
        service.displayAllOrdersWithCosts();
        
        service.displayCustomers( 0, new BigDecimal("500.00")); // found 2
        service.displayCustomers( 0, new BigDecimal("350.00")); // found 3
        service.displayCustomers( 0, new BigDecimal("300.00")); // found 6
        service.displayCustomers( 0, new BigDecimal("250.00")); // found 6
        service.displayCustomers( 0, new BigDecimal("200.00")); // found 8
        
        service.displayCustomers( 1, new BigDecimal("350.00")); // found 4
        service.displayCustomers( 1, null);                     // found 6
        
        service.displayCustomers( 2, new BigDecimal("350.00")); // not found
        service.displayCustomers( 2, new BigDecimal("300.00")); // found 3
        service.displayCustomers( 2, new BigDecimal("200.00")); // found 4
        service.displayCustomers( 2, null);                     // found 4
        
        service.displayCustomers( 3, new BigDecimal("250.00")); // not found
        service.displayCustomers( 3, new BigDecimal("200.00")); // found one
        service.displayCustomers( 3, null);                     // found one
        
        service.displayCustomers( 4, new BigDecimal("250.00")); // not found
        service.displayCustomers( 4, null);                     // found one
        
        service.displayAllCustomersWithoutOrders();             // found 3
        
        
        //service.saveCustomersData();
        //service.saveItinerariesData();
        //service.saveOrdersData();
    }
}
